/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiREST;

/**
 *
 * @author Marcel
 */
public class Comms {

    /* In the ApplicationConfig file inside the entity.service package
   * there is this annotation :
   *
   * @javax.ws.rs.ApplicationPath("webresources")
   * 
     */
    private static String PATH = "localhost:32185/upperCaseRESTJSWebSocket";

    public static String SERVER_REST = "http://" + PATH + "/webresources";

    public static String SERVER_WEBSOCKET = "ws://" + PATH + "/ws";

}
