package longbottom.chat;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import longbottom.DAO.*;


@WebSocket
public class ChatWebSocketHandler {

    private String sender;
    private String message;

    // Integer represents the project ID of the project

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception{

        //if there are no more users in the chat room delete the chat room
        int projectId = Integer.parseInt(user.getUpgradeRequest().getParameterMap().get("projectId").get(0));
        int userId = Integer.parseInt(user.getUpgradeRequest().getParameterMap().get("username").get(1));

        if (Chat.chatMap.containsKey(projectId)){

        }
        else{

        }

    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason){
    }


    @OnWebSocketMessage
    public void onMessage(Session user, String message){

    }
}
