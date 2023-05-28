/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.lwtech.csd297.tap4tap.commands;

import edu.lwtech.csd297.tap4tap.Tap4tapServlet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author carme
 */
public class HTTPErrorHandler implements CommandHandler<Tap4tapServlet>{
    
    @Override
    public String handle(HttpServletRequest request, Tap4tapServlet servlet){
        String template = "404.ftl";
        Map<String, Object> templateFields = new HashMap<>();
        CommandUtils.getSessionVariables(request, templateFields);
        String errorCode = request.getParameter("errorCode");
        switch (errorCode) {
            case "404":
                templateFields.put("errorMessage", "Page Not Found");
                break;
            case "500":
                templateFields.put("errorMessage", "Try again Later");
                break;  
            case "400":
                templateFields.put("errorMessage", "This account already exists / You are already logged in");
                break;
            case "403":
                templateFields.put("errorMessage", "Forbidden");
                break;
            default:
                templateFields.put("errorMessage", "");
                
                
        }
        templateFields.put("errorCode", errorCode);
        return CommandUtils.mergeTemplate(template, templateFields, servlet.getFreeMarkerConfig());
    }
    
}
