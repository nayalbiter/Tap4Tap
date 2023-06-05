package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import org.apache.logging.log4j.*;
import at.favre.lib.crypto.bcrypt.BCrypt;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.daos.*;
import edu.lwtech.csd297.tap4tap.pojos.*;

public class ForgotPasswordHandler implements CommandHandler<Tap4tapServlet>{
    private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet){
        String template = "forgotPassword.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        UserDAO userDAO = servlet.getUserDAO();

        User user = null;
        String userName = request.getParameter("userName");
        user = userDAO.retrieveByUsername(userName);

        String securityQuestion = null;
        boolean validUser = false;

        if(user == null){
            securityQuestion = "Username doesn't exist";
        }
        else if(user.getUsername().equals(userName)){
            securityQuestion = user.getSecurityQuestion();
            validUser = true;
        }
        else if(!user.getUsername().equals(userName)){
            securityQuestion = "Can't find username";
        }
        else{
            securityQuestion = "Can't find your security question";
        }

        boolean showModal = false;
        String update = request.getParameter("action");
        if(update!= null && update.equals("update")){
            showModal = true;
        }

        String securityAnswer = null;
        boolean reset = false;
        String message = null;
        try{securityAnswer = request.getParameter("securityAnswer");}
        catch(NullPointerException e){
            securityAnswer = null;
        }

        if(validUser && securityAnswer != null){
            BCrypt.Result result = BCrypt.verifyer().verify(securityAnswer.toCharArray(), user.getHashedSecurityAnswer());
            if(result.verified){
                logger.debug("security answer match");
                String newPassword = request.getParameter("newPassword");
                String newPasswordRepeat = request.getParameter("newPasswordRepeat");
                if(newPassword.equals(newPasswordRepeat)){
                    logger.debug("password and repeat match");
                    String newHashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
                    reset = userDAO.setPassword(user.getUserId(), newHashedPassword);
                    if(reset) message = "successfully reset the password";
                    else message = "fail to reset the password. try again";
                }
                else{
                    logger.debug("2 passwords don't match");
                    message = "New password and repeate new password don't match";
                }
            }
            else{
                logger.debug("security answer is wrong");
                message = "Security answer doesn't match";
            }
        }
        templateFields.put("username", userName);
        templateFields.put("showModal", showModal);
        templateFields.put("message", message);
        templateFields.put("validUser", validUser);
        templateFields.put("securityQuestion", securityQuestion);
        templateFields.put("reset", reset);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}