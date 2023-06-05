package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import at.favre.lib.crypto.bcrypt.BCrypt;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.*;
import org.apache.logging.log4j.*;

// Handle the "login" command
public class LoginHandler implements CommandHandler<Tap4tapServlet> {
    private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) {

        boolean loggedIn = false;
        String message = "";
        String template = "confirm.ftl";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loggedInUser = null;
        String breweryId = "";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        breweryId = request.getParameter("breweryId");
        User user = servlet.getUserDAO().retrieveByUsername(username);
        if (user == null) {
            message = "We do not have a member with that username on file. Please try again.";
            templateFields.put("message", message);
            templateFields.put("loggedIn", false);
            return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
        }
        else{
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getHashedPasword());
            loggedInUser = user;
            if (result.verified) {
                loggedIn = true;
                HttpSession session = request.getSession(true);      // true == Create a new session for this user
                session.setAttribute("loggedInUser", loggedInUser);
                logger.debug("getting breweryId: {}", breweryId);
                if(user.getAdmin()){
                    message = "Welcome back, " + user.getDisplayName() + "<br /><a href='?cmd=admin'>Admin Page</a> <br /><a href='?cmd=home'>Search brewery</a>";
                    HashMap<UUID, Brewery> breweryList= new HashMap<>();
                    session.setAttribute("breweryMapList", breweryList);
                }
                else if(breweryId == null){
                    message = "You have been successfully logged in to your account.<br /><a href='?cmd=home'>Home</a>";
                }
                else{
                    message = "You have been successfully logged in to your account.<br />Brewery is added to you list.<br/><a href='?cmd=myAccount&breweryId=" + breweryId + "'>My Account</a>";
                }

            } else {
                message = "Your password did not match what we have on file. Please try again.<br /><a href='?cmd=showLogin'>Log In</a>";
            }
        }

        templateFields.put("owner", loggedInUser);
        templateFields.put("loggedIn", loggedIn);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }

}
