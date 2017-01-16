package main;

import dataSets.UserDataSet;
import database.DataBaseService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.FishingServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Демьян on 09.01.2017.
 */
public class Main{
    public static void main(String[] args) throws Exception{

        DataBaseService dataBaseService = new DataBaseService();

        Server server = new Server(8080);
        Map<String,UserDataSet> usersMap = new ConcurrentHashMap<>();

        SignInServlet signInServlet = new SignInServlet(usersMap, dataBaseService);
        SignUpServlet signUpServlet = new SignUpServlet(usersMap, dataBaseService);
        FishingServlet fishingServlet = new FishingServlet(usersMap, dataBaseService);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("public_html");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signInServlet),"/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(fishingServlet), "/fishing");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler,context});
        server.setHandler(handlers);

        server.start();
        server.join();

    }
}
