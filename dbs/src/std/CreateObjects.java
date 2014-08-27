
package std;

import java.util.Scanner;
import model.data.Dealer;
import model.data.Store;
import dbs.Dbs;

/**
 *
 * @author Patrick
 */
public class CreateObjects {
    
    public static void create(Scanner scan, Dbs dbs) {
        System.out.println("What would you like to create?");

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
                    createDealer(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "2":
                    createStore(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "3":
                    createUser(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "4":
                    createProduct(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "5":
                    createSale(scan, dbs);
                    System.out.println("");
                    ok = true;
                    break;
                case "6":
                    createTag(scan, dbs);
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

    private static void createDealer(Scanner scan, Dbs dbs) {
        boolean finish = false;
        String name = "";
        String description = "";
        String phone = "";

        System.out.println("======== Create a dealer ================================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("- You can not choose a picture in this mode");
        System.out.println("---------------------------------------------------------");
        while (!finish) {
            System.out.print("Name:" + name + " ");
            name = scan.nextLine();
            if (name.equalsIgnoreCase("back")) {
                break;
            }
            System.out.print("Description:" + description + " ");
            description = scan.nextLine();
            if (description.equalsIgnoreCase("back")) {
                break;
            }
            System.out.print("Phone:" + phone + " ");
            phone = scan.nextLine();
            if (phone.equalsIgnoreCase("back")) {
                break;
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Phone: " + phone);

            boolean ok = false;

            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        dbs.getDealerRegister().create(name, description, phone, null);
                        System.out.println("Dealer created");
                        finish = true;
                        ok = true;
                        break;
                    case "n":
                        System.out.println("OK then, lets try again");
                        System.out.println("");
                        ok = true;
                        break;
                    default:
                        System.out.println("That was a stupid answer, try again...");
                        System.out.println("");
                        break;
                }
            }
        }
    }

    private static void createStore(Scanner scan, Dbs dbs) {
        boolean finish = false;
        String name = "";
        String address = "";
        String phone = "";
        String parentDealerId = "";

        System.out.println("======== Create a store ================================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("- You can not choose a picture in this mode");
        System.out.println("---------------------------------------------------------");
        while (!finish) {
            System.out.print("Name:" + name + " ");
            name = scan.nextLine();
            if (name.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Address:" + address + " ");
            address = scan.nextLine();
            if (address.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Phone:" + phone + " ");
            phone = scan.nextLine();
            if (phone.equalsIgnoreCase("back")) {
                break;
            }

            boolean ok = false;

            while (!ok) {
                ok = true;
                System.out.print("Dealer ID:" + parentDealerId + " ");
                parentDealerId = scan.nextLine();
                if (parentDealerId.equalsIgnoreCase("back")) {
                    break;
                } else {
                    Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                    if (d == null) {
                        System.out.println("The dealer was not found, try again...");
                        ok = false;
                    }
                }
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
            System.out.println("Dealer ID: " + parentDealerId);

            ok = false;
            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                        dbs.getStoreRegister().create(name, address, phone, null, d);
                        System.out.println("Store created");
                        finish = true;
                        ok = true;
                        break;
                    case "n":
                        System.out.println("OK then, lets try again");
                        System.out.println("");
                        ok = true;
                        break;
                    default:
                        System.out.println("That was a stupid answer, try again...");
                        System.out.println("");
                        break;
                }
            }
        }
    }

    private static void createUser(Scanner scan, Dbs dbs) {
        boolean finish = false;
        String name = "";
        String username = "";
        String password = "";
        String email = "";
        String phone = "";
        String parentStoreId = "";
        String parentDealerId = "";

        System.out.println("======== Create a user ================================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("---------------------------------------------------------");
        while (!finish) {
            System.out.print("Name:" + name + " ");
            name = scan.nextLine();
            if (name.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Username:" + username + " ");
            username = scan.nextLine();
            if (username.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Password:" + password + " ");
            password = scan.nextLine();
            if (password.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Email:" + email + " ");
            email = scan.nextLine();
            if (email.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Phone:" + phone + " ");
            phone = scan.nextLine();
            if (phone.equalsIgnoreCase("back")) {
                break;
            }

            boolean ok = false;

            while (!ok) {
                ok = true;
                System.out.println("Write 'null' if there is no store");
                System.out.print("Store ID:" + parentStoreId + " ");
                parentStoreId = scan.nextLine();
                if (parentStoreId.equalsIgnoreCase("back")) {
                    break;
                } else if (!parentStoreId.equalsIgnoreCase("null")) {
                    Store s = dbs.getStoreRegister().get(Integer.valueOf(parentStoreId));
                    if (s == null) {
                        System.out.println("The store was not found, try again...");
                        ok = false;
                    } else {
                        parentDealerId = String.valueOf(s.getParent().getId());
                        System.out.println("The parentDealerId was automatically set from the parentStore");
                    }
                }
            }

            if (parentStoreId.equalsIgnoreCase("null")) {
                ok = false;

                while (!ok) {
                    ok = true;
                    System.out.println("Write 'null' if there is no dealer");
                    System.out.print("Dealer ID:" + parentDealerId + " ");
                    parentDealerId = scan.nextLine();
                    if (parentStoreId.equalsIgnoreCase("back")) {
                        break;
                    } else if (!parentDealerId.equalsIgnoreCase("null")) {
                        Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                        if (d == null) {
                            System.out.println("The dealer was not found, try again...");
                            ok = false;
                        }
                    }
                }
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
            if (!parentStoreId.equalsIgnoreCase("null")) {
                System.out.println("ParentStoreId: " + parentStoreId);
            }
            if (!parentDealerId.equalsIgnoreCase("null")) {
                System.out.println("ParentDealerId: " + parentDealerId);
            }

            ok = false;
            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        if (!parentStoreId.equalsIgnoreCase("null")) {
                            Store s = dbs.getStoreRegister().get(Integer.valueOf(parentStoreId));
                            dbs.getUserRegister().create(name, username, password, email, phone, s);
                        } else if (!parentDealerId.equalsIgnoreCase("null")) {
                            Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                            dbs.getUserRegister().create(name, username, password, email, phone, d);
                        } else {
                            dbs.getUserRegister().create(name, username, password, email, phone);
                        }
                        System.out.println("User created");
                        finish = true;
                        ok = true;
                        break;
                    case "n":
                        System.out.println("OK then, lets try again");
                        System.out.println("");
                        ok = true;
                        break;
                    default:
                        System.out.println("That was a stupid answer, try again...");
                        System.out.println("");
                        break;
                }
            }

        }
    }

    private static void createProduct(Scanner scan, Dbs dbs) {
        boolean finish = false;
        String name = "";
        String description = "";
        String price = "";
        String parentStoreId = "";
        String parentDealerId = "";
        
        System.out.println("======== Create a product ================================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("- You can not choose a picture in this mode");
        System.out.println("---------------------------------------------------------");
        
        while (!finish) {
            System.out.print("Name:" + name + " ");
            name = scan.nextLine();
            if (name.equalsIgnoreCase("back")) {
                break;
            }
            
            System.out.print("Description:" + description + " ");
            description = scan.nextLine();
            if (description.equalsIgnoreCase("back")) {
                break;
            }
            
            System.out.print("Price:" + price + " ");
            price = scan.nextLine();
            if (price.equalsIgnoreCase("back")) {
                break;
            }
            
            boolean ok = false;

            while (!ok) {
                ok = true;
                System.out.println("Write 'null' if there is no store");
                System.out.print("Store ID:" + parentStoreId + " ");
                parentStoreId = scan.nextLine();
                if (parentStoreId.equalsIgnoreCase("back")) {
                    break;
                } else if (!parentStoreId.equalsIgnoreCase("null")) {
                    Store s = dbs.getStoreRegister().get(Integer.valueOf(parentStoreId));
                    if (s == null) {
                        System.out.println("The store was not found, try again...");
                        ok = false;
                    } else {
                        parentDealerId = String.valueOf(s.getParent().getId());
                        System.out.println("The parentDealerId was automatically set from the parentStore");
                    }
                }
            }

            if (parentStoreId.equalsIgnoreCase("null")) {
                ok = false;

                while (!ok) {
                    ok = true;
                    System.out.println("Write 'null' if there is no dealer");
                    System.out.print("Dealer ID:" + parentDealerId + " ");
                    parentDealerId = scan.nextLine();
                    if (parentStoreId.equalsIgnoreCase("back")) {
                        break;
                    } else {
                        Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                        if (d == null) {
                            System.out.println("The dealer was not found, try again...");
                            ok = false;
                        }
                    }
                }
            }
            
            
            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Price: " + price);
            if(!parentStoreId.equalsIgnoreCase("null")) {
                System.out.println("Store Id: " + parentStoreId);
            }
            System.out.println("Dealer Id: " + parentDealerId);
            
            ok = false;
            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        if (!parentStoreId.equalsIgnoreCase("null")) {
                            Store s = dbs.getStoreRegister().get(Integer.valueOf(parentStoreId));
                            dbs.getProductRegister().create(name, description, null, Double.valueOf(price), s);
                        } else {
                            Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                            dbs.getProductRegister().create(name, description, null, Double.valueOf(price), d);
                        }
                        System.out.println("Product created");
                        finish = true;
                        ok = true;
                        break;
                    case "n":
                        System.out.println("OK then, lets try again");
                        System.out.println("");
                        ok = true;
                        break;
                    default:
                        System.out.println("That was a stupid answer, try again...");
                        System.out.println("");
                        break;
                }
            }
            
        }
    }

    private static void createSale(Scanner scan, Dbs dbs) {
        System.out.println("Not implemented yet");
    }

    private static void createTag(Scanner scan, Dbs dbs) {
        boolean finish = false;
        String name = "";
        String description = "";

        System.out.println("======== Create a tag ================================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("---------------------------------------------------------");
        while (!finish) {
            System.out.print("Name:" + name + " ");
            name = scan.nextLine();
            if (name.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Description:" + description + " ");
            description = scan.nextLine();
            if (description.equalsIgnoreCase("back")) {
                break;
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);

            boolean ok = false;

            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        dbs.getTagRegister().create(name, description);
                        System.out.println("Tag created");
                        finish = true;
                        ok = true;
                        break;
                    case "n":
                        System.out.println("OK then, lets try again");
                        System.out.println("");
                        ok = true;
                        break;
                    default:
                        System.out.println("That was a stupid answer, try again...");
                        System.out.println("");
                        break;
                }
            }
        }
    }

}
