package servlets;

import dataSets.UserDataSet;
import database.DataBaseService;
import fishing.FishingWebSocket;
import org.eclipse.jetty.websocket.servlet.*;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Демьян on 09.01.2017.
 */
@WebServlet(name = "FishingServlet", urlPatterns = {"/fishing"})
public class FishingServlet extends WebSocketServlet {
    DataBaseService dataBaseService;
    Map<String,UserDataSet> usersMap;
    private final static int LOGOUT_TIME = 10 * 60 * 1000;


    public FishingServlet(Map<String, UserDataSet> usersMap, DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
        this.usersMap = usersMap;
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator((req,resp)-> new FishingWebSocket(dataBaseService, getUser(req)));
    }

    private UserDataSet getUser(ServletUpgradeRequest req){
        String id = req.getSession().getId();
        UserDataSet user = usersMap.get(id);
        usersMap.remove(id);
        return user;
    }
}
