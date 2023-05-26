package edu.lwtech.csd297.tap4tap;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.commands.*;
import edu.lwtech.csd297.tap4tap.daos.*;
import edu.lwtech.csd297.tap4tap.utils.SQLUtils;
import freemarker.template.*;

@WebServlet(name = "tap4tap", urlPatterns = {"/servlet"}, loadOnStartup = 0)
public class Tap4tapServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Tap4tapServlet.class);

    private static Configuration freeMarkerConfig = null;
    private static final Map<String, CommandHandler<Tap4tapServlet>> supportedCommands = new HashMap<>();

    private UserDAO userDAO = null;
    private BreweryDAO breweryDAO = null;
    private Connection conn = null;
    @Override
    public void init(ServletConfig config) throws ServletException {

        logger.warn("");
        logger.warn("========================================================");
        logger.warn("   tap4tap init() started");
        logger.warn("      http://http://localhost:8080/tap4tap/servlet");
        logger.warn("========================================================");
        logger.warn("");

        String resourcesDir = config.getServletContext().getRealPath("/WEB-INF/classes");
        logger.info("resourcesDir = {}", resourcesDir);

        logger.info("Initializing FreeMarker...");
        String templateDir = config.getServletContext().getRealPath("/WEB-INF/classes/templates");
        logger.debug("templatesDir = {}", templateDir);
        freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_0);
        try {
            freeMarkerConfig.setDirectoryForTemplateLoading(new File(templateDir));
        } catch (IOException e) {
            throw new UnavailableException("Template directory not found");
        }
        logger.info("Successfully initialized FreeMarker.");

        supportedCommands.put("home", new HomeHandler());
        supportedCommands.put("login", new LoginHandler());
        supportedCommands.put("logout", new LogoutHandler());
        supportedCommands.put("showLogin", new ShowLoginHandler());
        supportedCommands.put("searchResult", new SearchResultHandler());
        supportedCommands.put("createAccount", new createAccountHandler());
        supportedCommands.put("forgotPassword", new forgotPasswordHandler());
        supportedCommands.put("admin", new AdminHandler());
        //to connect to database

        boolean useSqlDao = true;
        // Get connection parameters
        String hostname = "localhost";
        String port = "3306";
        String database = "tap4tap";
        String username = System.getProperty("username");
        String password = System.getProperty("tap4tap_password");


        if (useSqlDao) {
            String initParams = "jdbc:mariadb://" + hostname + ":" + port + "/" + database + "?user=" + username + "&password=" + password;
            conn = SQLUtils.connect(initParams);
            if (conn == null) {
                throw new UnavailableException("Failed to connect to SQL database");
            }
            breweryDAO = new BrewerySqlDAO(conn);
            userDAO = new UserSqlDAO(conn);
        } else {
            logger.info("using memory DAOs");
            breweryDAO = new BreweryMemoryDAO();
            userDAO = new UserMemoryDAO();
    }

        logger.info("Initializing the DAOs...");


        logger.info("Successfully initialized the DAOs!");

        logger.warn("");
        logger.warn("Servlet initialization complete!");
        logger.warn("");
    }

    @Override
    public void destroy() {
        if (conn != null) {
            SQLUtils.disconnect(conn);
        }
        // TODO: Terminate other DAOs here
        logger.warn("-----------------------------------------");
        logger.warn("  tap4tap destroy() completed!");
        logger.warn("-----------------------------------------");
        logger.warn(" ");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        String logInfo = getLogInfo(request);

        try {
            // Get the cmd parameter from the URI (default to 'home')
            String cmd = getCommand(request, "home");

            // Handle "health" commands here so they don't get logged
            if (cmd.equals("health")) {
                response.sendError(HttpServletResponse.SC_OK, "OK");
                return;
            }

            logger.debug("IN - {}", logInfo);

            // Find the CommandHandler for cmd
            CommandHandler<Tap4tapServlet> command = supportedCommands.get(cmd);
            if (command == null) {
                logger.info("Unrecognized command received. cmd: {}", cmd);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Handle the command request
            String output;
            try {
                output = command.handle(request, this);
            } catch (UserInputException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.toString());
                return;
            }
            if (output == null || output.isBlank()) {
                logger.info("Null/Empty response returned when command was handled. cmd: {}", cmd);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            // Send the command's output to the user (using try-with-resources)
            try (PrintWriter out = response.getWriter()) {
                out.println(output);
            }
            } catch (IOException e) {
                logger.error("Unexpected I/O exception: ", e);
            } catch (RuntimeException e) {
                logger.error("Unexpected runtime exception: ", e);
                try {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected server error.");
                } catch (IOException ex) {
                    logger.error("Unable to send 500 response code.", ex);
                }
            }
        long time = System.currentTimeMillis() - startTime;
        logger.info("OUT- {} {}ms", logInfo, time);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    public Configuration getFreeMarkerConfig() {
        return freeMarkerConfig;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public BreweryDAO getBreweryDAD(){
        return breweryDAO;
    }

    // TODO: Add other DAO getters here

    // =================================================================

    private static String getLogInfo(HttpServletRequest request) {
        StringBuilder logInfo = new StringBuilder();
        logInfo.append(request.getRemoteAddr());
        logInfo.append(" ");
        logInfo.append(request.getMethod());
        logInfo.append(" ");
        logInfo.append(request.getRequestURI());
        logInfo.append(getSanitizedQueryString(request));
        return logInfo.toString();
    }

    private static String getCommand(HttpServletRequest request, String defaultCmd) {
        String cmd = request.getParameter("cmd");
        if (cmd == null)
            cmd = defaultCmd;
        return cmd;
    }

    private static String getSanitizedQueryString(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (queryString == null)
            return "";

        try {
            queryString = URLDecoder.decode(queryString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);                         // Should never happen
        }
        queryString = "?" + sanitizedString(queryString);
        return queryString;
    }

    private static String sanitizedString(String s) {
        return s.replaceAll("[\n|\t]", "_");
    }
}
