import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    Admin() throws SQLException
    {
        displayCase();
    }

    static void display()
    {
        System.out.println();
        System.out.println("****************************************************************************");
        System.out.println("1. Update User Details");
        System.out.println("2. Delete User Details");
        System.out.println("3. Exit");
        System.out.println("****************************************************************************");
        System.out.println();
    }
    static  void displayCase() throws SQLException
    {

        Scanner sc = new Scanner(System.in);
        DBConnection db = new DBConnection();
        db.DBCon();
        display();
        int Case = sc.nextInt();
        while(Case!=3) {
            switch (Case) {
                case 1: {
                    System.out.println("Enter User Id :");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the Correct Name");
                    String name = sc.nextLine();

                    db.updateUser(userId, name);
                    System.out.println("Updated ");
                    break;
                }
                case 2: {
                    System.out.println("Enter the user id to be deleted: ");
                    int userid = sc.nextInt();
                    db.deleteUser(userid);
                    System.out.println("Deleted");
                    break;
                }
            }
            display();
            Case= sc.nextInt();
        }
    }
}

