import java.sql.*;
import java.util.Scanner;

public class Technician  extends ComplaintDetails {
    Technician() throws SQLException{

        displayCase();
    }

    public void display()
    {
        System.out.println("****************************************************************************");
        System.out.println("1. File Complaint");
        System.out.println("2. View Complaint Details");
        System.out.println("3. View Complaint Status");
        System.out.println("4. View Complaint Status that are Issued");
        System.out.println("5. View Complaint Status that are In progress ");
        System.out.println("6. View Complaint Status that are Resolved");
        System.out.println("7. Changed Complaint Status to In progress");
        System.out.println("8. Changed Complaint Status to Resolved");
        System.out.println("9. Exit");
        System.out.println("****************************************************************************");
    }
    public void displayCase() throws SQLException {


        DBConnection db = new DBConnection();
        con = db.DBCon();
        Scanner sc = new Scanner(System.in);

        display();
        int Case = sc.nextInt();

        while(Case !=9) {

            sc.nextLine();

            switch (Case) {
                case 1: {

                    System.out.println("Enter User Id: ");
                    int id = sc.nextInt();

                    System.out.println("Enter Complaint Name: ");
                    String complaintName = sc.nextLine();

                    System.out.println("Enter Complaint Description: ");
                    String compliantDescription = sc.nextLine();

                    int c_id = fileComplaint(0, id, compliantDescription);
                    System.out.print("Complaint Filed , Your Complaint Id :" + c_id);
                    break;
                }
                case 2: {

                    System.out.println("Enter User Id: ");
                    int id = sc.nextInt();

                    displayComplaint(id);
                    break;
                }
                case 3: {

                    System.out.println("Enter User Id: ");
                    int id = sc.nextInt();

                    viewComplaint(id);
                    break;
                }
                case 4:
                {
                    viewIssued(0);
                    break;
                }
                case 5:
                {
                    viewInProgress(0);
                    break;
                }
                case 6:
                {
                    viewResolved(0);
                    break;
                }
                case 7: {

                    System.out.println("Enter User Id: ");
                    int id = sc.nextInt();

                    System.out.println("Enter Complaint Id: ");
                    int cId = sc.nextInt();
                    db.inProgress(id, cId);
                    System.out.println("Updated");
                    break;
                }
                case 8: {

                    System.out.println("Enter User Id: ");
                    int id = sc.nextInt();

                    System.out.println("Enter Complaint Id: ");
                    int cId = sc.nextInt();

                    db.resolved(id, cId);
                    System.out.println("Updated");
                    break;
                }
                case 9:
                    System.exit(0);
            }
            display();
            Case=sc.nextInt();
        }
    }

    public void viewIssued(int userId) throws SQLException
    {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE S.c_status = \"Issued\";");
        while (rs.next())
        {
            System.out.println("Compliant Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Compliant Description : "+rs.getString(3));
            System.out.println("Compliant Status      : "+rs.getString(4));
        }
    }

    public void viewInProgress(int userId) throws SQLException
    {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE S.c_status = \"In Progress\";");
        while (rs.next())
        {
            System.out.println("Compliant Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Compliant Description : "+rs.getString(3));
            System.out.println("Compliant Status      : "+rs.getString(4));
        }
    }

    public void viewResolved(int userId)throws SQLException
    {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE S.c_status = \"Resolved\" ;");
        while (rs.next())
        {
            System.out.println("Compliant Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Compliant Description : "+rs.getString(3));
            System.out.println("Compliant Status      : "+rs.getString(4));
        }
    }

}
