package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;

//handle search result page
public class SearchResultHandler implements CommandHandler<Tap4tapServlet> {

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) throws UserInputException {
        String template = "searchResult.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        //getting country information
        String country = request.getParameter("country");
        if ( country == null || country.equals("") || country.trim().equals("")) {
            throw new UserInputException("The country is invalid");
        }
        templateFields.put("country", country);
        //getting brewery information
        String breweryName = request.getParameter("breweryName");
        templateFields.put("breweryName", breweryName);

        //getting state information
        String state = request.getParameter("stateProvince");
        templateFields.put("stateProvince", state);
        String city = request.getParameter("city");
        templateFields.put("city", city);

        //getting zipcode information
        String zipString = request.getParameter("zipCode");
        templateFields.put("zipCode", zipString);
        templateFields.put("allBreweries", servlet.getBreweryDAD().retrieveAll());
        templateFields.put("breweries", servlet.getBreweryDAD().searchByKeys(country, state, city, breweryName, zipString));
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
