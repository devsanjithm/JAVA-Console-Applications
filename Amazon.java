import java.util.*;

public  class Amazon {

    public static Scanner sc = new Scanner(System.in);

    // clear
    static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args) {

        // object creation for following classes

        while (true) {
            Amazon.clear();
            System.out.println("----- Welcome to Amazon -----\n");
            System.out.println("1 . Admin");
            System.out.println("2 . Merchant");
            System.out.println("3 . User");
            System.out.println("4 . Exit\n");
            System.out.print("Enter your choice : ");
            int c = sc.nextInt();

            if (c == 1) {
                Admin.Admin_login();
            } else if (c == 2) {
                Merchant.merchant_panel();
            } else if (c == 3) {
                User.user_panel();
            } else if (c == 4) {
                System.out.println("Thank You Have a Great Day");
                break;
            } else {
                System.out.println("Enter valid Input");
                System.out.println("Press ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }

    }
}

// Admin class
class Admin {

    public String mer_name, mer_pass;
    public int mer_id = 0;
    public static int merchant_id = 1000;
    public boolean mer_verified = false;
    public Map<String, List<String>> mer_products = new HashMap<>();

    Admin(String name, String pass, int id, boolean verify) {
        this.mer_name = name;
        this.mer_pass = pass;
        this.mer_id = id;
        this.mer_verified = verify;
    }

    static Scanner sc = new Scanner(System.in);
    static List<Admin> merchants = new ArrayList<>();

    // Login method for Admin
    public static int Admin_login() {
        Amazon.clear();
        System.out.print("----- Welcome To Admin Panel -----\n\nEnter your Username : ");
        String U_name = sc.next();
        System.out.print("Enter your Password : ");
        String pass = sc.next();
        if (U_name.equals("Admin") && pass.equals("1234")) {
            // admin panel
            admin_panel();

        } else {
            System.out.println("Invalid");
            System.out.println("Press ENTER to continue");
            sc.nextLine();
            sc.nextLine();
        }
        return 0;
    }

    static void admin_panel() {
        while (true) {
            Amazon.clear();
            System.out.println("----- Welcome Admin -----\n");
            System.out.println(
                    "1. Add Merchant\n2. Remove Merchant\n3. View All Products\n4. Add product category\n5. Approve Merchants\n6. Exit\n");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                add_merchant();
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            } else if (choice == 2) {
                remove_merchant();
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            } else if (choice == 3) {
                User.Viewproducts();
            } else if (choice == 4) {

            } else if (choice == 5) {
                approve_mer();
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Invalid\n");
                System.out.println("Press ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }

        }
    }

    static void add_merchant() {
        Amazon.clear();
        System.out.print("Enter Name : ");
        String name = sc.next();
        System.out.print("Enter Password : ");
        String pass = sc.next();
        merchants.add(new Admin(name, pass, ++merchant_id, true));
        System.out.println("\nNew Merchant id : " + merchant_id);
        System.out.println("Merchant Added successfully");
    }

    static void remove_merchant() {
        Amazon.clear();
        System.out.print("Enter Id to remove Merchant : ");
        int id = sc.nextInt();
        for (int i = 0; i < merchants.size(); i++) {
            if (id == merchants.get(i).mer_id) {
                merchants.remove(i);
                System.out.println("\nMerchant Removed successfully");
            }
        }
    }

    static void approve_mer() {
        int count = 0;
        System.out.println("List of Unapproved merchants");
        for (Admin admin : merchants) {
            if (admin.mer_verified == false) {
                System.out.println(admin.mer_id + "--" + admin.mer_name);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("Enter id to Approve : ");
            int id = sc.nextInt();
            System.out.print("Do you want to Approve (y/n) : ");
            String approve = sc.next();
            if (approve.equals("y")) {
                for (Admin admin : merchants) {
                    if (admin.mer_id == id && !(admin.mer_verified)) {
                        admin.mer_verified = true;
                        System.out.println("\nMerchant Approved");
                    }
                }
            }
        }
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }
}

class Merchant {

    static Scanner sc = new Scanner(System.in);

    static void merchant_panel() {
        while (true) {
            Amazon.clear();
            System.out.println("---- Welcome to Merchant Panel ----");
            System.out.println("\n1. New Merchant(Self Registration)\n2. Existing Merchant\n3. Exit");
            System.out.print("\nEnter your choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Amazon.clear();
                System.out
                        .println(
                                "Note..\nSelf Registration must include \nAdmin Verification to use this Applicatin\nPlease wait till Admin approve.");
                merchant_newlogin();
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            } else if (choice == 2) {
                Amazon.clear();
                System.out.print("Enter your Id : ");
                int id = sc.nextInt();
                System.out.print("Enter your Pass : ");
                String pass = sc.next();
                for (int i = 0; i < Admin.merchants.size(); i++) {
                    if (id == Admin.merchants.get(i).mer_id && pass.equals(Admin.merchants.get(i).mer_pass)) {
                        if (Admin.merchants.get(i).mer_verified == true) {
                            merchant_existing(i);
                            break;
                        } else {
                            System.out.println("\nYour Id is still pending to verify\nSo wait till Admin Approval");
                            break;
                        }
                    } else {
                        System.out.println("Username and password is invalid");
                    }
                }
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid");
            }
        }
    }

    static void merchant_newlogin() {
        System.out.print("\nEnter name : ");
        String name = sc.next();
        System.out.print("Enter pass : ");
        String pass = sc.next();
        Admin.merchants.add(new Admin(name, pass, ++Admin.merchant_id, false));
        System.out.println(
                "\nNew merchant Created\nBut wait till Admin Approval\nNew Merchant id : " + Admin.merchant_id);
    }

    static void merchant_existing(int curr_user) {
        while (true) {
            Amazon.clear();
            System.out.println("----- Welcome " + Admin.merchants.get(curr_user).mer_name + " -----");
            System.out.println(
                    "\n1. Add Products\n2. Update Products\n3. Remove Products\n4. Review Products\n5. View Repeated Customer\n6. View Most sold Products\n7. View sales Report\n8. Exit");
            System.out.print("\nEnter your Option : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                addproducts(curr_user);
            } else if (choice == 2) {
                updateproducts(curr_user);
            } else if (choice == 3) {
                removeproducts(curr_user);
            } else if (choice == 4) {
                // reviewproducts();
            } else if (choice == 5) {
                // repeatedcust();
            } else if (choice == 6) {
                // mostSoldProducts();
            } else if (choice == 7) {
                // salesProducts();
            } else if (choice == 8) {
                break;
            } else {
                System.out.println("Invalid Option");
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    static void addproducts(int curr_user) {
        Amazon.clear();
        List<String> product = new ArrayList<>();
        System.out.print("Enter product name : ");
        String pro_name = sc.next().toLowerCase();
        System.out.print("Enter count of the product : ");
        product.add(sc.next());
        System.out.print("Enter product prize : ");
        product.add(sc.next());
        Admin.merchants.get(curr_user).mer_products.put(pro_name, product);
    }

    static void updateproducts(int curr_user) {
        System.out.println("List of Products\n");
        product_list(curr_user);
        System.out.print("\nEnter product to Update : ");
        String toUpdateproduct = sc.next().toLowerCase();
        System.out.print("Enter Count to Update(if don't update enter 0) : ");
        String pro_count = sc.next();
        System.out.print("Enter prize to Update(if don't update enter 0) : ");
        String pro_prize = sc.next();
        updatevalueinmap(curr_user, toUpdateproduct, pro_count, pro_prize);
        product_list(curr_user);
        System.out.println("\nproduct Update successfully");
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();

    }

    static void product_list(int curr_user) {
        Admin k = Admin.merchants.get(curr_user);
        System.out.println("----products------count-------Prize---");
        for (String name : k.mer_products.keySet()) {
            List<String> li = k.mer_products.get(name);
            System.out.println("----" + name + "------" + li.get(0) + "------" + li.get(1) + "----");
        }
    }

    static void updatevalueinmap(int curr_user, String product, String count, String prize) {
        Admin k = Admin.merchants.get(curr_user);
        List<String> li = new ArrayList<>();
        String oldcount = k.mer_products.get(product).get(0);
        String oldprize = k.mer_products.get(product).get(1);
        if (count.equals("0"))
            li.add(oldcount);
        else
            li.add(count);
        if (prize.equals("0"))
            li.add(oldprize);
        else
            li.add(prize);
        k.mer_products.put(product, li);
    }

    static void removeproducts(int curr_user) {
        product_list(curr_user);
        System.out.print("\nEnter product to remove : ");
        String product = sc.next();
        Admin.merchants.get(curr_user).mer_products.remove(product);
        product_list(curr_user);
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }
}

class User {

    static int user_id_incr = 101;

    static Scanner sc = new Scanner(System.in);

    public String user_name, user_pass;
    public int user_id;

    User(String name, String pass, int id) {
        this.user_name = name;
        this.user_pass = pass;
        this.user_id = id;
    
    }
    static List<String>purc=new ArrayList<>();
    static List<String>cart=new ArrayList<>();

    static List<User> user_list = new ArrayList<>();

    static void user_panel() {
        while (true) {
            Amazon.clear();
            System.out.println("1. User New self Registration\n2. Existing User Login\n3. Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                new_user_Register();
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            } else if (choice == 2) {
                Amazon.clear();
                System.out.println(user_list.toString());
                System.out.print("Enter your name : ");
                String name = sc.next();
                System.out.print("Enter password : ");
                String pass = sc.next();
                for (int i = 0; i < user_list.size(); i++) {
                    if (name.equals(user_list.get(i).user_name) && pass.equals(user_list.get(i).user_pass)) {
                        user_existing(i);
                        break;
                    } else {
                        System.out.println("Username and password is invalid");
                        System.out.println("\nPress ENTER to continue");
                        sc.nextLine();
                        sc.nextLine();
                    }
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid Option");
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    static void new_user_Register() {
        Amazon.clear();
        System.out.print("Enter name : ");
        String name = sc.next();
        System.out.print("Enter Password : ");
        String pass = sc.next();
        user_list.add(new User(name, pass, ++user_id_incr));
        System.out.println("\nNew User created successfully");
    }

    static void user_existing(int curr_user) {
        while (true) {
            Amazon.clear();
            System.out.println("----- Welcome " + user_list.get(curr_user).user_name + " -----");
            System.out.println(
                    "\n1. View Products\n2. buy product\n3. Add products to cart\n4. View Purchase history\n5. View Cart\n6. Exit");
            System.out.print("\nEnter your Option : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Viewproducts();
            } 
            else if (choice == 2) {
                if(Admin.merchants.size()==0){
                    System.out.println("No products Available");
                    System.out.println("press Enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                }
                else if(Admin.merchants.size()>0){
                    
                
                Viewproducts();
                System.out.println("Enter the product name to purchase");
                String na=sc.next();
                System.out.println("Enter the count");
                int co=sc.nextInt();
               System.out.println("Press Enter");
                sc.nextLine();
                int g=Integer.parseInt(Admin.merchants.get(curr_user).mer_products.get(na).get(0));
                if(co<=g){
                g-=co;
                System.out.println("Product purchased succesfully");
                
                Admin.merchants.get(curr_user).mer_products.get(na).set(0,Integer.toString(g));

                purc.add(na+" "+Integer.toString(co));
                System.out.println("press Enter");
                
                sc.nextLine();
                

                }else{
                    if(g==0){
                        System.out.println("product sold out");
                        System.out.println("press Enter to continue");
                        sc.nextLine();
                    }
                    else{
                        System.out.println("please enter count within "+g);
                        System.out.println("press Enter to continue");
                        sc.nextLine();
                    }
                }
            }
        }
            else if (choice == 3) {
                if(Admin.merchants.size()==0){
                    System.out.println("No products Available");
                    System.out.println("press Enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                }
                else if(Admin.merchants.size()>0){
                    
                
                Viewproducts();
                System.out.println("Enter the product name to add to your cart ");
                String na=sc.next();
                System.out.println("Enter the count");
                int co=sc.nextInt();
               System.out.println("Press Enter");
                sc.nextLine();
                int g=Integer.parseInt(Admin.merchants.get(curr_user).mer_products.get(na).get(0));
                if(co<=g){
                
                System.out.println("Product added to cart succesfully");
                

                cart.add(na+" "+Integer.toString(co));
                System.out.println("press Enter to continue");
                
                sc.nextLine();
                

                }else{
                    if(g==0){
                        System.out.println("product sold out");
                        System.out.println("press Enter to continue");
                        sc.nextLine();
                    }
                    else{
                        System.out.println("please enter count within "+g);
                        System.out.println("press Enter to continue");
                        sc.nextLine();
                    }
                }
            }
                
            } else if (choice == 4) {
                System.out.println("Purchase history");
                for(String i:purc){
                    System.out.println(i);
                }
                sc.nextLine();
                sc.nextLine();
            }
            else if(choice==5){
                System.out.println("Product available in cart");
                for(String d:cart){
                    System.out.println(d);
                }
                System.out.println("Press enter to continue");
                sc.nextLine();
                sc.nextLine();
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Invalid Option");
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    static void Viewproducts() {
        System.out.println("----products------count-------Prize---");
        for (int i = 0; i < Admin.merchants.size(); i++) {
            Admin k = Admin.merchants.get(i);
            for (String name : k.mer_products.keySet()) {
                List<String> li = k.mer_products.get(name);
                System.out.println("----" + name + "------" + li.get(0) + "------" + li.get(1) + "----");
            }
        }
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }
}