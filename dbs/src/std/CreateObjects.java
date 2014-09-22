package std;

import java.util.Scanner;
import model.data.Dealer;
import model.data.Store;
import dbs.Dbs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import model.data.Picture;
import model.data.Product;
import model.data.Tag;

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
        System.out.println("7   Picture");

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
                case "7":
                    createPicture(scan, dbs);
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
        String pictureId = "";
        ArrayList<Tag> tags = new ArrayList<>();

        System.out.println("======== Create a product ================================");
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

            ok = false;

            while (!ok) {
                ok = true;
                System.out.println("Write 'null' if there is no picture");
                System.out.print("Picture Id:" + pictureId + " ");
                pictureId = scan.nextLine();
                if (pictureId.equalsIgnoreCase("back")) {
                    break;
                }

                if (pictureId.equalsIgnoreCase("back")) {
                    break;
                } else if (!pictureId.equalsIgnoreCase("null")) {
                    Picture p = dbs.getPictureRegister().get(Integer.valueOf(pictureId));
                    if (p == null) {
                        System.out.println("The picture was not found, try again...");
                        ok = false;
                    }
                }
            }

            while (true) {
                String oks;
                System.out.print("Do you want to add a tag?[y/n]:");
                oks = scan.nextLine();
                if (oks.equalsIgnoreCase("y")) {

                    System.out.print("Tag Id: ");
                    String tagId = scan.nextLine();
                    Tag t = dbs.getTagRegister().get(Integer.valueOf(tagId));
                    if (t != null) {
                        tags.add(t);
                        System.out.println("Tag added");
                    } else {
                        System.out.println("Did not find tag. try again...");
                    }
                } else {
                    break;
                }
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Price: " + price);
            if (!parentStoreId.equalsIgnoreCase("null")) {
                System.out.println("Store Id: " + parentStoreId);
            }
            System.out.println("Dealer Id: " + parentDealerId);
            if (!pictureId.equalsIgnoreCase("null")) {
                System.out.println("Picture Id: " + pictureId);
            }
            System.out.println("Tags: ");
            for (Tag t : tags) {
                System.out.println("   " + t.toString());
            }

            ok = false;
            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        Picture p = null;
                        if (!pictureId.equalsIgnoreCase("null")) {
                            p = dbs.getPictureRegister().get(Integer.valueOf(pictureId));
                        }
                        if (!parentStoreId.equalsIgnoreCase("null")) {
                            Store s = dbs.getStoreRegister().get(Integer.valueOf(parentStoreId));
                            dbs.getProductRegister().create(name, description, p, Double.valueOf(price), s, tags);
                        } else {
                            Dealer d = dbs.getDealerRegister().get(Integer.valueOf(parentDealerId));
                            dbs.getProductRegister().create(name, description, p, Double.valueOf(price), d, tags);
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
        boolean finish = false;
        String productId = "";
        String price = "";
        String start = "";
        String end = "";
        String publish = "";

        System.out.println("======== Create a sale ================================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("- You can not choose a picture in this mode");
        System.out.println("---------------------------------------------------------");
        while (!finish) {
            boolean ok = false;
            while (!ok) {
                ok = true;
                System.out.print("Product Id:" + productId + " ");
                productId = scan.nextLine();
                if (productId.equalsIgnoreCase("back")) {
                    break;
                } else {
                    Product p = dbs.getProductRegister().get(Integer.valueOf(productId));
                    if (p == null) {
                        System.out.println("The product was not found, try again...");
                        ok = false;
                    }
                }
            }

            System.out.print("New price:" + price + " ");
            price = scan.nextLine();
            if (price.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Start date [dd:mm:yyyy:HH:MM]:" + start + " ");
            start = scan.nextLine();
            if (start.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("End date [dd:mm:yyyy:HH:MM]:" + end + " ");
            end = scan.nextLine();
            if (end.equalsIgnoreCase("back")) {
                break;
            }

            System.out.print("Publish date [dd:mm:yyyy:HH:MM]:" + publish + " ");
            publish = scan.nextLine();
            if (publish.equalsIgnoreCase("back")) {
                break;
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Product Id: " + productId);
            System.out.println("New price: " + price);
            System.out.println("Start date: " + start);
            System.out.println("End date: " + end);
            System.out.println("Publish date: " + publish);

            ok = false;
            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        String[] startSplit = start.split(":");
                        int startDay = Integer.valueOf(startSplit[0]);
                        int startMonth = Integer.valueOf(startSplit[1]);
                        int startYear = Integer.valueOf(startSplit[2]);
                        int startHour = Integer.valueOf(startSplit[3]);
                        int startMinute = Integer.valueOf(startSplit[4]);
                        Date startDate = new Date(startYear, startMonth, startDay, startHour, startMinute);

                        String[] endSplit = end.split(":");
                        int endDay = Integer.valueOf(endSplit[0]);
                        int endMonth = Integer.valueOf(endSplit[1]);
                        int endYear = Integer.valueOf(endSplit[2]);
                        int endHour = Integer.valueOf(endSplit[3]);
                        int endMinute = Integer.valueOf(endSplit[4]);
                        Date endDate = new Date(endYear, endMonth, endDay, endHour, endMinute);

                        String[] publishSplit = publish.split(":");
                        int publishDay = Integer.valueOf(publishSplit[0]);
                        int publishMonth = Integer.valueOf(publishSplit[1]);
                        int publishYear = Integer.valueOf(publishSplit[2]);
                        int publishHour = Integer.valueOf(publishSplit[3]);
                        int publishMinute = Integer.valueOf(publishSplit[4]);
                        Date publishDate = new Date(publishYear, publishMonth, publishDay, publishHour, publishMinute);

                        Product p = dbs.getProductRegister().get(Integer.valueOf(productId));
                        
                        dbs.getSaleRegister().create(p, Double.valueOf(price), startDate, endDate, publishDate);
                        System.out.println("Sale created");
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

    private static void createPicture(Scanner scan, Dbs dbs) {
        boolean finish = false;
        String name = "";
        String path = "";

        System.out.println("======== Create a picture ===============================");
        System.out.println("You can at any time write 'back' to get back to main menu");
        System.out.println("---------------------------------------------------------");
        while (!finish) {
            System.out.print("Name:" + name + " ");
            name = scan.nextLine();
            if (name.equalsIgnoreCase("back")) {
                break;
            }

            boolean ok = false;
            while (!ok) {
                System.out.print("Path:" + path + " ");
                path = scan.nextLine();
                if (name.equalsIgnoreCase("back")) {
                    break;
                }
                File f = new File(path);
                try {
                    FileInputStream in = new FileInputStream(f);
                    ok = true;
                } catch (FileNotFoundException ex) {
                    System.out.println("The picture was not found, try again");
                }
            }

            System.out.println("");
            System.out.println("Is this right?");
            System.out.println("Name: " + name);
            System.out.println("Path: " + path);

            ok = false;

            while (!ok) {
                System.out.print("[y/n]: ");
                String answer = scan.nextLine();
                switch (answer.toLowerCase()) {
                    case "y":
                        dbs.getPictureRegister().create(name, path);
                        System.out.println("Picture created");
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
