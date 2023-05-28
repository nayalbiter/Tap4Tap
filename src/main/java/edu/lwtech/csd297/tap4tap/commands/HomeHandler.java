package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;
import edu.lwtech.csd297.tap4tap.daos.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import org.apache.logging.log4j.*;
// Handle the "home" command
public class HomeHandler implements CommandHandler<Tap4tapServlet> {
 private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);
    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {
        if(request.getSession(false) != null)logger.debug("coming back home {}", request.getSession().getAttribute("owner"));
        logger.debug("welcome home");
        String template = "home.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        // TODO: Add template variables to the templateFields map

        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
