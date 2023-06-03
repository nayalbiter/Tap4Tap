package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.daos.UserDAO;

import org.apache.logging.log4j.*;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MyAccountHandler implements CommandHandler<Tap4tapServlet> {
    private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet){
        String template = "myAccount.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("loggedInUser");
        int userID = user.getUserId();
        String changePasswordMessage = "";
        String securityAnswer = request.getParameter("securityAnswer");
        if(securityAnswer != null){
            BCrypt.Result result = BCrypt.verifyer().verify(securityAnswer.toCharArray(), user.getHashedSecurityAnswer());
            if(result.verified){
                String newPassword = request.getParameter("newPassword").strip();
                String repeatNewPassword = request.getParameter("repeatNewPassword").strip();
                if(!newPassword.isEmpty() && newPassword.equals(repeatNewPassword)){
                    String newHashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
                    UserDAO userDAO = servlet.getUserDAO();
                    userDAO.setPassword(userID, newHashedPassword);
                    changePasswordMessage = "Password updated successfully";
                }
                else if(newPassword.isEmpty()){
                    changePasswordMessage = "New password field can't be empty";
                }
                else if(!newPassword.equals(repeatNewPassword)){
                    changePasswordMessage = "New password and repeate new password need to match";
                }
            }
            else{
                changePasswordMessage = "Security answer doesn't match with what we have";
            }
        }
        String message = "";
        String breweryId = request.getParameter("breweryId");
        List<Brewery> breweryList = new ArrayList<>();

        if(breweryId != null){
            UUID breweryID = UUID.fromString(breweryId);
            Favorite item = new Favorite(breweryID, userID);
            boolean insert = servlet.getFavoriteDAO().insert(item);
            if(insert){
                message = "Selected brewery inserted to your list";
            }
            else{
                message = "Error inserting to your list";
            }
        }
        List<Favorite> favoriteList = servlet.getFavoriteDAO().retrieveByUserId(userID);
        for(Favorite favorite: favoriteList){
            UUID breweryID = favorite.getBreweryId();
            Brewery brewery = servlet.getBreweryDAD().retrieveByID(breweryID);
            breweryList.add(brewery);
        }
        templateFields.put("changePasswordMessage", changePasswordMessage);
        templateFields.put("breweryList", breweryList);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
