import java.sql.SQLException;
import java.util.Scanner;

public class User extends ComplaintDetails
{
        public static int id;
        static Scanner sc = new Scanner(System.in);

        User(int userid) throws SQLException
        {
            id=userid;
            displayCase();
        }

        static void display()
        {
            System.out.println();
            System.out.println("****************************************************************************");
            System.out.println("1. File Complaint");
            System.out.println("2. View Complaint Details");
            System.out.println("3. View Complaint Status");
            System.out.println("4. View Complaint Status that are Issued");
            System.out.println("5. View Complaint Status that are In progress ");
            System.out.println("6. View Complaint Status that are Resolved");
            System.out.println("7. Exit");
            System.out.println("****************************************************************************");
            System.out.println();
        }
        public void displayCase() throws SQLException
        {
            display();
            int Case = sc.nextInt();
            while(Case!=7) {
                switch (Case) {
                    case 1: {
                        sc.nextLine();

                        System.out.println("Enter Complaint Description: ");
                        String compliantDescription = sc.nextLine();

                        int c_id = fileComplaint(0, id, compliantDescription);
                        System.out.print("Complaint Filed , Your Complaint Id :" + c_id);
                        break;
                    }
                    case 2: {
                        displayComplaint(id);
                        break;
                    }
                    case 3: {
                        viewComplaint(id);
                        break;
                    }
                    case 4:
                    {
                        viewIssued(id);
                        break;
                    }
                    case 5:
                    {
                        viewInProgress(id);
                        break;
                    }
                    case 6:
                    {
                        viewResolved(id);
                        break;
                    }
                }
                display();
                Case = sc.nextInt();
            }
        }

}
