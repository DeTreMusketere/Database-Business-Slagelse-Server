/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebServices;

import java.io.Serializable;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.data.Dealer;
import model.data.Picture;
import model.data.Sale;

/**
 *
 * @author PK
 */
@WebService(serviceName = "ClientWebService")
public class ClientWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello() {
        return "¨< MIMMIMIMIMIMMIMIMIMMMMIMMIMIIMIMIMI";
    }

    /**
     * Checks if the device is currently up to date with the latest sales
     * returns true if it is, false if it is behind. 
     */
    @WebMethod(operationName = "isUpToDate")
    public boolean isUpToDate() {
        //TODO write your implementation code here:
        return false;
    }

    /**
     * Returns a byte[] of an arraylist with arraylists of everything
     * @return 
     */
    @WebMethod(operationName = "getEverything")
    public String getEverything() {
        //TODO write your implementation code here:
        return null;
    }
    
    
                      
                
}
