package longbottom.chat;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;

public class ChatWebSocketHandler {

    private String sender;
    private String message;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception{

    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason){
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message){
    }
}
