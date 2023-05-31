package edu.lwtech.csd297.tap4tap.commands;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import freemarker.template.*;
import freemarker.template.utility.NullArgumentException;

import org.apache.logging.log4j.*;

public class CommandUtils {

    private static final Logger logger = LogManager.getLogger(CommandUtils.class);

    private CommandUtils() {
        // Prevent instantiation
    }

    // Get the user's session variables (if they exist)
    public static void getSessionVariables(HttpServletRequest request, Map<String, Object> templateFields) {
        User loggedInUser = null;
        boolean loggedIn = false;
        HttpSession session = request.getSession(false);            // false == don't create a new session if one doesn't exist
        if (session != null) {
            try {
                loggedInUser = (User)session.getAttribute("loggedInUser");
                loggedIn = true;
            } catch (NullArgumentException e) {
                loggedInUser = null;
                logger.debug("seesion without loggeduser name");
                loggedIn = false;
            }
        }
        if(loggedInUser != null){templateFields.put("owner", loggedInUser);}
        else{templateFields.put("owner", "");}
        templateFields.put("loggedIn", loggedIn);
    }

    public static String mergeTemplate(String template, Map<String, Object> templateFields, Configuration config) {
        try {
            StringWriter out = new StringWriter();
            Template view = config.getTemplate(template);
            view.process(templateFields, out);
            return out.toString();
        } catch (IOException | TemplateException e) {
            logger.error("Unexpected error processing Freemarker template: {}", template, e);
            return "Template error: " + e.toString();
        }
    }

    public static int parseInt(String s) {
        int i = -1;
        if (s != null) {
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                logger.error("Unable to parse integer from {}", s);
            }
        }
        return i;
    }

}
