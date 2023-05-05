import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = Database.connect();
        Scanner scan = new Scanner(System.in);
        String username = Transactions.username;
        boolean loggedin = false;
        int choice = 0;
        // Trip.displayByLocations(connection, username, username, choice, null);
        // User.profile(connection, "arjun");
        do {
            System.out.println("**** WELCOME TO CARPOOL ****");
            System.out.println("+--------------------+");
            System.out.println("|       MENU         |");
            System.out.println("+--------------------+");
            System.out.println("| 1) LOGIN           |");
            System.out.println("| 2) SIGN UP         |");
            System.out.println("| 3) EXIT            |");
            System.out.println("+--------------------+");
            System.out.print("Your choice : ");
            choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    loggedin = Transactions.login(connection);
                    if (!loggedin) {
                        System.out.println("/nTry again / Try registering");
                        break;
                    }

                    int option;

                    do {
                        System.out.println("\u001B[36m" + "+-------------------------------+" + "\u001B[0m");
                        System.out.println("\u001B[36m" + "|           MAIN MENU           |" + "\u001B[0m");
                        System.out.println("\u001B[36m" + "+-------------------------------+" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m1) BOOK A TRIP           \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m2) ADD TRIP              \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m3) CANCEL BOOKING        \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m4) CANCEL TRIP           \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m5) EDIT ACCOUNT DETAILS  \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m6) VIEW MY TRIP          \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[33m" + "| \u001B[0m7) VIEW MY BOOKING       \u001B[33m|" + "\u001B[0m");
                        System.out.println(
                                "\u001B[31m" + "| \u001B[0m8) EXIT                  \u001B[31m|" + "\u001B[0m");
                        System.out.println("\u001B[36m" + "+-------------------------------+" + "\u001B[0m");

                        System.out.print("Your choice : ");
                        option = Integer.parseInt(scan.nextLine());

                        switch (option) {
                            case 1:
                                // code for booking a trip
                                System.out.println("You chose option 1: BOOK A TRIP");
                                Transactions.createBooking(connection);;
                                break;
                            case 2:
                                // code for adding a trip
                                System.out.println("You chose option 2: ADD TRIP");
                                Transactions.addtrip(connection);
                                break;
                            case 3:
                                // code for cancelling a booking
                                System.out.println("You chose option 3: CANCEL BOOKING");
                                break;
                            case 4:
                                // code for cancelling a trip
                                System.out.println("You chose option 4: CANCEL TRIP");
                                break;
                            case 5:
                                // code for editing account details
                                System.out.println("You chose option 5: EDIT ACCOUNT DETAILS");

                                System.out.print("Enter new first name: ");
                                String newfirstname = scan.nextLine();

                                System.out.print("Enter new last name: ");
                                String newlastname = scan.nextLine();

                                System.out.print("Enter new password: ");
                                String newPassword = scan.nextLine();

                                System.out.print("Enter new email: ");
                                String newEmail = scan.nextLine();

                                System.out.print("Enter new mobile number: ");
                                String newMobileNumber = scan.nextLine();

                                System.out.print("Enter new gender: ");
                                String newGender = scan.nextLine();
                                User user=new User(username);
                                user.editUser(connection, username, newfirstname, newlastname, newPassword, newEmail,
                                        newMobileNumber, newGender);

                                break;
                            case 6:
                                // view trip

                                break;
                            case 7:
                                // view booking
                                break;

                            case 8:
                                // exit the program
                                System.out.println("You chose option 8: EXIT");
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                                break;
                        }

                        System.out.println(); // add a blank line for readability
                    } while (option != 8);//

                    break;

                case 2:
                    Transactions.register(connection);
                    break;
            }
        } while (choice != 3);
        // if(Transactions.login(connection)){
        // username = Transactions.username;
        // Transactions.createBooking(connection);
        // // addtrip(connection);
        // // Trip.displayByUsername(connection,"daniel");
        // Booking.displayByUsername(connection, username );
        // }
        // Trip.displayByUsername(connection,"daniel");

    }
}
