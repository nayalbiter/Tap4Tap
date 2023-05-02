package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;

// Handle the "home" command
public class HomeHandler implements CommandHandler<Tap4tapServlet> {

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {
        String template = "home.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        // TODO: Add template variables to the templateFields map

        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
