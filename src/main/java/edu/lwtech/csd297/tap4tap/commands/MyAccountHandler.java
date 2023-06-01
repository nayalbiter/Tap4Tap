package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import org.apache.logging.log4j.*;

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
        String breweryId = request.getParameter("breweryId");
        List<Brewery> breweryList = new ArrayList<>();
        String message = "";
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
        templateFields.put("breweryList", breweryList);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
