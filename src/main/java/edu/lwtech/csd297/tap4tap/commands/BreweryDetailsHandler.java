package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;

public class BreweryDetailsHandler implements CommandHandler<Tap4tapServlet> {
    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet){
        String template = "breweryDetails.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
