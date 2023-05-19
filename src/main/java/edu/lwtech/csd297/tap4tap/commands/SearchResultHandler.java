package edu.lwtech.csd297.tap4tap.commands;

import java.util.*;
import org.apache.logging.log4j.*;

import javax.servlet.http.*;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import edu.lwtech.csd297.tap4tap.pojos.SearchParameter;

//handle search result page
public class SearchResultHandler implements CommandHandler<Tap4tapServlet> {
    private static final Logger logger = LogManager.getLogger(SearchResultHandler.class.getName());

    //pagination
    private static String makePageLink(HttpServletRequest request, int page) {
        Map<String, String> newParams = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            if (!entry.getKey().equals("page")) {
                newParams.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        newParams.put("page", "" + page);

        String url = "";
        for (Map.Entry<String, String> entry : newParams.entrySet()) {
            if (!url.isEmpty()) {
                url += "&";
            }
            url += entry.getKey() + "=" + entry.getValue();
        }
        return url;
    }

    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet) throws UserInputException {
        String template = "searchResult.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }
        int limit = 20;
        int offset = (page - 1) * limit;;
        String prevPageLink = page > 1 ? makePageLink(request, page - 1) : "this link is broken";
        String nextPageLink = makePageLink(request, page + 1);

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

        // ---- TODO: duplicate same as above for other fields

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

        templateFields.put("allBreweries", servlet.getBreweryDAD().search(new SearchParameter[]{}, limit, offset));

        templateFields.put("prevPageLink", prevPageLink);
        templateFields.put("nextPageLink", nextPageLink);

        String html = CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
        // logger.error("Returning page: " + html);
        return html;
    }
}
