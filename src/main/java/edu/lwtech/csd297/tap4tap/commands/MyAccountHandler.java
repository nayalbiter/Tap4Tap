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
        String userId = request.getParameter("user");
        int userID = Integer.parseInt(userId);
        String breweryId = request.getParameter("breweryId");
        Favorite item = new Favorite(1, breweryId, userID);
        boolean insert = servlet.getFavoriteDAO().insert(item);
        String message = "";
        if(insert){
            message = "Selected brewery inserted to your list";
        }
        else{
            message = "Error inserting to your list";
        }
        List<Favorite> favoriteList = servlet.getFavoriteDAO().retrieveByUserId(userID);
        List<Brewery> breweryList = new ArrayList<Brewery>();
        for(Favorite favorite: favoriteList){
            breweryId = favorite.getBreweryId();
            UUID breweryID = UUID.fromString(breweryId);
            Brewery brewery = servlet.getBreweryDAD().retrieveByID(breweryID);
            breweryList.add(brewery);
        }
        templateFields.put("breweryList", breweryList);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
