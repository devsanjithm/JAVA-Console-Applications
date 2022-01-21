
import java.text.SimpleDateFormat;
import java.util.*;

public class Atm {
    static int[] atm_bal = { 10, 10, 10, 15 };
    public static Scanner sc = new Scanner(System.in);
    static Atm[] atm;

    public String User_Name, User_Pin;
    public int User_Balance = 0;
    public int User_attempt;
    public ArrayList<String> User_Statement;

    Atm(String Name, String Pin, int Balance) {
        this.User_Name = Name;
        this.User_Pin = Pin;
        this.User_Balance = Balance;
        this.User_attempt = 1;
    }

    public static int atmbal() {
        int ans = 0;
        for (int i = 0; i < atm_bal.length; i++) {
            if (i == 0)
                ans += atm_bal[i] * 2000;
            if (i == 1)
                ans += atm_bal[i] * 500;
            if (i == 2)
                ans += atm_bal[i] * 200;
            if (i == 3)
                ans += atm_bal[i] * 100;
        }
        return ans;
    }

    public static int[] withdraw(int am) {
        int[] amt_arr = new int[4];
        while (am > 0) {
            while (am >= 2000 && atm_bal[0] > 0) {
                am -= 2000;
                amt_arr[0] += 1;
                atm_bal[0] -= 1;
            }
            while (am >= 500 && atm_bal[1] > 0) {
                am -= 500;
                amt_arr[1] += 1;
                atm_bal[1] -= 1;
            }
            while (am >= 200 && atm_bal[2] > 0) {
                am -= 200;
                amt_arr[2] += 1;
                atm_bal[2] -= 1;
            }
            while (am >= 100 && atm_bal[3] > 0) {
                am -= 100;
                amt_arr[3] += 1;
                atm_bal[3] -= 1;
            }
        }
        return amt_arr;
    }

    public static int admin() {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.println("-----Welcome Admin-----");
            System.out.println("1. Deposit money in Atm");
            System.out.println("2. Check Balance in Atm");
            System.out.println("3. Exit");
            int c = sc.nextInt();
            if (c == 1) {
                System.out.print("\033[H\033[2J");
                System.out.print("Enter 2000 count : ");
                atm_bal[0] += sc.nextInt();
                System.out.println();
                System.out.print("Enter 500 count :");
                atm_bal[1] += sc.nextInt();
                System.out.println();
                System.out.print("Enter 200 count : ");
                atm_bal[2] += sc.nextInt();
                System.out.println();
                System.out.print("Enter 100 count : ");
                atm_bal[3] += sc.nextInt();
                System.out.println("Amount added successfully");
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            } else if (c == 2) {
                System.out.print("\033[H\033[2J");

                System.out.println("Balance in Atm");
                System.out.println();
                System.out.println("2000 count : " + atm_bal[0] + "\nAmount Present : " + atm_bal[0] * 2000);
                System.out.println();
                System.out.println("500 count : " + atm_bal[1] + "\nAmount Present : " + atm_bal[1] * 500);
                System.out.println();
                System.out.println("200 count : " + atm_bal[2] + "\nAmount Present : " + atm_bal[2] * 200);
                System.out.println();
                System.out.println("100 count : " + atm_bal[3] + "\nAmount Present : " + atm_bal[3] * 100);
                System.out.println();
                System.out.println("Total Balance : " + atmbal());
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            } else if (c == 3) {
                break;
            } else {
                System.out.println("Enter the valid case !");
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            }
        }
        return 0;
    }

    public static int atm(int curr_user) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.println("-----Welcome " + atm[curr_user].User_Name + "-----");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Balance");
            System.out.println("4. Mini Statement");
            System.out.println("5. Change Password");
            System.out.println("6. Transaction");
            System.out.println("7. Exit");
            System.out.print("Enter you Option : ");
            int c = sc.nextInt();
            if (c == 1) {
                System.out.println("Enter amount");
                int am = sc.nextInt();
                while (true) {
                    int flag = 0, flag1 = 0, flag2 = 0, th = atm_bal[0], fi = atm_bal[1], tw = atm_bal[2],
                            hu = atm_bal[3];
                    if (am > atmbal()) {
                        System.out.println("Given amt is graeter than atm amount");
                        flag = 1;
                    }
                    if (am > atm[curr_user].User_Balance) {
                        System.out.println("Insufficient Amount In your Account ! \nEnter crt amount");
                        flag2 = 1;
                    }
                    if (am < 100) {
                        flag1 = 1;
                        System.out.println("Enter amount in above 100s");
                    } else {
                        // temporary money check in atm
                        int k = am;
                        int c1 = 0;
                        while (k >= 2000 && th > 0) {
                            c1 += 2000;
                            k -= 2000;
                            th -= 1;
                        }
                        while (k >= 500 && fi > 0) {
                            c1 += 500;
                            k -= 500;
                            fi -= 1;
                        }
                        while (k >= 200 && tw > 0) {
                            c1 += 200;
                            k -= 200;
                            tw -= 1;
                        }
                        while (k >= 100 && hu > 0) {
                            c1 += 100;
                            k -= 100;
                            hu -= 1;
                        }
                        if (c1 != am) {
                            System.out.println("Only 2000 / 500 / 200 / 100 notes are availables");
                            System.out.println("Enter crt demoniations");
                            flag1 = 1;
                        }
                    }
                    if (flag == 1 || flag1 == 1 || flag2 == 1) {
                        System.out.print("enter amout : ");
                        am = sc.nextInt();
                        continue;
                    } else {
                        break;
                    }

                }
                atm[curr_user].User_Balance -= am;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                String date = ft.format(new Date()) + "-----WithDarw----" + am + "-----"
                        + atm[curr_user].User_Balance;
                atm[curr_user].User_Statement.add(date);
                int[] arr = withdraw(am);
                for (int i = 0; i < arr.length; i++) {
                    if (i == 0) {
                        System.out.println("2000 notes = " + arr[i]);
                    }
                    if (i == 1) {
                        System.out.println("500 notes = " + arr[i]);
                    }
                    if (i == 2) {
                        System.out.println("200 notes = " + arr[i]);
                    }
                    if (i == 3) {
                        System.out.println("100 notes = " + arr[i]);
                    }
                }
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            } else if (c == 2) {
                System.out.println("Enter amount");
                int am = sc.nextInt();
                while (true) {
                    if (am % 2000 == 0 || am % 500 == 0 || am % 100 == 0 || am % 200 == 0) {
                        break;
                    } else {
                        System.out.println("Only 2000 / 500 / 200 / 100 notes are Taken");
                        System.out.println("Enter crt demoniations");
                        am = sc.nextInt();
                        continue;
                    }
                }
                atm[curr_user].User_Balance += am;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                String date = ft.format(new Date()) + "-----Deposit-----" + am + "-----"
                        + atm[curr_user].User_Balance;
                for (; am != 0;) {
                    if (am >= 2000) {
                        atm_bal[0] += 1;
                        am -= 2000;
                    } else if (am >= 500) {
                        atm_bal[1] += 1;
                        am -= 500;
                    } else if (am >= 200) {
                        atm_bal[2] += 1;
                        am -= 200;
                    } else {
                        atm_bal[3] += 1;
                        am -= 100;
                    }
                }

                atm[curr_user].User_Statement.add(date);
                System.out.println("Amout Deposited successfully");
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            } else if (c == 3) {
                System.out.println();
                System.out.println("Your Balance is " + atm[curr_user].User_Balance);
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();

            } else if (c == 4) {
                System.out.println("Mini Statement !");
                System.out.println();
                System.out.println("-------time-----------------type----amount-----balance--");
                System.out.println();
                for (int k = 0; k < atm[curr_user].User_Statement.size(); k++) {
                    System.out.println("---" + atm[curr_user].User_Statement.get(k) + "---");
                }
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            } else if (c == 5) {
                System.out.print("Enter the New Password : ");
                String New_Pin = sc.next();
                sc.nextLine();
                atm[curr_user].User_Pin = New_Pin;
                System.out.println("Pin has been Changed !");
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
            } else if (c == 6) {
                System.out.println("Enter the account name You want to transfer");
                String acname = sc.next();
                int flag = 0;

                for (int i = 0; i < atm.length; i++) {
                    if (atm[i].User_Name.equals(acname)) {
                        flag = 1;
                        System.out.println("Enter the amount you want to transfer");
                        int am3 = sc.nextInt();
                        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                        String date = ft.format(new Date()) + "-----Transcation to " + acname + " -----" + am3 + "-----"
                                + atm[curr_user].User_Balance;
                        if (am3 < atm[curr_user].User_Balance) {
                            atm[i].User_Balance += am3;
                            atm[curr_user].User_Balance -= am3;
                            System.out.println("Transaction successful press enter to continue");
                            atm[curr_user].User_Statement.add(date);
                            System.out.println("\nPress ENTER to countinue");
                            sc.nextLine();
                            sc.nextLine();
                            break;
                        } else {
                            System.out.println("Not sufficient balance");
                            System.out.println("\nPress ENTER to countinue");
                            sc.nextLine();
                            sc.nextLine();
                        }
                    }

                }
                if (flag == 0) {

                    System.out.println("enter valid account name");
                }

            }

            else if (c == 7) {
                break;
            } else {
                System.out.println("Enter the valid case !");
                System.out.println();
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        atm = new Atm[2];
        atm[0] = new Atm("User1", "1234", 5000);
        atm[0].User_Statement = new ArrayList<>();
        atm[1] = new Atm("User2", "1234", 6000);
        atm[1].User_Statement = new ArrayList<>();

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.println("Welcome to ATM");
            System.out.println("1 . Admin");
            System.out.println("2 . User");
            System.out.println("3 . Exit");
            System.out.print("Enter your choice : ");
            int c = sc.nextInt();

            if (c == 2) {
                System.out.print("\033[H\033[2J");
                int flag = 1;
                System.out.println("Enter Details to Login");
                System.out.println();
                System.out.print("Enter Name : ");
                String name1 = sc.next();
                System.out.print("Enter Pass : ");
                String pass1 = sc.next();
                for (int i = 0; i < atm.length; i++) {
                    if (atm[i].User_Name.equals(name1) && atm[i].User_Pin.equals(pass1)) {
                        flag = 1;
                        atm(i);
                        break;
                    } else {
                        flag = 0;
                    }
                }
                if (flag == 0) {
                    System.out.println();
                    System.out.println("Enter correct details");
                    System.out.println();
                    System.out.println("Press ENTER to countinue");
                    sc.nextLine();
                    sc.nextLine();
                }
            } else if (c == 1) {
                int flag = 1;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Details to Login");
                System.out.println();
                System.out.print("Enter Name : ");
                String name1 = sc.next();
                System.out.print("Enter Pass : ");
                String pass1 = sc.next();
                String admin = "Admin";
                String adminpass = "1234";
                if (admin.equals(name1) && adminpass.equals(pass1)) {
                    flag = 1;
                    admin();
                } else {
                    flag = 0;
                }
                if (flag == 0) {
                    System.out.println();
                    System.out.println("Enter correct details");
                    System.out.println();
                    System.out.println("Press ENTER to countinue");
                    sc.nextLine();
                    sc.nextLine();
                }
            } else if (c == 3) {
                System.out.println("Thank You Have a Great Day");
                break;
            } else {
                System.out.println("Enter valid Input");
                System.out.println("Press ENTER to countinue");
                sc.nextLine();
                sc.nextLine();
            }
        }

    }
}
