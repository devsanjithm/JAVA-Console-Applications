import java.util.*;
import java.text.*;

public class Splitwise {

    static Scanner sc = new Scanner(System.in);

    static void clear() {
        System.out.print("\033[H\033[2J");
    }

    static String actualDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String date = sdf.format(cal.getTime());
        return date;
    }

    static void waitUntil() {
        System.out.print("\nPress ENTER to continue...");
        sc.nextLine();
        sc.nextLine();
    }

    static void main_fun() {
        while (true) {
            clear();
            System.out.print("---- Welcome to splitwise Application ----" +
                    "\n\n1. Register User" +
                    "\n2. Login User" +
                    "\n3. Exit\n" +
                    "\nEnter Your Choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                clear();
                User.registerUser();
                waitUntil();
            } else if (choice == 2) {
                clear();
                User.loginUser();
                waitUntil();
            } else if (choice == 3) {
                System.out.println("----------------------------");
                System.out.println("|Thank You Have a Great Day|");
                System.out.println("----------------------------");
                System.exit(0);
            } else {
                System.out.println("\nEnter valid Input");
                waitUntil();
            }
        }
    }

    public static void main(String[] args) {
        main_fun();
    }
}

class User {

    static int userid_incr = 100;
    static Scanner sc = new Scanner(System.in);

    public String email, password, name;

    public int user_id, user_Wallet;

    public List<String> userTransactions;

    User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.user_id = ++userid_incr;
        this.user_Wallet = 1500;
        this.userTransactions = new ArrayList<String>();
    }

    static List<User> userList = new ArrayList<>();
    static List<Expenses> NgrpexpenseList = new ArrayList<>();
    static List<Group> groupList = new ArrayList<>();

    static void registerUser() {
        System.out.println("---- Create Your Account ----");
        System.out.print("\n\nEnter your Email : ");
        String email = sc.next();
        for (User user : userList) {
            if (email.equals(user.email)) {
                System.out.println("\nEmail ID Already Exists\n" +
                        "Choose Different Id");
                return;
            }
        }
        System.out.print("\nEnter your Password : ");
        String password = sc.next();
        System.out.print("\nEnter your Name : ");
        String name = sc.next();
        userList.add(new User(email, password, name));
        System.out.println("\nUser Created Successfully");
    }

    static void loginUser() {
        System.out.println("------ Login ------");
        System.out.print("\nEnter your Email : ");
        String email = sc.next();
        System.out.print("\nEnter password : ");
        String pass = sc.next();
        int flag = 0;
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).email) && pass.equals(userList.get(i).password)) {
                user_panel(i);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("\nUsername and password is invalid");
        }
    }

    static void user_panel(int curr_user) {
        while (true) {
            Splitwise.clear();
            System.out.println("----- Welcome " + userList.get(curr_user).name + " -----");
            System.out.print("\n1. Add an Expenses" +
                    "\n2. Update Your Wallet Amount" +
                    "\n3. Create a group with thier Contacts" +
                    "\n4. Add friends into the Group" +
                    "\n5. Remove friends from the Group" +
                    "\n6. View and Pay their pending dues" +
                    "\n7. View their Transaction History" +
                    "\n8. Logout\n" +
                    "\nEnter your Choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Splitwise.clear();
                add_expense(curr_user);
            } else if (choice == 2) {
                Splitwise.clear();
                update_wallet(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 3) {
                Splitwise.clear();
                create_group(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 4) {
                Splitwise.clear();
                add_friendfront(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 5) {
                Splitwise.clear();
                remove_friends(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 6) {
                Splitwise.clear();
                View_expenses(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 7) {
                Splitwise.clear();
                Transaction(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 8) {
                break;
            } else {
                System.out.println("\nInvalid Option");
                Splitwise.waitUntil();
            }
        }
    }

    static void Transaction(int curr_user) {
        System.out.println("---- Transaction History ----");
        User obj = userList.get(curr_user);
        for (int i = 0; i < obj.userTransactions.size(); i++) {
            String[] arr = obj.userTransactions.get(i).split(":");
            if (arr.length == 6) {
                // Non_Groups Transactions
                System.out.println(
                        "\nExpense Name : " + arr[0] +
                                "\nExpense Date : " + arr[1] +
                                "\nExpense Amount : " + arr[2] +
                                "\nExpense CreatedBy : " + arr[3] +
                                "\nExpense Type : " + arr[4] +
                                "\nMoney  : " + arr[5]);

            } else if (arr.length == 7) {
                // Groups Transactions
                System.out.println(
                        "\nExpense Name : " + arr[0] +
                                "\nExpense Date : " + arr[1] +
                                "\nExpense Amount : " + arr[2] +
                                "\nExpense CreatedBy : " + arr[3] +
                                "\nExpense Type : " + arr[4] +
                                "\nGroup Name of the Expense Belongs : " + arr[5]
                                + "\nMoney  : " + arr[6]);

            }
        }
        if (userList.get(curr_user).userTransactions.size() == 0) {
            System.out.println("\n\n--------- Null --------");
        }
    }

    static void add_expense(int curr_user) {
        while (true) {
            Splitwise.clear();
            System.out.print("---- Add Expenses ----\n" +
                    "\n1. Group Expenses" +
                    "\n2. Non-Group Expenses" +
                    "\n3. Back\n" +
                    "\nEnter Your Choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Splitwise.clear();
                group_expenses(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 2) {
                Splitwise.clear();
                nongroup_expenses(curr_user);
                Splitwise.waitUntil();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("\nInvalid Option");
                Splitwise.waitUntil();
            }

        }
    }

    static void update_wallet(int curr_user) {
        System.out.print("----- Update Wallet ----\n" +
                "\nYour Wallet Amount is : " + userList.get(curr_user).user_Wallet);
        System.out.print("\n\nWhether You Want to Update the Amount (y/n) : ");
        String confirmation = sc.next();
        if (confirmation.equals("y")) {
            System.out.print("\nEnter Amount to update : ");
            int amount = sc.nextInt();
            userList.get(curr_user).user_Wallet += amount;
            System.out.println("\nAmount Updated Successfully" +
                    "\n\nYour Wallet Amount is : " + userList.get(curr_user).user_Wallet);

        }
    }

    static void contacts(int curr_user) {
        Splitwise.clear();
        System.out.print("Contacts  \n");
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).user_id != userList.get(curr_user).user_id) {
                System.out.print("\nName : " + userList.get(i).name + "\n");
            }
        }
    }

    static void create_group(int curr_user) {
        System.out.println("---- Create Group ----");
        System.out.print("\n\nWhether You Want to Create Group (y/n) : ");
        String confirmation = sc.next();
        if (confirmation.equals("y")) {
            System.out.print("\nGroup Name : ");
            String group_name = sc.next();
            System.out.print("\nWhether want to Add friends (y/n) : ");
            String confirmation1 = sc.next();
            if (confirmation1.equals("y")) {
                groupList.add(new Group(group_name, userList.get(curr_user).user_id));
                add_friends(curr_user, groupList.size() - 1);
            } else {
                groupList.add(new Group(group_name, userList.get(curr_user).user_id));
                System.out.println("\nGroup Created Successfully");
            }
        }
    }

    static void add_friends(int curr_user, int index) {
        contacts(curr_user);
        List<String> li = new ArrayList<String>();
        while (true) {
            System.out.print("\nEnter Name to add friends or cancel list (n) : ");
            String con = sc.next();
            if (!(con.equals("n"))) {
                if (!(groupList.get(index).group_persons.contains(con) || li.contains(con))) {
                    boolean flag = true;
                    for (User u : userList) {
                        if (u.name.equals(con)) {
                            li.add(con);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.print("\nEntered name is Not available in contact List");
                    }
                } else {
                    System.out.print("\nEntered Name is already in list");
                }
            } else {
                li.add(userList.get(curr_user).name);
                for (String s : li) {
                    groupList.get(index).group_persons.add(s);
                }
                break;
            }
        }
        System.out.print(li.size() != 0 ? "\nFriends added Successfully" : "");
    }

    static void add_friendfront(int curr_user) {
        System.out.println("---- Add Friends ----");
        for (int i = 0; i < groupList.size(); i++) {
            System.out.println(groupList.get(i).group_name);
        }
        if (groupList.size() == 0) {
            System.out.print("\nNo Group Records\n");
            return;
        }
        System.out.print("\nEnter Group name to add friends or cancel (n) : ");
        String grp_name = sc.next();
        if (grp_name.equals("n")) {
            return;
        }
        boolean flag = false;
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).group_name.equals(grp_name)) {
                add_friends(curr_user, i);
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.print("\nEntered Group name is Not available");
        }
    }

    static void remove_friends(int curr_user) {
        System.out.println("---- Remove Friends ----");
        for (int i = 0; i < groupList.size(); i++) {
            System.out.println(groupList.get(i).group_name);
        }
        if (groupList.size() == 0) {
            System.out.print("\nNo Group Records\n");
            return;
        }
        System.out.print("\nEnter Group name to Remove friends or cancel (n) : ");
        String grp_name = sc.next();
        if (grp_name.equals("n")) {
            return;
        }

        boolean flag = false;
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).group_name.equals(grp_name)) {
                System.out.println("\nAvailable Persons in the " + grp_name + " Groups");
                for (int j = 0; j < groupList.get(i).group_persons.size(); j++) {
                    System.out.print("\n" + groupList.get(i).group_persons.get(j));
                }
                while (true) {
                    System.out.print("\nEnter Name to add friends or cancel list (n) : ");
                    String con = sc.next();
                    if (!(con.equals("n"))) {
                        if (groupList.get(i).group_persons.contains(con)) {
                            groupList.get(i).group_persons.remove(con);
                            System.out.println("\nPerson Removed successfully");
                        } else {
                            System.out.print("\nEntered Name is Not in list");
                        }
                    } else {
                        break;
                    }
                }
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.print("\nEntered Group name is Not available");
        }
    }

    static void group_expenses(int curr_user) {
        System.out.println("----  Add Group Expenses ----");
        for (int i = 0; i < groupList.size(); i++) {
            System.out.println(groupList.get(i).group_name);
        }
        if (groupList.size() == 0) {
            System.out.print("\nNo Group Records\n");
            return;
        }
        System.out.print("\nEnter Group Name or cancel (n) : ");
        String grp_name = sc.next();
        if (grp_name.equals("n")) {
            return;
        }
        boolean flag = true;
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).group_name.equals(grp_name)) {
                group_expenseAdd(curr_user, i);
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.print("\nEntered Group name is Not available");
        }
    }

    static void group_expenseAdd(int curr_user, int groupIndex) {
        System.out.print("\nEnter Expenses Name : ");
        String expense_name = sc.next();
        System.out.print("\nEnter Expenses Amount : ");
        int expense_amount = sc.nextInt();
        groupList.get(groupIndex).expense
                .add(new group_expenses(expense_amount, expense_name, userList.get(curr_user).user_id,
                        userList.get(curr_user).name));
        for (int i = 0; i < groupList.get(groupIndex).group_persons.size(); i++) {
            group_expenses obj = groupList.get(groupIndex).expense.get(groupList.get(groupIndex).expense.size() - 1);
            obj.expense_status.put(groupList.get(groupIndex).group_persons.get(i), "Not Paid");

        }
        System.out.print("\n Expenses Added to the Group Successfully ");
    }

    static void nongroup_expenses(int curr_user) {
        System.out.println("----  Add Non-Group Expenses ----");
        System.out.print("\nEnter Expenses Name : ");
        String expense_name = sc.next();
        System.out.print("\nEnter Expenses Amount : ");
        int expense_amount = sc.nextInt();
        contacts(curr_user);
        Map<String, String> map = new HashMap<String, String>();
        while (true) {
            System.out.print("\nEnter Name to add friends or cancel list (n) : ");
            String con = sc.next();
            if (!(con.equals("n"))) {
                if (!(map.containsKey(con))) {
                    boolean flag = true;
                    for (User u : userList) {
                        if (u.name.equals(con)) {
                            map.put(con, "Not Paid");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.print("\nEntered name is Not available in contact List");
                    }
                } else {
                    System.out.print("\nEntered Name is already in list");
                }
            } else {
                map.put(userList.get(curr_user).name, "Paid");
                NgrpexpenseList.add(new Expenses(expense_name, expense_amount, userList.get(curr_user).user_id,
                        userList.get(curr_user).name, map));
                break;
            }
        }
        System.out.print(map.size() != 0 ? "\nFriends added Successfully" : "");

    }

    static void View_expenses(int curr_user) {

        System.out.println("---- View Expenses ----");
        System.out.print("\n1. Group Expenses" +
                "\n2. Non-Group Expenses" +
                "\n3. Back\n" +
                "\nEnter Your Choice : ");
        int choice = sc.nextInt();
        if (choice == 2) {
            Viewnongroup_expenses(curr_user);

            return;
        } else if (choice == 3) {
            return;
        }
        for (int i = 0; i < groupList.size(); i++) {
            System.out.println(groupList.get(i).group_name);
        }
        if (groupList.size() == 0) {
            System.out.print("\nNo Group Records\n");
            return;
        }
        System.out.print("\nEnter Group Name or cancel (n) : ");
        String grp_name = sc.next();
        if (grp_name.equals("n")) {
            return;
        }
        boolean flag = false;

        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).group_name.equals(grp_name)) {
                if (groupList.get(i).expense.size() == 0) {
                    System.out.print("\nNo expenses Records");
                    return;
                }
                for (int j = 0; j < groupList.get(i).expense.size(); j++) {
                    String username = userList.get(curr_user).name;
                    if (groupList.get(i).expense.get(j).expense_status.get(username).equals("Not Paid")) {
                        group_expenses obj = groupList.get(i).expense.get(j);
                        int amount = obj.expense_amount / groupList.get(i).group_persons.size();
                        if (obj.username.equals(username)) {
                            groupList.get(i).expense.get(j).expense_status.put(username, "Paid");
                        }
                        System.out.print("\nName : " + obj.expense_name);
                        System.out.print("\nDate : " + obj.expense_dateandtime);
                        System.out.print("\nstatus : " + obj.expense_status);
                        System.out.print(obj.username.equals(username) ? "\nAmount : " + obj.expense_amount
                                : "\nAmount : " + amount);
                        System.out.print(
                                obj.username.equals(username) ? "\nExpenses CreatedBy : " + " By You"
                                        : "\nExpenses CreatedBy : " + obj.username);
                        if (!(obj.username.equals(username))) {
                            System.out.print("\n\nDo you want to Pay this Due (y/n): ");
                            String con = sc.next();
                            if (con.equals("y")) {
                                if (userList.get(curr_user).user_Wallet > amount) {
                                    userList.get(curr_user).user_Wallet -= amount;
                                    String jo = obj.expense_name + ":" + obj.expense_dateandtime + ":" + amount + ":"
                                            + obj.username + ":" + "Group" + ":" + groupList.get(i).group_name + ":"
                                            + "Debit";
                                    userList.get(curr_user).userTransactions.add(jo);
                                    for (int k = 0; k < userList.size(); k++) {
                                        if (userList.get(k).name.equals(obj.username)) {
                                            String jo1 = obj.expense_name + ":" + obj.expense_dateandtime + ":" + amount
                                                    + ":"
                                                    + obj.username + ":" + "Group" + ":" + groupList.get(i).group_name
                                                    + ":" + "Credit";
                                            userList.get(k).user_Wallet += amount;
                                            for (int l = 0; l < userList.size(); l++) {
                                                if (userList.get(l).name.equals(obj.username)) {
                                                    userList.get(l).userTransactions.add(jo1);
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    groupList.get(i).expense.get(j).expense_status.put(username, "Paid");
                                    System.out.println("\nDue Payed Successfully ");
                                } else {
                                    System.out.println("\nInsufficient Balance");
                                }
                            }
                        }
                    } else {
                        System.out.println("\nNo Due Records");
                    }
                }
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.print("\nEntered Group name is Not available");
        }
    }

    static void Viewnongroup_expenses(int curr_user) {
        Splitwise.clear();
        for (Expenses expenses : NgrpexpenseList) {
            if (!(expenses.expense_createdBy == userList.get(curr_user).user_id)) {
                if (expenses.persons_involved.containsKey(userList.get(curr_user).name)) {
                    System.out.print(expenses.expense_name);
                    System.out.println(" " + expenses.persons_involved.get(userList.get(curr_user).name));
                }
            } else {
                System.out.println(expenses.expense_name + " is created by you");
            }
        }
        if (NgrpexpenseList.size() == 0) {
            System.out.print("\nNo Non-Group Records\n");
            return;
        }
        System.out.println("\nEnter Expenses Name to pay : ");
        String expe_name = sc.next();

        boolean flag = true;
        for (int i = 0; i < NgrpexpenseList.size(); i++) {
            if (NgrpexpenseList.get(i).expense_name.equals(expe_name)) {
                if (!(NgrpexpenseList.get(i).expense_createdBy == userList.get(curr_user).user_id)) {
                    if (NgrpexpenseList.get(i).persons_involved.get(userList.get(curr_user).name).equals("Not Paid")) {
                        Expenses obj = NgrpexpenseList.get(i);
                        int amount = obj.expense_amount / obj.persons_involved.size();
                        System.out.print("\nName : " + obj.expense_name);
                        System.out.print("\nDate : " + obj.expense_dateandtime);
                        System.out.print("\nstatus : " + obj.persons_involved);
                        System.out.print("\nAmount : " + amount);
                        System.out.print("\nExpenses CreatedBy : " + obj.username);
                        System.out.print("\n\nDo you want to Pay this Due (y/n): ");
                        String con = sc.next();
                        if (con.equals("y")) {
                            if (userList.get(curr_user).user_Wallet > amount) {
                                userList.get(curr_user).user_Wallet -= amount;
                                String jo = obj.expense_name + ":" + obj.expense_dateandtime + ":" + amount + ":"
                                        + obj.username + ":" + "Non_Groups" + ":" + "Debit";
                                userList.get(curr_user).userTransactions.add(jo);
                                for (int k = 0; k < userList.size(); k++) {
                                    if (userList.get(k).name.equals(obj.username)) {
                                        userList.get(k).user_Wallet += amount;
                                        String jo1 = obj.expense_name + ":" + obj.expense_dateandtime + ":" + amount
                                                + ":"
                                                + obj.username + ":" + "Non-Group" + ":" + "Credit";
                                        userList.get(k).user_Wallet += amount;
                                        for (int l = 0; l < userList.size(); l++) {
                                            if (userList.get(l).name.equals(obj.username)) {
                                                userList.get(l).userTransactions.add(jo1);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                NgrpexpenseList.get(i).persons_involved.put(userList.get(curr_user).name, "Paid");
                                System.out.println("\nDue Payed Successfully ");
                                flag = false;
                                break;
                            } else {
                                System.out.println("\nInsufficient Balance");
                                flag = false;
                                break;
                            }
                        }
                    } else {
                        System.out.println("\nYou Already Paid ");
                        flag = false;
                        break;
                    }

                } else {
                    System.out.println("\nThis Expenses was Created By You");
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            System.out.println("\nYou have no Non-group records");
        }
    }
}

class Expenses {

    static int expenseID_incr = 10000;

    public String expense_name, expense_dateandtime, username;

    public int expense_amount, expense_createdBy, expenseId;

    public String date = Splitwise.actualDate();

    public Map<String, String> persons_involved;

    Expenses(String name, int amount, int CreatedBy, String username, Map<String, String> li) {
        this.expense_amount = amount;
        this.expense_createdBy = CreatedBy;
        this.expense_dateandtime = date;
        this.expense_name = name;
        this.persons_involved = li;
        this.username = username;
        this.expenseId = ++expenseID_incr;
    }

}

class Group {

    static int group_id_incr = 1000;

    public String group_name;

    public int group_id, group_createdBy;

    public List<String> group_persons;

    public List<group_expenses> expense;

    Group(String name, int createdBy) {
        this.group_id = group_id_incr;
        this.group_name = name;
        this.group_persons = new ArrayList<String>();
        this.group_createdBy = createdBy;
        this.expense = new ArrayList<>();
    }

}

class group_expenses {

    static int expenseID_incr = 100000;

    public String expense_name, expense_dateandtime, username;

    public int expense_amount, expense_createdBy, expense_Id;

    public String date = Splitwise.actualDate();

    public Map<String, String> expense_status;

    group_expenses(int amount, String name, int createdBy, String username) {
        this.expense_amount = amount;
        this.expense_Id = ++expenseID_incr;
        this.expense_createdBy = createdBy;
        this.expense_dateandtime = date;
        this.expense_name = name;
        this.expense_status = new HashMap<String, String>();
        this.username = username;
    }
}