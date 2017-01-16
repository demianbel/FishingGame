package fishing;

import com.google.gson.Gson;
import dataSets.FishDataSet;
import dataSets.UserDataSet;
import database.DBException;
import database.DataBaseService;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.Map;

/**
 * Created by Демьян on 09.01.2017.
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class FishingWebSocket {
    private DataBaseService dataBaseService;
    private FishingService fishingService;
    private UserDataSet user;
    private Session session;
    private FishDataSet catchedFish;

    public FishingWebSocket(DataBaseService dataBaseService, UserDataSet user) {
        this.dataBaseService = dataBaseService;
        this.user = user;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        System.out.println("web socket opened");
        this.session = session;
        fishingService = new FishingService(dataBaseService);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        catchedFish = fishingService.getFish();
        user.addScore(catchedFish.getScore());

        Object[] message = new Object[2];
        message[0] = user;
        message[1] = catchedFish;

        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);
        sendString(jsonMessage);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        try {
            dataBaseService.saveUser(user);
        } catch (DBException e){ e.printStackTrace();}
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
