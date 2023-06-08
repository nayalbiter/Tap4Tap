package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.daos.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import org.apache.logging.log4j.*;

public class AdminHandler implements CommandHandler<Tap4tapServlet> {
    private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) throws PermissionDeniedException {
        String template = "admin.ftl";
        BreweryDAO breweryDAO = servlet.getBreweryDAD();
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        if(request.getSession(false) == null){
            throw new PermissionDeniedException("You need to log in as an admin");
        }
        HashMap<UUID, Brewery> breweryMapList = (HashMap<UUID, Brewery>)request.getSession().getAttribute("breweryMapList");

        String breweryId = request.getParameter("breweryId");
        String modal = request.getParameter("modal");
        String action = request.getParameter("action");
        String breweryToEditId = request.getParameter("breweryToEditId");
        String breweryToDeleteId = request.getParameter("breweryToDeleteId");

        UUID breweryID = null;
        Brewery brewery;

        logger.debug("getting brewery id {}", breweryId);
        logger.debug("getting modal {}", modal);
        if(breweryId != null && modal == null){
            breweryID = UUID.fromString(breweryId);
            brewery = breweryDAO.retrieveByID(breweryID);
            Brewery breweryAdded = breweryMapList.put(breweryID, brewery);
            logger.debug("adding brewery {} to the list to edit", breweryAdded);
        }
        boolean edit = false;
        boolean delete = false;
        Brewery chosenBrewery = null;

        //for edit
        if(breweryId != null && modal != null){
            if(breweryId != null){
                breweryID = UUID.fromString(breweryId);
                chosenBrewery = breweryDAO.retrieveByID(breweryID);
                logger.debug("brewery: {}", chosenBrewery);
            }
            if(modal.equals("edit"))
                {logger.debug("check modal {}", modal);
                edit = true;}
            else if(modal.equals("delete"))
                delete = true;
        }
        boolean inserted = false;
        boolean updated = false;
        boolean deleted = false;
        String message = null;
        boolean missingField = false;
        boolean validCountry = false;
        // getting values from form
        String breweryName = null;
        try{breweryName = request.getParameter("breweryName");}
        catch(NullPointerException e){
            breweryName = null;
            logger.error("brewery Name parameter hasn't showed up yet");
        }
        String breweryType = null;
        String breweryAddress = null;
        String breweryCity = null;
        String breweryState = null;
        String breweryZipCode = null;
        String breweryCountry = null;
        String breweryPhone= null;
        String breweryWebsite= null;
        String breweryLongitude = null;
        String breweryLatitude = null;

        if(breweryName != null){
            breweryName = breweryName.strip();
            breweryType = request.getParameter("breweryType").strip();
            breweryAddress = request.getParameter("breweryAddress").strip();
            breweryCity = request.getParameter("breweryCity").strip();
            breweryState = request.getParameter("breweryState").strip();
            breweryZipCode = request.getParameter("breweryZipCode").strip();
            breweryCountry = request.getParameter("breweryCountry").strip();
            breweryPhone = request.getParameter("breweryPhone").strip();
            breweryWebsite = request.getParameter("breweryWebsite").strip();
            breweryLongitude = request.getParameter("breweryLongitude").strip();
            breweryLatitude = request.getParameter("breweryLatitude").strip();
        }
        logger.debug("check action {}", action);

        boolean validLongitudeLatitude = true;
        if(action != null && action.equals("edit")){
            UUID breweryToEditID = UUID.fromString(breweryToEditId);
            Brewery breweryToEdit = breweryDAO.retrieveByID(breweryToEditID);
            logger.debug("brewery retrieved {}", breweryToEdit);
            if(breweryName.isEmpty())
                breweryName = breweryToEdit.getName();
            logger.debug("brewery type {}", breweryType);
            if(breweryType.isEmpty()){
                logger.debug("breweryType {}", breweryType);
                breweryType = breweryToEdit.getBreweryType();
            }
            if(breweryAddress.isEmpty())
                breweryAddress = breweryToEdit.getAddress1();
            if(breweryCity.isEmpty())
                breweryCity = breweryToEdit.getCity();
            if(breweryState.isEmpty())
                breweryState = breweryToEdit.getStateProvince();
            if(breweryZipCode.isEmpty())
                breweryZipCode = breweryToEdit.getPostalCode();
            if(breweryCountry.isEmpty())
                breweryCountry = breweryToEdit.getCountry();
            if(breweryPhone.isEmpty())
                breweryPhone = breweryToEdit.getPhone();
            if(breweryWebsite.isEmpty())
                breweryWebsite = breweryToEdit.getWebsiteUrl();
            if(breweryLongitude.isEmpty())
                breweryLongitude = Double.toString(breweryToEdit.getLongitude());
            if(breweryLatitude.isEmpty())
                breweryLatitude = Double.toString(breweryToEdit.getLatitude());

            if(breweryCountry.equalsIgnoreCase("united states") || breweryCountry.equalsIgnoreCase("south korea") || breweryCountry.equalsIgnoreCase("portugal") || breweryCountry.equalsIgnoreCase("ireland") || breweryCountry.equals("england") || breweryCountry.equals("france") || breweryCountry.equalsIgnoreCase("poland") || breweryCountry.equalsIgnoreCase("scotland") || breweryCountry.equalsIgnoreCase("isle of man") || breweryCountry.equalsIgnoreCase("austria")){
                logger.debug("validating country :{}", breweryCountry);
                validCountry = true;
            }
            validLongitudeLatitude = true;
            double longitude = 0;
            double latitude = 0;
            try{longitude = Double.parseDouble(breweryLongitude);
            }
            catch(NumberFormatException e){ validLongitudeLatitude = false;
            }

            try{latitude = Double.parseDouble(breweryLatitude);
            }
            catch(NumberFormatException e){ validLongitudeLatitude = false;
            }
            if(validCountry && validLongitudeLatitude){
                Brewery breweryToUpdate = new Brewery(breweryToEditID, breweryName, breweryType, breweryAddress, null, null, breweryCity, breweryState, breweryZipCode, breweryCountry, breweryWebsite, breweryPhone, longitude, latitude);
                updated = breweryDAO.update(breweryToUpdate);
                breweryMapList.replace(breweryToEditID, breweryToUpdate);
            }

        }
        else if(action != null && action.equals("delete")){
            UUID breweryToDeleteID = UUID.fromString(breweryToDeleteId);
            brewery = breweryDAO.retrieveByID(breweryToDeleteID);
            logger.debug("about to remove brewery {}", brewery);
            Brewery removed = breweryMapList.remove(breweryToDeleteID);
            logger.debug("brewery is removed = {}", removed);
            int deletedRowCount = breweryDAO.delete(breweryToDeleteID);
            logger.debug("deleted row count = {}", deletedRowCount);
            if(deletedRowCount == 1) deleted = true;
        }

        else if(breweryName != null){
            if(breweryName.isEmpty() || breweryType.isEmpty() || breweryAddress.isEmpty() || breweryCity.isEmpty() || breweryState.isEmpty() || breweryZipCode.isEmpty() || breweryCountry.isEmpty() || breweryPhone.isEmpty() || breweryWebsite.isEmpty() || breweryLongitude.isEmpty() || breweryLatitude.isEmpty()){
                logger.debug("require field message");
                missingField = true;
            }
            else if(breweryCountry.equalsIgnoreCase("united states") || breweryCountry.equalsIgnoreCase("south korea") || breweryCountry.equalsIgnoreCase("portugal") || breweryCountry.equalsIgnoreCase("ireland") || breweryCountry.equalsIgnoreCase("england") || breweryCountry.equalsIgnoreCase("france") || breweryCountry.equalsIgnoreCase("poland") || breweryCountry.equalsIgnoreCase("scotland") || breweryCountry.equalsIgnoreCase("isle of man") || breweryCountry.equalsIgnoreCase("austria")){
                logger.debug("validating country :{}", breweryCountry);
                validCountry = true;

                validLongitudeLatitude = true;
                //creating a new brewery
                UUID uuid=UUID.randomUUID();
                double longitude = 0;
                double latitude = 0;
                try{longitude = Double.parseDouble(breweryLongitude);}
                catch(NumberFormatException e){ validLongitudeLatitude = false;}
                try{latitude = Double.parseDouble(breweryLatitude);}
                catch(NumberFormatException e){ validLongitudeLatitude = false;}
                if(validLongitudeLatitude){
                    Brewery newBrewery = new Brewery(uuid, breweryName, breweryType, breweryAddress, null, null, breweryCity, breweryState, breweryZipCode, breweryCountry, breweryWebsite, breweryPhone,longitude, latitude);
                    logger.debug("new brewery {}", newBrewery);

                    inserted = breweryDAO.insert(newBrewery);
                }
            }
        }
        logger.debug("deleted variable = {}", deleted);
        logger.debug("validCountry variable = {}", validCountry);
        if(inserted){
            message = "Inserted successfully";
        }
        else if(missingField){
            message = "Required fields can't be empty";
            logger.debug("getting missing field message");
        }
        else if(updated){
            message = "Updated succesfully";
        }
        else if(deleted){
            message = "Deleted successfully";
        }
        else if(breweryName != null && !validCountry){
            message = "Not supporting country field. Contact developer";
        }
        else if(!validLongitudeLatitude){
            message = "Not valid longitude, and/or latitude";
        }
        else if(breweryName != null && (!inserted || !updated)){
            message = "Can't update/insert.<br>if trying to insert -> Check if address is not repeated by other brewery";
        }

        logger.debug("putting in the field");
        logger.debug("breweryList {}", breweryMapList);
        templateFields.put("edit", edit);
        templateFields.put("delete", delete);
        templateFields.put("breweryList", breweryMapList);
        templateFields.put("chosenBrewery", chosenBrewery);
        templateFields.put("message", message);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
