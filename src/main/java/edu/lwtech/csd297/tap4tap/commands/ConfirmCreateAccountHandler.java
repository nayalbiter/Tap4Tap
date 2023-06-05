package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import at.favre.lib.crypto.bcrypt.BCrypt;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.*;
import edu.lwtech.csd297.tap4tap.daos.*;

public class ConfirmCreateAccountHandler implements CommandHandler<Tap4tapServlet>{
    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet){
        String template = "confirmCreateAccount.ftl";
        String message = "";
        String username = request.getParameter("username").strip();;
        String displayName = request.getParameter("displayName").strip();
        String password = request.getParameter("password").strip();
        String confirmPassword = request.getParameter("confirmPassword").strip();
        String hashedPassword = "";

        if(password.equals(confirmPassword)){hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());}
        String securityQuestion = request.getParameter("securityQuestion").strip();
        String securityAnswer = request.getParameter("securityAnswer").strip();
        String hashedAnswer = BCrypt.withDefaults().hashToString(12, securityAnswer.toCharArray());
        String email = request.getParameter("email").strip();

        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        User newUser = null;
        if(username.isEmpty()|| displayName.isEmpty()|| password.isEmpty()||securityQuestion.isEmpty()|| securityAnswer.isEmpty()){
            message = "Required fields can't be empty. Try again.<br /><a href='?cmd=createAccount'>Create Account</a>";
        }
        else {

            if(!password.equals(confirmPassword)){
                message = "Password and confirm password don't match. Try again.<br /><a href='?cmd=createAccount'>Create Account</a>";
            }
            else{
                if(!email.isEmpty()){
                    newUser = new User(username, hashedPassword, securityQuestion, hashedAnswer, displayName, email);
                    }
                else{
                    newUser = new User(username, hashedPassword, securityQuestion, hashedAnswer, displayName);
                }
                UserDAO userDAO =servlet.getUserDAO();
                boolean inserted = userDAO.insert(newUser);
                if(inserted){
                    message = "Account created successfully!<br /><a href='?cmd=showLogin'>Login</a>";
                }
                if(!inserted){
                    message = "Account create unsuccessfully. Try again.<br /><a href='?cmd=createAccount'>Create Account</a>";
                }
            }
        }
        templateFields.put("message",message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
