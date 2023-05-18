package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.SearchParameter;

//handle search result page
public class SearchResultHandler implements CommandHandler<Tap4tapServlet> {

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) throws UserInputException {
        String template = "searchResult.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        int limit = 20;
        int offset = 0;
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            offset = page * limit;
        } catch (NumberFormatException e) {
            // Rely on default above.
        }

        List<SearchParameter> params = new ArrayList<SearchParameter>();

        //getting country information
        String country = request.getParameter("country");
        if ( country == null || country.trim().equals("")) {
            throw new UserInputException("The country is invalid");
        }
        params.add(new SearchParameter("country", country, true));
        templateFields.put("country", country);

        //getting brewery information
        String breweryName = request.getParameter("breweryName");
        if (breweryName != null && !breweryName.isEmpty()) {
            params.add(new SearchParameter("name", breweryName, false));
        }
        templateFields.put("breweryName", breweryName);

        //getting state information
        String state = request.getParameter("stateProvince");
        SearchParameter searchParamStateProvince= new SearchParameter("state_province", state, false);

        templateFields.put("stateProvince", state);
        String city = request.getParameter("city");
        SearchParameter searchParamCity= new SearchParameter("city", city, false);

        templateFields.put("city", city);

        //getting zipcode information
        String zipString = request.getParameter("zipCode");
        SearchParameter searchParamZipCode= new SearchParameter("zip_code", zipString, false);

        templateFields.put("zipCode", zipString);

        templateFields.put("breweries", servlet.getBreweryDAD().search(params.toArray(new SearchParameter[]{}), limit, offset));

        templateFields.put("allBreweries", servlet.getBreweryDAD().search(new SearchParameter[]{}, 5, 0));
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
}
