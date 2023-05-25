package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.*;

// Handle the "login" command
public class LoginHandler implements CommandHandler<Tap4tapServlet> {

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {

        boolean loggedIn = false;
        String message = "";
        String template = "confirm.ftl";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        List<User> Users = servlet.getUserDAO().retrieveByName(username);
        if (Users == null) {
            message = "We do not have a member with that username on file. Please try again.";
            templateFields.put("message", message);
            templateFields.put("loggedIn", false);
            return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
        }
        for(User user : Users){
            if (user.getHashedPasword().equals(password)) {
                // int ownerID = member.getUserId();
                loggedIn = true;
                HttpSession session = request.getSession(true);         // true == Create a new session for this user
                session.setAttribute("owner", 1);
                // session.setAttribute("owner", ownerID);
                message = "You have been successfully logged in to your account.<br /><a href='?cmd=home'>Home</a>";
            } else {
                message = "Your password did not match what we have on file. Please try again.<br /><a href='?cmd=showLogin'>Log In</a>";
            }
        }

        templateFields.put("loggedIn", loggedIn);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
