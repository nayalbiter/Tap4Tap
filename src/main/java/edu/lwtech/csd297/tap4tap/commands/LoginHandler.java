package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import at.favre.lib.crypto.bcrypt.BCrypt;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.*;

// Handle the "login" command
public class LoginHandler implements CommandHandler<Tap4tapServlet> {

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {

        String ownerDisplayName = "";
        boolean loggedIn = false;
        String message = "";
        String template = "confirm.ftl";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loggedInUser = "";

        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        User user = servlet.getUserDAO().retrieveByUsername(username);
        if (user == null) {
            message = "We do not have a member with that username on file. Please try again.";
            templateFields.put("message", message);
            templateFields.put("loggedIn", false);
            return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
        }

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getHashedPasword());
        if (result.verified) {
            loggedIn = true;
            loggedInUser = user.getDisplayName();
            HttpSession session = request.getSession(true);         // true == Create a new session for this user
            session.setAttribute("owner", loggedInUser);
            message = "You have been successfully logged in to your account.<br /><a href='?cmd=home'>Home</a>";

        } else {
            message = "Your password did not match what we have on file. Please try again.<br /><a href='?cmd=showLogin'>Log In</a>";
        }
        templateFields.put("owner", loggedInUser);
        templateFields.put("loggedIn", loggedIn);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
