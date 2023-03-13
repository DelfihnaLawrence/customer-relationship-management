import java.sql.*;
import java.util.Scanner;
public class Main
{
    public static void displayCase()
    {
        System.out.println();
        System.out.println("****************************************************************************");
        System.out.println("1. Register ");
        System.out.println("2. Login ");
        System.out.println("3. Forget Password? ");
        System.out.println("4. Exit ");
        System.out.println("****************************************************************************");
        System.out.println();
    }

    public static void findUser(int userid) throws SQLException
    {
        if(userid==101)
        {
            Admin admin = new Admin();
        }
        else if(userid==102 || userid==105)
        {
            Technician tech = new Technician();
        }
        else{
            User user = new User(userid);
        }
    }
    public static boolean confirmPassword(String pass)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Re-enter password: ");
        String npass= sc.nextLine();

        if(pass.equals(npass))
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args)  throws SQLException {

        DBConnection db = new DBConnection();
        db.DBCon();
        Scanner sc = new Scanner(System.in);
        displayCase();
        int inputCase = sc.nextInt();
        while(inputCase!=4)
        {
            switch (inputCase) {
                case 1: {
                    sc.nextLine();

                    System.out.println("Enter User Name: ");
                    String userName = sc.nextLine();

                    System.out.println("Enter User DOB: ");
                    String userDOB = sc.nextLine();

                    System.out.println("Enter User Email: ");
                    String userEmail = sc.nextLine();

                    System.out.println("Enter User Phone Number: ");
                    String userPhno = sc.nextLine();

                    System.out.println("Enter User Address: ");
                    String userAddress = sc.nextLine();

                    int userid = db.insertUser(0, userName, userDOB, userPhno, userAddress, userEmail);

                    System.out.println("Your User Id : "+userid);

                    System.out.println("Enter Password : ");
                    String pass = sc.nextLine();
                    boolean result = confirmPassword(pass);
                    while (!result) {
                        System.out.println("Passwords didn't match! , Please enter correct password! ");
                        System.out.println("Enter Password : ");
                        pass = sc.nextLine();
                        result = confirmPassword(pass);
                    }

                    db.insertPassword(userid, pass);
                    break;
                }
                case 2: {

                    System.out.println("****************************************************************************");
                    System.out.println("                         WELCOME BACK !!!!!                                 ");
                    System.out.println("****************************************************************************");

                    System.out.println("Enter User Id: ");
                    int userid = sc.nextInt();

                    System.out.println("Enter Password: ");
                    sc.nextLine();
                    String pass = sc.nextLine();

//                System.out.println(userid);
//                System.out.println(pass);

                    boolean result = db.checkPassword(userid, pass);

                    while (!result) {
                        System.out.println("Incorrect password or userId! . Try Again!!!");
                        System.out.println("Enter User Id: ");
                        int id = sc.nextInt();

                        sc.nextLine();

                        System.out.println("Enter Password: ");
                        String passw = sc.nextLine();
                        result = db.checkPassword(id, passw);
                    }

                    if (result)
                        System.out.println("Verified");

                    findUser(userid);
                    break;
                }
                case 3: {
                    System.out.println("Forget Password ? ");
                    System.out.println("Enter User Id: ");
                    int id = sc.nextInt();
                    String password = "";
                    sc.nextLine();
                    boolean result = db.checkUser(id);
                    while (!result) {
                        System.out.println("Incorrect UserId");
                        System.out.println("Enter User Id: ");
                        id = sc.nextInt();
                        result = db.checkUser(id);
                    }
                    System.out.println("Enter New Password: ");
                    password = sc.nextLine();
                    db.updatePassword(id, password);
                    System.out.println("Password Updated");
                    break;
                }
                case 4: {
                    System.exit(0);
                }
            }
            displayCase();
            inputCase= sc.nextInt();
        }


    }
}