import java.util.*;
import java.text.*;
import java.util.concurrent.*;

public class Library {
    static Scanner sc = new Scanner(System.in);

    static void clear() {
        System.out.print("\033[H\033[2J");
    }

    static String actualDate(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, n);
        String date = sdf.format(cal.getTime());
        return date;
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

    static int main_fun() {
        while (true) {
            Library.clear();
            System.out.print("----- Welcome to Library Management -----\n" +
                    "\n1. Admin" +
                    "\n2. Borrower / User" +
                    "\n3. Exit\n" +
                    "\nEnter your choice : ");
            int c = sc.nextInt();

            if (c == 1) {
                Admin.admin_login();
            } else if (c == 2) {
                Borrower.user_login();
            } else if (c == 3) {
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

class Admin {
    static Scanner sc = new Scanner(System.in);

    public String email, password, name;

    Admin(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    static List<Admin> adminList = new ArrayList<Admin>();
    static List<Book> bookList = new ArrayList<Book>(
            Arrays.asList(new Book("Book1", 100, "1234"),
                    new Book("Book2", 80, "4321")));
    static List<Borrower> userList = new ArrayList<Borrower>(
            Arrays.asList(new Borrower("user1", "1234", "user1@gmail.com"),
                    new Borrower("user2", "1234", "user2@gmail.com")));

    static List<Borrowered_details> borrowed_book_detailsList = new ArrayList<Borrowered_details>();

    static void admin_login() {
        adminList.add(new Admin("admin@gmail.com", "1234", "admin"));
        Library.clear();
        System.out.print("\t---- Login ----" +
                "\n\nEnter your Email : ");
        String email = sc.next();
        System.out.print("Enter your Password : ");
        String pass = sc.next();
        boolean flag = true;
        for (int i = 0; i < adminList.size(); i++) {
            if (email.equals(adminList.get(i).email) && pass.equals(adminList.get(i).password)) {
                flag = false;
                admin_panel(i);
            }
        }
        if (flag) {
            System.out.println("\nUser Credentials are Invalid");
            Library.waitUntil();
        }
    }

    static void admin_panel(int curr_user) {
        while (true) {
            Library.clear();
            System.out.print("----- Welcome to " + adminList.get(curr_user).name + " -----\n" +
                    "\n1. Add / Modify / Delete Book" +
                    "\n2. Add Admin" +
                    "\n3. Add Borrower" +
                    "\n4. View Book" +
                    "\n5. Search Book" +
                    "\n6. Reports" +
                    "\n7. Back\n" +
                    "\nEnter your Choice : ");
            int choice = sc.nextInt();

            if (choice == 1) {
                Book.book_panel(curr_user);
            } else if (choice == 2) {
                add_admin();
                Library.waitUntil();
            } else if (choice == 3) {
                add_borrower(curr_user);
            } else if (choice == 4) {
                Library.clear();
                Book.show_book("0");
                Library.waitUntil();
            } else if (choice == 5) {
                search_book(curr_user);
                Library.waitUntil();
            } else if (choice == 6) {
                Library.clear();
                reports();
            } else if (choice == 7) {
                Library.main_fun();
            } else {
                System.out.println("\nInvalid choice");
                Library.waitUntil();
            }
        }
    }

    static void add_admin() {
        Library.clear();
        System.out.print("---- Add Admin ----" +
                "\n\nEnter your Name : ");
        String name = sc.next();
        System.out.print("Enter your Email : ");
        String email = sc.next();
        System.out.print("Enter your password : ");
        String pass = sc.next();
        adminList.add(new Admin(email, pass, name));
        System.out.println("\nAdmin Added successfully");
    }

    static void reports() {
        while (true) {
            Library.clear();
            System.out.print("\n1. Books with less Quantity" +
                    "\n2. Books are Not Borrowed So far" +
                    "\n3. Books heavily Borrowed" +
                    "\n4. status of the Books" +
                    "\n5. Students in Outstanding List" +
                    "\n6. back\n" +
                    "\nEnter your Choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Library.clear();
                less_quantity();
                Library.waitUntil();
            } else if (choice == 2) {
                Library.clear();
                not_borrowed();
                Library.waitUntil();
            } else if (choice == 3) {
                Library.clear();
                heavy_borrowed();
                Library.waitUntil();
            } else if (choice == 4) {
                Library.clear();
                statusoftheBook();
                Library.waitUntil();
            } else if (choice == 5) {
                Library.clear();
                outstandingList();
                Library.waitUntil();
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("\n Invalid Option");
                Library.waitUntil();
            }
        }
    }

    static void statusoftheBook() {
        System.out.print("Enter Book Name or Isbn Number to search : ");
        String name_ISBN = sc.next();
        boolean flag = true;
        for (int i = 0; i < Admin.bookList.size(); i++) {
            if (name_ISBN.equals(Admin.bookList.get(i).book_ISBN)
                    || name_ISBN.equals(Admin.bookList.get(i).book_name)) {
                name_ISBN = Admin.bookList.get(i).book_ISBN;
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("\nEntered Book ISBN is Invalid" +
                    "\nEnter correct Book ISBN");
            Library.waitUntil();
            return;
        }
        boolean flag1 = true;
        for (int i = 0; i < Admin.borrowed_book_detailsList.size(); i++) {
            Borrowered_details obj = Admin.borrowed_book_detailsList.get(i);
            if (obj.book_ISBN.equals(name_ISBN)) {
                if (obj.returnStatus.equals("Not Returned")) {
                    System.out.println("Student Name : " + obj.username);
                    System.out.println("Student ID : " + obj.user_id);
                    System.out.println("Borrowed Date : " + obj.borrowed_date);
                    System.out.println("User Return Date : " + obj.userReturn_date);
                    flag1 = false;
                    break;
                }
            }
        }
        if (flag1) {
            System.out.println("\nCurrently there is No books Borrowed");
            return;
        }
    }

    static void outstandingList() {
        boolean flag = true;
        System.out.println("Students Not returned book on time\nNames : ");
        for (int i = 0; i < Admin.userList.size(); i++) {
            if (Admin.userList.get(i).user_fine.size() > 0) {
                System.out.println(Admin.userList.get(i).userName);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("\nCurrently there is no records");
            return;
        }
    }

    static void heavy_borrowed() {
        boolean flag = true;
        System.out.println("\nBooks that are borrowed heavily");
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).borrowed_status > 10) {
                System.out.println(bookList.get(i).book_name + " | " + bookList.get(i).book_ISBN + " | Borrowed times("
                        + bookList.get(i).borrowed_status + ")");
                flag = false;
            }
        }
        if (flag) {
            System.out.println("\nCurrently there is no records");
            return;
        }
    }

    static void less_quantity() {
        boolean flag = true;
        System.out.println("\nBooks with Less Quantity");
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).book_quantity < 10) {
                System.out.println(bookList.get(i).book_name + " | " + bookList.get(i).book_ISBN + " | "
                        + bookList.get(i).book_quantity);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("\nCurrently there is no records");
            return;
        }
    }

    static void add_borrower(int curr_user) {
        Library.clear();
        System.out.print("---- Add borrower ----" +
                "\n\nEnter Borrower Name or (if you want to exit type 'y') : ");
        String name = sc.next();
        if (name.equals("y")) {
            admin_panel(curr_user);
        }
        System.out.print("Enter Borrower Email : ");
        String email = sc.next();
        System.out.print("Enter Borrower password : ");
        String pass = sc.next();
        userList.add(new Borrower(name, pass, email));
        System.out.println("\nUser Added successfully");
    }

    static void search_book(int curr_user) {
        Library.clear();
        System.out.println("---- Search panel ----");
        System.out.print("\nEnter book name or ISBN number or (if you want to exit type 'y') : ");
        String name = sc.next();
        if (name.equals("y")) {
            admin_panel(curr_user);
        }

        boolean flag = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).book_name.equals(name) || bookList.get(i).book_ISBN.equals(name)) {
                System.out.println("\n\nName " + "  Quantity" + "  ISBN\n");
                System.out.println(bookList.get(i).book_name +
                        " | " + bookList.get(i).book_quantity +
                        " | " + bookList.get(i).book_ISBN);
                flag = false;
                break;
            }
            flag = true;
        }
        if (flag) {
            System.out.println("\nEntered Book Name or ISBN is Invalid" +
                    "\nEnter correct Details");
            Library.waitUntil();
            search_book(curr_user);
        }
    }

    static void not_borrowed() {
        boolean flag = true;
        System.out.println("Books that are not borrowed ");
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).borrowed_status == 0) {
                System.out.println(bookList.get(i).book_name + " | " + bookList.get(i).book_ISBN);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("\nCurrrently there is no records");
            return;
        }
    }

}

class Book {

    static Scanner sc = new Scanner(System.in);

    public String book_name, book_ISBN;
    public int book_quantity, borrowed_status;

    Book(String name, int quantity, String ISBN) {
        this.book_name = name;
        this.book_quantity = quantity;
        this.book_ISBN = ISBN;
        this.borrowed_status = 0;
    }

    static void book_panel(int curr_user) {
        while (true) {
            Library.clear();
            System.out.print("---- Book Panel ----" +
                    "\n\n1. Add Book" +
                    "\n2. Modify Book" +
                    "\n3. Delete Book" +
                    "\n4. Back\n" +
                    "\nEnter your Choice : ");

            int choice = sc.nextInt();
            if (choice == 1) {
                add_book(curr_user);
            } else if (choice == 2) {
                modify_book(curr_user);
            } else if (choice == 3) {
                delete_book(curr_user);
            } else if (choice == 4) {
                Admin.admin_panel(curr_user);
            } else {
                System.out.println("\nInvalid choice");
                Library.waitUntil();
            }
        }
    }

    static void add_book(int curr_user) {
        Library.clear();
        System.out.print("---- Add Book ----" +
                "\n\nEnter Book Name or (if you want to exit type 'y') : ");
        String book_name = sc.next();
        if (book_name.equals("y")) {
            book_panel(curr_user);
        }
        for (int i = 0; i < Admin.bookList.size(); i++) {
            if (Admin.bookList.get(i).book_name.equals(book_name)) {
                System.out.println("\nEntered Book is Already Available " +
                        "If you want to Add Book. Go to Modify panel to Modify the Quantity of the book.");
                Library.waitUntil();
                book_panel(curr_user);
            }
        }
        System.out.print("Enter Book Quantity : ");
        int book_quantity = sc.nextInt();
        System.out.print("Enter ISBN Number : ");
        String book_ISBN = sc.next();
        Admin.bookList.add(new Book(book_name, book_quantity, book_ISBN));
        System.out.println("\nBook Created Successfully");
        Library.waitUntil();
    }

    static void modify_book(int curr_user) {
        Library.clear();
        System.out.println("---- Modify Book ----\n");
        show_book("0");
        System.out.print("\nEnter Book name to modify or (if you want to exit type 'y') : ");
        String book_name = sc.next();
        if (book_name.equals("y")) {
            book_panel(curr_user);
        }
        boolean flag = true;
        for (int i = 0; i < Admin.bookList.size(); i++) {
            if (Admin.bookList.get(i).book_name.equals(book_name)) {
                System.out.print("Enter quantity to modify : ");
                int quantity = sc.nextInt();
                Admin.bookList.get(i).book_quantity = quantity;
                System.out.println("\nBook upadated successfully");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("\nEntered Book Name is Invalid" +
                    "\nEnter correct Book Name");
            Library.waitUntil();
            modify_book(curr_user);
        }
        Library.waitUntil();
    }

    static void show_book(String n) {
        if (n.equals("0")) {
            System.out.println("Name " + "  Quantity" + "  ISBN\n");
            for (int i = 0; i < Admin.bookList.size(); i++) {
                System.out.println(Admin.bookList.get(i).book_name +
                        " | " + Admin.bookList.get(i).book_quantity +
                        " | " + Admin.bookList.get(i).book_ISBN);
            }
        } else {
            boolean flag = true;
            System.out.println("\nName " + "  Quantity" + "  ISBN\n");
            for (int i = 0; i < Admin.bookList.size(); i++) {
                if (n.equals(Admin.bookList.get(i).book_ISBN)) {
                    System.out.println(Admin.bookList.get(i).book_name +
                            " | " + Admin.bookList.get(i).book_quantity +
                            " | " + Admin.bookList.get(i).book_ISBN);
                    flag = false;
                    break;
                }

            }
            if (flag) {
                System.out.println("\nEntered Book ISBN is Invalid" +
                        "\nEnter correct Book ISBN");
                Library.waitUntil();
            }
        }
    }

    static void delete_book(int curr_user) {
        Library.clear();

        System.out.print("----- Delete panel -----\n");
        System.out.println();
        show_book("0");
        System.out.print("\nEnter Book name to delete or (if you want to exit type 'y') : ");
        String book_name = sc.next();
        if (book_name.equals("y")) {
            book_panel(curr_user);
        }
        boolean flag = false;
        for (int i = 0; i < Admin.bookList.size(); i++) {
            if (Admin.bookList.get(i).book_name.equals(book_name)) {
                System.out.print("\nEnter Admin Password for Conformation : ");
                String pass = sc.next();
                if (Admin.adminList.get(curr_user).password.equals(pass)) {
                    Admin.bookList.remove(i);
                    System.out.println("\n Book deleted successfully");
                    flag = false;
                    break;
                } else {
                    System.out.println("\nPassword Is Invalid");
                    Library.waitUntil();
                    delete_book(curr_user);
                }

            }
            flag = true;
        }
        if (flag) {
            System.out.println("\nEntered Book Name is Invalid" +
                    "\nEnter correct Book Name");
            Library.waitUntil();
            delete_book(curr_user);
        }
        Library.waitUntil();
    }
}

class Borrower {

    static Scanner sc = new Scanner(System.in);
    int id = 101;
    public String userName, userWallet, userPassword, userEmail;
    public int userId;
    public List<String> user_cart;
    public List<String> user_fine;

    Borrower(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userWallet = "1500";
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userId = ++id;
        this.user_cart = new ArrayList<String>();
        this.user_fine = new ArrayList<String>();
    }

    static void user_login() {
        Library.clear();
        System.out.print("\t----- Login -----\n" +
                "\nEnter your Email : ");
        String email = sc.next();
        System.out.print("Enter your Password : ");
        String pass = sc.next();
        boolean flag = true;
        for (int i = 0; i < Admin.userList.size(); i++) {
            if (Admin.userList.get(i).userEmail.equals(email) && Admin.userList.get(i).userPassword.equals(pass)) {
                flag = false;
                user_panel(i);
            }
        }
        if (flag) {
            System.out.println("\nUser Credentials are Invalid");
            Library.waitUntil();
        }
    }

    static void user_panel(int curr_user) {

        while (true) {
            Library.clear();

            System.out.print("---- Welcome " + Admin.userList.get(curr_user).userName + " ----\n" +
                    "\n1. View all Books and CheckOut" +
                    "\n2. Borrow Book" +
                    "\n3. Past borrowed Books" +
                    "\n4. Return Book" +
                    "\n5. Extended Return Date" +
                    "\n6. View Cart" +
                    "\n7. Remove Book from Cart" +
                    "\n8. List of Previous Fines" +
                    "\n9 .Exit\n" +
                    "\nEnter Your Choice : ");

            int choice = sc.nextInt();
            if (choice == 1) {
                Library.clear();
                Book.show_book("0");
                checkOut_book(curr_user);
            } else if (choice == 2) {
                Library.clear();
                borrow_book(curr_user);
                Library.waitUntil();
            } else if (choice == 3) {
                Library.clear();
                past_borrowedBooks(curr_user);
                Library.waitUntil();
            } else if (choice == 4) {
                Library.clear();
                return_book(curr_user);
                Library.waitUntil();
            } else if (choice == 5) {
                Library.clear();
                returndate_extended(curr_user);
                Library.waitUntil();
            } else if (choice == 6) {
                Library.clear();
                View_cart(curr_user);
                Library.waitUntil();
            } else if (choice == 7) {
                Library.clear();
                remove_cart(curr_user);
                Library.waitUntil();
            } else if (choice == 8) {
                Library.clear();
                fine_history(curr_user);
                Library.waitUntil();
            } else if (choice == 9) {
                break;
            } else {
                System.out.println("\nInvalid Option");
                Library.waitUntil();
            }
        }
    }

    static void borrow_book(int curr_user) {
        if (Admin.userList.get(curr_user).user_cart.size() == 0) {
            System.out.println("\nYou have no Books in your cart\n");
            return;
        }
        if (Integer.parseInt(Admin.userList.get(curr_user).userWallet) < 500) {
            System.out.println("\nYou have insufficient balance in your account");
            return;
        }
        Library.clear();
        System.out.print("\nYour cart Products\n");
        View_cart(curr_user);
        System.out.print("\nDo you want to borrow these Books? (y/n)");
        String confirmation = sc.next();
        if (confirmation.equals("y")) {
            Borrower obj = Admin.userList.get(curr_user);
            for (int i = 0; i < obj.user_cart.size(); i++) {
                for (int j = 0; j < Admin.bookList.size(); j++) {
                    if (obj.user_cart.get(i).equals(Admin.bookList.get(j).book_ISBN)) {
                        System.out.println(Admin.bookList.get(j).book_name +
                                " | " + Admin.bookList.get(j).book_ISBN);
                        int userId = obj.userId;
                        String username = obj.userName;
                        String bookname = Admin.bookList.get(j).book_name;
                        String book_ISBN = Admin.bookList.get(j).book_ISBN;
                        System.out.print("\nWhen will you return the Book enter date (dd/mm/yyyy) : ");
                        String date = sc.next();
                        Admin.bookList.get(j).borrowed_status++;
                        Admin.borrowed_book_detailsList
                                .add(new Borrowered_details(userId, book_ISBN, username, bookname, date, "--/--/----"));
                    }
                }
            }
            obj.user_cart.clear();
            System.out.println("\nThank You for Taking Books from our Library");
        }

    }

    static void remove_cart(int curr_user) {
        View_cart(curr_user);
        if (Admin.userList.get(curr_user).user_cart.size() == 0) {
            return;
        }
        System.out.print("\n\nEnter BookName or Book ISbn to remove : ");
        String name_ISBN = sc.next();
        boolean flag = true;
        for (int i = 0; i < Admin.bookList.size(); i++) {
            if (name_ISBN.equals(Admin.bookList.get(i).book_ISBN)
                    || name_ISBN.equals(Admin.bookList.get(i).book_name)) {
                name_ISBN = Admin.bookList.get(i).book_ISBN;
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("\nEntered Book ISBN is Invalid" +
                    "\nEnter correct Book ISBN");
            Library.waitUntil();
            return;
        }
        System.out.print("\nDo you want to remove (y/n): ");
        String confirmation = sc.next();
        if (confirmation.equals("y")) {
            Admin.userList.get(curr_user).user_cart.remove(name_ISBN);
            System.out.println("\nBook Removed from your cart successfully");
        }
    }

    static void returndate_extended(int curr_user) {
        System.out.print("\nName " + "  ISBN  " + " actualReturn_date " + " borrowed date" + " Returnstatus "
                + " userReturn Date ");
        for (int i = 0; i < Admin.borrowed_book_detailsList.size(); i++) {
            Borrowered_details obj = Admin.borrowed_book_detailsList.get(i);
            if (obj.user_id == Admin.userList.get(curr_user).userId) {
                if (obj.returnStatus.equals("Not Returened")) {
                    System.out.println(
                            "\n" + obj.Book_name + " | " + obj.book_ISBN + " | " + obj.actualReturn_date + " | "
                                    + obj.borrowed_date + " | " + obj.returnStatus + " | " + obj.userReturn_date);
                }
            }
        }
        System.out.print("\nEnter Book Isbn Number to extend date : ");
        String Isbn = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < Admin.borrowed_book_detailsList.size(); i++) {
            Borrowered_details obj = Admin.borrowed_book_detailsList.get(i);
            if (obj.user_id == Admin.userList.get(curr_user).userId) {
                if (Isbn.equals(obj.book_ISBN)) {
                    if (obj.extendTimes > 2) {
                        System.out.println("\nSorry Date Extended Limit has Reached");
                        return;
                    }
                    try {
                        System.out.print("\nHow many Days want to extend : ");
                        int Date = sc.nextInt();
                        Date date2 = sdf.parse(obj.userReturn_date);
                        Date date1 = sdf.parse(Library.actualDate());
                        long mills = Math.abs(date1.getTime() - date2.getTime());
                        long days = TimeUnit.DAYS.convert(mills, TimeUnit.MILLISECONDS);
                        obj.userReturn_date = Library.actualDate((int) days + Date);
                        obj.extendTimes++;
                        System.out.println("\nDate Extended successfully");
                        return;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static void past_borrowedBooks(int curr_user) {
        if (Admin.borrowed_book_detailsList.size() == 0) {
            System.out.println("\nNo records found");
            return;
        }
        System.out.print("\nName " + "  ISBN  " + " actualReturn_date " + " borrowed date" + " Returnstatus "
                + " userReturn Date ");
        for (int i = 0; i < Admin.borrowed_book_detailsList.size(); i++) {
            Borrowered_details obj = Admin.borrowed_book_detailsList.get(i);
            if (obj.user_id == Admin.userList.get(curr_user).userId) {
                System.out.println(
                        "\n" + obj.Book_name + " | " + obj.book_ISBN + " | " + obj.actualReturn_date + " | "
                                + obj.borrowed_date + " | " + obj.returnStatus + " | " + obj.userReturn_date);
            }
        }
    }

    static void checkOut_book(int curr_user) {
        if (Admin.userList.get(curr_user).user_cart.size() > 2) {
            System.out.println("\nYou can Add only three Books At a time\n");
            return;
        }
        System.out.print("\nEnter Book Name or ISBN number to add in Cart : ");
        String name_ISBN = sc.next();
        boolean flag = true;
        for (int i = 0; i < Admin.bookList.size(); i++) {
            if (name_ISBN.equals(Admin.bookList.get(i).book_ISBN)
                    || name_ISBN.equals(Admin.bookList.get(i).book_name)) {
                name_ISBN = Admin.bookList.get(i).book_ISBN;
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("\nEntered Book ISBN is Invalid" +
                    "\nEnter correct Book ISBN");
            Library.waitUntil();
            return;
        }
        Borrower obj = Admin.userList.get(curr_user);
        for (int i = 0; i < obj.user_cart.size(); i++) {
            if (obj.user_cart.get(i).equals(name_ISBN)) {
                System.out.println("\nThis Book is already in your cart" +
                        "\nSo cannot add same book to your cart");
                Library.waitUntil();
                return;
            }
        }
        System.out.print("\nDo you want to add (y/n): ");
        String confirmation = sc.next();
        if (confirmation.equals("y")) {
            obj.user_cart.add(name_ISBN);
            System.out.println("\nBook added to the cart successfully");
        }
    }

    static void View_cart(int curr_user) {

        if (Admin.userList.get(curr_user).user_cart.size() == 0) {
            System.out.println("\nYou have no Books in your cart\n");
            return;
        }
        Borrower obj = Admin.userList.get(curr_user);

        System.out.println("Name " + "  ISBN\n");
        for (int i = 0; i < obj.user_cart.size(); i++) {
            for (int j = 0; j < Admin.bookList.size(); j++) {
                if (obj.user_cart.get(i).equals(Admin.bookList.get(j).book_ISBN)) {
                    System.out.println(Admin.bookList.get(j).book_name +
                            " | " + Admin.bookList.get(j).book_ISBN);
                }
            }
        }
    }

    static void fine_history(int curr_user) {
        System.out.println("fine Date  " + " Fine Status " + "  Fine Amount");
        for (int i = 0; i < Admin.userList.get(curr_user).user_fine.size(); i++) {
            Borrower obj = Admin.userList.get(curr_user);
            String[] arr = obj.user_fine.get(i).split(":");
            System.out
                    .println("\n" + arr[2] + " | " + arr[1] + " | " + arr[0]);
        }
    }

    static void return_book(int curr_user) {
        System.out.println("\nYour Borrowed Books ");
        System.out.print("\nName " + "  ISBN  " + " actualReturn_date " + " borrowed date" + " Returnstatus "
                + " userReturn Date ");
        for (int i = 0; i < Admin.borrowed_book_detailsList.size(); i++) {
            Borrowered_details obj = Admin.borrowed_book_detailsList.get(i);
            if (obj.user_id == Admin.userList.get(curr_user).userId) {
                if (obj.returnStatus.equals("Not Returned")) {
                    System.out.println(
                            "\n" + obj.Book_name + " | " + obj.book_ISBN + " | " + obj.actualReturn_date + " | "
                                    + obj.borrowed_date + " | " + obj.returnStatus + " | " + obj.userReturn_date);
                    System.out.print("\nDo you want to return these books (y/n) : ");
                    String confirmation = sc.next();
                    if (confirmation.equals("y")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date2 = sdf.parse(obj.userReturn_date);
                            Date date1 = sdf.parse(Library.actualDate());
                            long mills = date1.getTime() - date2.getTime();
                            long days = TimeUnit.DAYS.convert(mills, TimeUnit.MILLISECONDS);
                            if (days > 0) {
                                System.out.println("\nYou didn't return book on time so\nYou need to pay fine");
                                int fine = (int) (days < 10 ? days * 2 : days * 4);
                                System.out.println("\nYour Fine amount : " + fine);
                                System.out.print("\nWhether you want to pay as cash or debit from your wallet : (c/w)");
                                String pay = sc.next();
                                if (pay.equals("c")) {
                                    System.out.println("\nThank u");
                                    System.out.println("\nBook return Successfully");
                                } else {
                                    int min = Integer.parseInt(Admin.userList.get(curr_user).userWallet);
                                    min -= fine;
                                    Admin.userList.get(curr_user).userWallet = String.valueOf(min);
                                    String ss = String.valueOf(fine) + ":" + "Book late Return" + ":"
                                            + Library.actualDate();
                                    Admin.userList.get(curr_user).user_fine.add(ss);
                                    System.out.println(
                                            "\nYour Wallet Balance : " + Admin.userList.get(curr_user).userWallet);
                                    System.out.println("\nThank u");
                                    System.out.println("\nBook return Successfully");
                                }
                            } else {
                                System.out.println("\nBook return Successfully");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        obj.returnStatus = "Returned";
                        obj.actualReturn_date = Library.actualDate();
                    }
                }
            }
        }
    }

}

class Borrowered_details {

    static int book_borrowing_id_incr = 1001;

    public int borrowing_id, user_id, extendTimes;

    public String username, Book_name, borrowed_date, userReturn_date,
            actualReturn_date, returnStatus, book_ISBN;

    Borrowered_details(int user_id, String book_ISBN, String username, String book_name, String userReturn_date,
            String actualDate) {
        this.borrowing_id = ++book_borrowing_id_incr;
        this.user_id = user_id;
        this.Book_name = book_name;
        this.book_ISBN = book_ISBN;
        this.username = username;
        this.actualReturn_date = actualDate;
        this.returnStatus = "Not Returned";
        this.borrowed_date = Library.actualDate();
        this.userReturn_date = userReturn_date;
        this.extendTimes = 0;
    }
}