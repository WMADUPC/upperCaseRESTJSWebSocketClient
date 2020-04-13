/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSocketService;

import apiREST.Comms;
import com.google.gson.Gson;

import entity.Uppercase;
import java.net.URI;

import java.util.Arrays;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import uppercaserestjswebsocketclient.UpperCaseRESTJSWebsocketClient;

/**
 *
 * @author Marcel
 */
@ClientEndpoint
public class WebSocketClient {

    static Session session;
    static UpperCaseRESTJSWebsocketClient gui;

    public static void newInstance() {
        try {
            WebSocketContainer container = 
                    ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(WebSocketClient.class,
                    URI.create(Comms.SERVER_WEBSOCKET));
            System.out.println("instance created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setGUI(UpperCaseRESTJSWebsocketClient w) {
        gui = w;
    }

    public static void close() {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Is called upon receiving a message from the server
    @OnMessage
    public void onMessage(String message) {
        //System.out.println("Reference received " + message);

        Gson gson = new Gson();

        /*
     * Cannot use List<Uppercase> because JSON does not work well
     * with Collection, so ve use an array Uppercase[] and it works    
         */
        Uppercase[] receivedList;
        receivedList = gson.fromJson(message, Uppercase[].class);

        if (receivedList.length > 0) {
            for (Uppercase c : receivedList) {
                System.out.println("onMessage : " + c);
            }
            // Show sentences 
            gui.updateSentences(Arrays.asList(receivedList));

        }

    }

}
