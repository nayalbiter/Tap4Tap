package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;

// Handle the "showLogin" command
public class ShowLoginHandler implements CommandHandler<Tap4tapServlet> {
    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {
        String template = "login.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        String breweryId = request.getParameter("breweryId");
        templateFields.put("breweryId", breweryId);
        CommandUtils.getSessionVariables(request, templateFields);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
