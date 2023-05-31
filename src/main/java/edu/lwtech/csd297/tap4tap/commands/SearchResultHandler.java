package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import org.apache.logging.log4j.*;

import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.SearchParameter;

//handle search result page
public class SearchResultHandler implements CommandHandler<Tap4tapServlet> {
    private static final Logger logger = LogManager.getLogger(SearchResultHandler.class.getName());

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) throws UserInputException {
        String template = "searchResult.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        List<SearchParameter> params = new ArrayList<SearchParameter>();
        //getting country information
        String country = request.getParameter("country");
        if ( country == null || country.trim().equals("")) {
            throw new UserInputException("The country is invalid");
        }
        params.add(new SearchParameter("country", country, true));

        //getting brewery information
        String breweryName = request.getParameter("breweryName");
        if (breweryName != null && !breweryName.isEmpty()) {
            params.add(new SearchParameter("name", breweryName, false));
        }

        //getting state information
        String state = request.getParameter("stateProvince");
        if (state != null && !state.isEmpty()){
            params.add(new SearchParameter("state_province", state, false));
        }

        //getting city information
        String city = request.getParameter("city");
        if (city != null && !city.isEmpty()){
            params.add(new SearchParameter("city", city, false));
        }

        //getting zipcode information
        String zipString = request.getParameter("zipCode");
        if (zipString != null && !zipString.isEmpty()){
            params.add(new SearchParameter("postal_code", zipString, false));
        }
        templateFields.put("breweries", servlet.getBreweryDAD().search(params));


        String html = CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
        return html;
    }
}
