
package std;

import dbs.Dbs;
import java.util.Scanner;
import model.data.Tag;

/**
 *
 * @author Patrick
 */
public class ShowObjects {
    
    public static void show(Scanner scan, Dbs dbs) {
        System.out.println("What would you like to show?");

        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("1   Dealer");
        System.out.println("2   Store");
        System.out.println("3   User");
        System.out.println("4   Product");
        System.out.println("5   Sale");
        System.out.println("6   Tag");

        boolean ok = false;
        while (!ok) {
            System.out.print("[]: ");
            String command = scan.nextLine();
            switch (command.toLowerCase()) {
                case "1":
                    showDealer(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "2":
                    showStore(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "3":
                    showUser(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "4":
                    showProduct(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "5":
                    showSale(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "6":
                    showTag(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "back":
                    ok = true;
                    break;
                default:
                    System.out.println("I did not understand that...");
                    break;
            }
        }
    }
    
    private static void showDealer(Scanner scan, Dbs dbs) {
        System.out.println("Not implementet yet");
    }
    
    private static void showStore(Scanner scan, Dbs dbs) {
        System.out.println("Not implementet yet");
    }
    
    private static void showUser(Scanner scan, Dbs dbs) {
        System.out.println("Not implementet yet");
    }
    
    private static void showProduct(Scanner scan, Dbs dbs) {
        System.out.println("Not implementet yet");
    }
    
    private static void showSale(Scanner scan, Dbs dbs) {
        System.out.println("Not implementet yet");
    }
    
    private static void showTag(Scanner scan, Dbs dbs) {
        System.out.print("Tag ID: ");
        String input = scan.nextLine();
        Tag t = dbs.getTagRegister().get(Integer.valueOf(input));
        if(t == null) {
            System.out.println("Did not find tag, bye");
        } else {
            System.out.println("How would you like to show it?");
            System.out.print("[json/plain]: ");
            input = scan.nextLine();
            switch(input.toLowerCase()) {
                case "json":
                    System.out.println(t.toJSONObject().toString(2));
                    break;
                case "plain":
                    System.out.println("Not implementet yet");
                    break;
                default:
                    System.out.println("What?? bye");
                    break;
            }
        }
    }

}
