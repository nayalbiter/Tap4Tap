package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;

// Handle the "logout" command
public class LogoutHandler implements CommandHandler<Tap4tapServlet> {

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {
        boolean loggedIn = false;
        String template = "confirm.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        HttpSession session = request.getSession(false);            // false == don't create a new session if one doesn't exist

        // Logout the user by killing their session
        if (session != null) {
            session.invalidate();
        }
        templateFields.put("message", "You have been successfully logged out.<br /><a href='?cmd=home'>Home</a>");
        templateFields.put("loggedIn", loggedIn);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
