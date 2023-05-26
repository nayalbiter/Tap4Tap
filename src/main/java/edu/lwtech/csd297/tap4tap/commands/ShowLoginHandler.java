package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;

// Handle the "showLogin" command
public class ShowLoginHandler implements CommandHandler<Tap4tapServlet> {
    private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {
        logger.debug("showlogin handler is initialized");
        String template = "login.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
