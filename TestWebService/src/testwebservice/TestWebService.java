package testwebservice;

import java.util.ArrayList;

/**
 *
 * @author PK
 */
public class TestWebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(hello());
        testShit();
    }

    private static String hello() {
        webservices.ClientWebService_Service service = new webservices.ClientWebService_Service();
        webservices.ClientWebService port = service.getClientWebServicePort();
        return port.hello();
    }
    
    private static void testShit(){
        webservices.ClientWebService_Service service = new webservices.ClientWebService_Service();
        webservices.ClientWebService port = service.getClientWebServicePort();
        ArrayList<String> bigTest = (ArrayList<String>) port.test();
        for(String s : bigTest){
            System.out.println(s);
        }
               
        
    }

}
