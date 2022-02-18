import java.util.*;

public class Railway {

    public static Scanner sc = new Scanner(System.in);

    static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args) {

        while (true) {
            clear();
            System.out.print(
                    "\n----- Welcome Admin -----\n" +
                            "\n1. Admin" +
                            "\n2. User" +
                            "\n3. Exit\n" +
                            "\nEnter your choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Admin.Admin_login();
            } else if (choice == 2) {
                User.frontUser_panel();
            } else if (choice == 3) {
                System.out.println("Thank You Have a Great Day");
                break;
            } else {
                System.out.println("Enter valid Input");
                System.out.println("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }
}

class Admin {

    static boolean called = false;
    static int TrainIdGenerator = 1;

    static int x_axis_length = 1;
    static int y_axis_length = 5;

    static ArrayList<ArrayList<ArrayList<String>>> train = new ArrayList<>(x_axis_length);
    static List<Train> TrainInfo = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static int Admin_login() {
        Railway.clear();
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
            Railway.clear();
            System.out.println("----- Welcome Admin -----\n");
            System.out.println(
                    "1. Add Trains / Routes / Stations\n2. Declare Seats Availability\n3. View Train\n4. Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                add_trainRouteStation();
            } else if (choice == 2) {
                seats_available();
            } else if (choice == 3) {
                view_train();
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid\n");
                System.out.println("Press ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    static void view_train() {
        Railway.clear();
        for (int i = 0; i < train.size(); i++) {
            for (int j = 0; j < train.get(i).size(); j++) {
                for (int k = 0; k < train.get(i).get(j).size(); k++) {
                    System.out.print(train.get(i).get(j).get(k) + " ");
                }
                System.out.println();
            }
            System.out.println("Train Id : " + TrainInfo.get(i).train_Id + "\n" + "Train Name : "
                    + TrainInfo.get(i).train_Name + "\n" + "Train Route : " + TrainInfo.get(i).train_Route + "\n"
                    + "Train Stations : " + TrainInfo.get(i).train_Stations);
            System.out.println(
                    "Availability of Seats : " + getAvailability(Integer.parseInt(TrainInfo.get(i).train_Id) - 1));
            System.out.println("..............");
        }
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }

    static void add_trainRouteStation() {
        Railway.clear();
        System.out.print("Enter Train Name : ");
        String train_name = sc.next();

        System.out.print("Enter Route : ");
        String route = sc.next();
        sc.nextLine();
        sc.nextLine();
        System.out.print("Enter Station : ");
        String station = sc.nextLine();
        train.add(new ArrayList<ArrayList<String>>());
        TrainInfo.add(new Train(String.valueOf(TrainIdGenerator), train_name, route, station));
        TrainIdGenerator++;

        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }

    static int getAvailability(int i) {
        int res = 0;
        for (int j = 0; j < train.get(i).size(); j++) {
            for (int k = 0; k < train.get(i).get(j).size(); k++) {
                if (train.get(i).get(j).get(k).equals("0")) {
                    res++;
                }
            }
        }
        return res;
    }

    static void seats_available() {
        Railway.clear();
        System.out.print("Enter Train Id : ");
        String id = sc.next();

        System.out.print("Enter No. of Seats : ");
        int No = sc.nextInt();

        int Id = Integer.parseInt(id) - 1;
        if (train.size() > Id) {
            if (train.get(Id).size() == 0) {
                for (int i = 0; i < No; i++) {
                    String[] temp1 = TrainInfo.get(Id).train_Stations.split(" ");
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int j = 0; j < temp1.length; j++) {
                        temp.add("0");
                    }
                    train.get(Id).add(temp);
                }
            } else {
                train.get(Id).clear();
                for (int i = 0; i < No; i++) {
                    String[] temp1 = TrainInfo.get(Id).train_Stations.split(" ");
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int j = 0; j < temp1.length; j++) {
                        temp.add("0");
                    }
                    train.get(Id).add(temp);
                }
            }
        } else {
            System.out.println("Enter correct id");
            System.out.println("\nPress ENTER to continue");
            sc.nextLine();
            sc.nextLine();
        }
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }
}

class User {

    static int user_id_incr = 100;
    static boolean isBook = false;
    public static Scanner sc = new Scanner(System.in);

    public String user_name, user_pass;
    public int user_id;

    User(String name, String pass, int id) {
        this.user_name = name;
        this.user_pass = pass;
        this.user_id = id;
    }

    static List<Ticket> book_ticket = new ArrayList<>();
    static List<User> user_list = new ArrayList<>();

    static void frontUser_panel() {
        user_list.add(new User("user", "1234", ++user_id_incr));
        while (true) {
            Railway.clear();
            System.out.print("----- Welcome To User Panel -----\n" +
                    "\n1. New User Registration" +
                    "\n2. Existing User Login" +
                    "\n3. Exit\n" +
                    "\nEnter your Choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                new_userSignUp();
            } else if (choice == 2) {
                Railway.clear();
                System.out.print("Enter your name : ");
                String name = sc.next();
                System.out.print("Enter password : ");
                String pass = sc.next();
                int flag = 0;
                for (int i = 0; i < user_list.size(); i++) {
                    if (name.equals(user_list.get(i).user_name) && pass.equals(user_list.get(i).user_pass)) {
                        exist_userLogin(i);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    System.out.println("Username and password is invalid");
                    System.out.println("\nPress ENTER to continue");
                    sc.nextLine();
                    sc.nextLine();
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid");
                System.out.println("Press ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    static void new_userSignUp() {
        Railway.clear();
        System.out.print("Enter name : ");
        String name = sc.next();
        System.out.print("Enter Password : ");
        String pass = sc.next();
        user_list.add(new User(name, pass, ++user_id_incr));
        System.out.println("\nNew User created successfully");
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }

    static void exist_userLogin(int curr_user) {
        while (true) {
            Railway.clear();
            System.out.print("---- Welcome " + user_list.get(curr_user).user_name + " ----" +
                    "\n1. View Trains and Availability" +
                    "\n2. Book Tickets" +
                    "\n3. View Booking" +
                    "\n4. cancel Booking" +
                    "\n5. Exit\n" +
                    "\nEnter your choice : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                Admin.view_train();
            } else if (choice == 2) {
                book_tickets(curr_user);
            } else if (choice == 3) {
                viewBookings(curr_user);
            } else if (choice == 4) {
                cancleBooking();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("\nInvalid");
                System.out.print("\nPress ENTER to continue");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    static void showSpecificTrain(int i) {
        System.out.print("\n Seats\n");
        for (int j = 0; j < Admin.train.get(i).size(); j++) {
            for (int k = 0; k < Admin.train.get(i).get(j).size(); k++) {
                System.out.print(Admin.train.get(i).get(j).get(k) + " ");
            }
            System.out.println();
        }
        System.out.println("\nTrain Id : " + Admin.TrainInfo.get(i).train_Id + "\n" + "Train Name : "
                + Admin.TrainInfo.get(i).train_Name + "\n" + "Train Route : " + Admin.TrainInfo.get(i).train_Route
                + "\n"
                + "Train Stations : " + Admin.TrainInfo.get(i).train_Stations);
        System.out.println(
                "Availability of Seats : "
                        + Admin.getAvailability(Integer.parseInt(Admin.TrainInfo.get(i).train_Id) - 1));
        System.out.println("..............");
    }

    static void book_tickets(int curr_user) {
        Railway.clear();
        System.out.println("---- Welcome to Ticket Booking center ----\n");

        System.out.print("Enter Train id : ");
        String id = sc.next();
        showSpecificTrain(Integer.parseInt(id) - 1);
        System.out.print("Enter boarding point : ");
        int bo_point = sc.nextInt();
        System.out.print("Enter depature point : ");
        int de_point = sc.nextInt();
        int tid = Integer.parseInt(id) - 1;
        isBook = true;
        int SeatNo = bookSeat(bo_point, de_point, tid, String.valueOf(curr_user));

        if (SeatNo != -1) {
            System.out.println("Your Seat No : " + SeatNo);
        } else {
            book_ticket.add(
                    new Ticket(-1, String.valueOf(curr_user), Admin.TrainInfo.get(tid).train_Name,
                            Admin.TrainInfo.get(tid).train_Route,
                            "Pending", Integer.parseInt(Admin.TrainInfo.get(tid).train_Id), -1, bo_point, de_point));
            System.out.println("Currenly No Seats Available...you are in the waiting list");
        }

        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }

    static int bookSeat(int in, int out, int tId, String curr_user) {
        int res = -1;
        for (int i = 0; i < Admin.train.get(tId).size(); i++) {
            int tot = 0, size = 0;
            for (int k = in - 1; k < out; k++) {
                size++;
                if (Admin.train.get(tId).get(i).get(k).equals("0")) {
                    tot++;
                }
            }
            String username_first = user_list.get(Integer.parseInt(curr_user)).user_name;
            String z = username_first.substring(0, 2) + user_list.get(Integer.parseInt(curr_user)).user_id;
            if (tot == size) {

                for (int k = in - 1; k < out; k++) {
                    Admin.train.get(tId).get(i).set(k, z);
                }
                res = i;
                if (isBook) {
                    book_ticket.add(
                            new Ticket(Ticket.TicketNoGenerator, String.valueOf(curr_user),
                                    Admin.TrainInfo.get(tId).train_Name,
                                    Admin.TrainInfo.get(tId).train_Route,
                                    "Booked", Integer.parseInt(Admin.TrainInfo.get(tId).train_Id), i, in, out));
                    Ticket.TicketNoGenerator++;
                }
                return res;
            }
        }

        return res;
    }

    static void viewBookings(int curr_user) {
        Railway.clear();

        for (int i = 0; i < book_ticket.size(); i++) {
            if (book_ticket.get(i).passenger_ID.equals(String.valueOf(curr_user))) {
                System.out.println("Ticket no     : " + book_ticket.get(i).TicketNo);
                System.out.println("Train Name    : " + book_ticket.get(i).Train_name);
                System.out.println("Train ID      : " + book_ticket.get(i).Train_id);
                System.out.println("Train Route   : " + book_ticket.get(i).Train_Route);
                System.out.println("Train Seat    : " + book_ticket.get(i).Train_seat);
                System.out.println("Station In    : " + book_ticket.get(i).start_seat);
                System.out.println("Station Out   : " + book_ticket.get(i).end_seat);
                System.out.println("Ticket Status : " + book_ticket.get(i).Train_status);
                System.out.println();
                System.out.println("----------------------------------");
            }
        }
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }

    static void bookPending() {
        isBook = false;

        for (int i = 0; i < book_ticket.size(); i++) {
            if (book_ticket.get(i).Train_status.equals("Pending")) {
                int res = bookSeat(book_ticket.get(i).start_seat, book_ticket.get(i).end_seat,
                        book_ticket.get(i).Train_id - 1, book_ticket.get(i).passenger_ID);
                if (res != -1) {
                    book_ticket.get(i).Train_status = "Booked";
                    book_ticket.get(i).TicketNo = Ticket.TicketNoGenerator;
                    book_ticket.get(i).Train_seat = res;

                    Ticket.TicketNoGenerator++;
                }
            }
        }
    }

    static void cancleBooking() {
        Railway.clear();
        System.out.print("Enter Ticket No : ");
        int ticker_no = sc.nextInt();

        for (int i = 0; i < book_ticket.size(); i++) {
            if (book_ticket.get(i).TicketNo == ticker_no) {
                removeSeat(book_ticket.get(i).Train_id - 1, book_ticket.get(i).Train_seat,
                        book_ticket.get(i).start_seat, book_ticket.get(i).end_seat);
                book_ticket.remove(i);
                bookPending();
                break;
            }
        }
        // System.out.println("\nPress enter to continue...");
        System.out.println("\nPress ENTER to continue");
        sc.nextLine();
        sc.nextLine();
    }

    static void removeSeat(int tId, int tSeat, int tStart, int tEnd) {
        for (int j = tStart - 1; j < tEnd; j++) {
            Admin.train.get(tId).get(tSeat).set(j, "0");
        }
    }

}

class Train {
    public String train_Id;
    public String train_Name;
    public String train_Route;
    public String train_Stations;

    Train(String trainId, String trainName, String trainRoute, String trainStations) {
        this.train_Id = trainId;
        this.train_Name = trainName;
        this.train_Route = trainRoute;
        this.train_Stations = trainStations;
    }

}

class Ticket {

    public int TicketNo;
    static int TicketNoGenerator = 1;
    public String Train_name, Train_Route, Train_status, passenger_ID;
    public int start_seat, end_seat, Train_id, Train_seat;

    Ticket(int TicketNo, String pass_id, String name, String route, String status, int id, int train_seat, int start,
            int end) {
        this.Train_name = name;
        this.Train_Route = route;
        this.Train_status = status;
        this.start_seat = start;
        this.end_seat = end;
        this.Train_id = id;
        this.passenger_ID = pass_id;
        this.Train_seat = train_seat;
        this.TicketNo = TicketNo;
    }
}