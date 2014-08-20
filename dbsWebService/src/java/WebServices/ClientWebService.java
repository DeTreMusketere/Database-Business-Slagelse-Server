/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

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
     * Gets the current update number of the server
     *
     * @return current update number
     */
    @WebMethod(operationName = "isUpToDate")
    public int isUpToDate() {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Returns a byte[] of an arraylist with arraylists of everything Should be
     * used first time device connects to the server
     *
     * @return
     */
    @WebMethod(operationName = "getEverything")
    public byte[] getEverything() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Returns a byte[] of an arraylist with arraylists for every class, with
     * updated objects.
     *
     * @param updateNumber The current updateNumber of the device
     * @return byte[] of a 2 dimensional ArrayList holding all the updated
     * objects
     */
    @WebMethod(operationName = "getUpdated")
    public byte[] getUpdated(@WebParam(name = "updateNumber") int updateNumber) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "test")
    public ArrayList<String> test() {
        ArrayList<String> test = new ArrayList<>();
//        for(int i = 1; i <= 100000; i++){
//        test.add(i + " 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
//        }
        return test;
    }

}
