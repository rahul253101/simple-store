import java.util.*;

class app{

    public static void main(String[] args){

        Map<User,Inventory>instanceDb = new HashMap<User,Inventory>();
        User currentUser = null;
        Map<Inventory.Tuple,Seller> productOwner = new HashMap<>();

        while(true){
            System.out.println("press 1 to Resigiter into the application \n" +
                    "press 2 to Login into the application \n" +
                    "else to break");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            if(input.equals("1")){

                System.out.println("press 1 to Resigiter as seller \n" +
                            "press 2 to Resigiter as buyer \n"+
                             "press 3 to get all the list all the sellers \n" +
                             "press 4 to get all the list all the buyers");

                Scanner innerScanner = new Scanner(System.in);
                String x = innerScanner.nextLine();

                if(x.equals("1")){

                    System.out.println("enter your name email password");
                    Scanner rinnerScanner = new Scanner(System.in);
                    String[] strs = new String[3];
                    strs = rinnerScanner.nextLine().split(" ");
                    Seller seller = new Seller(strs[0],strs[1],strs[2]);
                    System.out.println(seller);
                    Profile.addProfile(seller);
                    // database.put(new Profile.Tuple(data.id,'S'), seller);
                    continue;


                }
                else if(x.equals("2")){

                    System.out.println("enter your name email password");
                    Scanner linnerScanner = new Scanner(System.in);
                    String[] strs = new String[3];
                    strs = linnerScanner.nextLine().split(" ");
                    Buyer buyer = new Buyer(strs[0],strs[1],strs[2]);
                    Profile.addProfile(buyer);
                    // database.put(new Profile.Tuple(data.id,'B'), buyer);
                    continue;

                }
                else if(x.equals("3")){
                    System.out.println("getting you the list..");
                    Profile.getAllSellers();
                }

                else if(x.equals("4")){
                    System.out.println("getting you the list..");
                    Profile.getAllBuyers();
                }
                else{
                    break;
                }

            }

            else if(input.equals("2")){
                System.out.println("logging..in");

                while(true){

                    System.out.println("press 1 to login as a seller \n" +
                                    "press 2 to login as buyer");

                    Scanner newScanner = new Scanner(System.in);

                    String entered = newScanner.nextLine();

                    if (entered.equals("1")){

                        Scanner newScanner2 = new Scanner(System.in);
                        String[] entered2 = new String[2];
                        entered2 = newScanner2.nextLine().split(" ");

                        Profile.Tuple nested = new Profile.Tuple(entered2[0],'S');
                        System.out.println(nested);
                        if(Profile.users.containsKey(nested)){
                            System.out.println(Profile.users.get(nested));
                            if(Profile.users.get(nested).password.equals(entered2[1])){
                                System.out.println("hello " + Profile.users.get(nested).name);
                                currentUser = Profile.users.get(nested);
                                if(!instanceDb.containsKey(Profile.users.get(nested))){
                                    Inventory instance4 = new Inventory();
                                    instanceDb.put(Profile.users.get(nested),instance4);
                                }

                                Inventory myInstance = instanceDb.get(Profile.users.get(nested));

                                myInstance.listInventory();

                                // Inventory instance1 = new Inventory();

                                while(true){

                                    System.out.println("press 1 to add items into your inventory \n"+
                                "else anything exit");

                                    Scanner scanner4 = new Scanner(System.in);

                                    String input3 = scanner4.nextLine();

                                    if (input3.equals("1")){
                                        System.out.println("enter the product name and the quantity..");

                                        Scanner scanner5 = new Scanner(System.in);

                                        String[] input4 = new String[2];
                                        input4 = scanner5.nextLine().split(" ");

                                        String hash =  Profile.users.get(nested).id;
                                        productOwner.put(new Inventory.Tuple(input4[0],hash), (Seller)Profile.users.get(nested));
                                        myInstance.addItem(input4[0],hash,Integer.parseInt(input4[1]));

                                        continue;


                                    }else{

                                        myInstance.listInventory();
                                        break;
                                    }

                                }



                            }else{
                                System.out.println("please check your inputs!!");
                                continue;
                            }

                        }else{
                            break;
                        }

                    }

                    else if(entered.equals("2")){

                        Scanner newScanner3 = new Scanner(System.in);
                        String[] entered3 = new String[2];
                        entered3 = newScanner3.nextLine().split(" ");

                        Profile.Tuple nested2 = new Profile.Tuple(entered3[0],'B');
                        System.out.println(nested2);

                        if(Profile.users.containsKey(nested2)){

                            if(Profile.users.get(nested2).password.equals(entered3[1])){
                                currentUser = Profile.users.get(nested2);
                                System.out.println("hello " + Profile.users.get(nested2).name);
                                System.out.println("here are the list of items we have!!");

                                for(Inventory res: instanceDb.values()){
                                    if (res != null){
                                        res.listInventory();
                                    }

                                }

                                while(true){
                                    System.out.println("press 1 to buy \n" + "else to break" );

                                    Scanner scanner0 = new Scanner(System.in);
                                    String ans = scanner0.nextLine();

                                    Buyer curBuyer = (Buyer)currentUser;

                                    curBuyer.displayCart();

                                    if(ans.equals("1")){
                                        System.out.println("to select the item you should enter the product name \n" +
                                        "seller id \n" +
                                        "count of the product");

                                        Scanner scn = new Scanner(System.in);
                                        String[] values = new String[3];
                                        values = scn.nextLine().split(" ");



                                        HashMap<Buyer,Inventory> x = Inventory.addToCart(values[0],curBuyer,values[1],Integer.parseInt(values[2]),instanceDb.get(productOwner.get(new Inventory.Tuple(values[0], values[1]))));
                                        Set<Buyer> currSet = x.keySet();
                                        Collection<Inventory> inCollection = x.values();

                                        // To convert to arrays:
                                        Buyer[] curr = currSet.toArray(new Buyer[0]);
                                        Inventory[] in = inCollection.toArray(new Inventory[0]);

                                        Profile.users.remove(new Profile.Tuple(currentUser.id,'B'));
                                        Profile.users.put(new Profile.Tuple(currentUser.id,'B'), curr[0]);
                                        // instanceDb.remove(currentUser);
                                        // instanceDb.put(currentUser,in[0]);
                                        curr[0].displayCart();
                                        continue;

                                    }
                                    else{
                                        break;
                                    }

                                }


                            }else{
                                System.out.println("please check your inputs!!");
                                continue;
                            }

                        }else{
                            break;
                        }

                    }else{
                        break;
                    }


                }
            }

            else{
                break;
            }


        }
    }
}
