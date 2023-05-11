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

        //putting country information
        String country = request.getParameter("country");
        if ( country == null || country.equals("") || country.trim().equals("")) {
            throw new UserInputException("The country is invalid");
        }
        //putting brewery information
        String breweryName;
        try {
            breweryName = request.getParameter("breweryName");
        } catch (Exception e) {
            breweryName = "";
        }
        templateFields.put("breweryName", breweryName);

        //putting state information
        String state;
        try{
            state = request.getParameter("stateProvince");
            if(state == ""){
                state = "New York";
            }
        } catch(Exception e){
            state = "New York";
        }
        templateFields.put("stateProvince", state);

        //putting city information
        String city;
        try{
            city = request.getParameter("city");
            if(city == ""){
                city = "New York";
            }
        } catch(Exception e){
            city = "New York";
        }
        templateFields.put("city", city);

        //putting zipcode information
        int zipCode = 0;
        try{
            String zipString = request.getParameter("zipCode");
            zipCode = Integer.parseInt(zipString);
        }catch(Exception e){
            zipCode = 10001;
        }
        templateFields.put("zipCode", zipCode);

        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
