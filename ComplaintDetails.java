import java.sql.*;

public class ComplaintDetails extends Complaint {

    public Connection con = null;
    ComplaintDetails() throws SQLException{
        DBConnection db = new DBConnection();
        con = db.DBCon();
    }
    public int fileComplaint(int c_id, int userid, String cdesc) throws SQLException {

        PreparedStatement psmt = (con).prepareStatement("INSERT INTO compliant VALUES(?,?,?);");
        Statement smt = (con).createStatement();
        ResultSet rs = smt.executeQuery("SELECT MAX(c_id) FROM compliant;");
        while (rs.next()) c_id = rs.getInt(1) + 1;

        psmt.setInt(1, c_id);
        psmt.setInt(2, userid);
        psmt.setString(3, cdesc);

        PreparedStatement p = con.prepareStatement("INSERT INTO compliant_status VALUES(?,?,?)");
        p.setInt(1,c_id);
        p.setInt(2,userid);
        p.setString(3,"Issued");
        int j = psmt.executeUpdate();
        int k = p.executeUpdate();
        return c_id;
    }

    public void displayComplaint(int userid) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT * FROM compliant WHERE user_id=?;");
        p.setInt(1, userid);
        ResultSet set = p.executeQuery();
        while (set.next()) {
            System.out.println("Complaint Id : " + set.getInt(1));
            System.out.println("User Id : " + set.getInt(2));
            System.out.println("Complaint Name : " + set.getString(3));
        }
    }

    public void viewComplaint(int userId) throws SQLException {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE C.user_id="+userId+";");
        while (rs.next())
        {
            System.out.println("Complaint Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Complaint Description : "+rs.getString(3));
            System.out.println("Complaint Status      : "+rs.getString(4));
        }
    }

    public void viewIssued(int userId) throws SQLException
    {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE S.c_status = \"Issued\" AND C.user_id="+userId+";");
        while (rs.next())
        {
            System.out.println("Complaint Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Complaint Description : "+rs.getString(3));
            System.out.println("Complaint Status      : "+rs.getString(4));
        }
    }

    public void viewInProgress(int userId) throws SQLException
    {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE S.c_status = \"In Progress\" AND C.user_id="+userId+";");
        while (rs.next())
        {
            System.out.println("Complaint Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Complaint Description : "+rs.getString(3));
            System.out.println("Complaint Status      : "+rs.getString(4));
        }
    }

    public void viewResolved(int userId)throws SQLException
    {
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT C.c_id , C.user_id , C.c_desc , S.c_status FROM compliant as C inner join compliant_status AS S ON C.c_id = S.c_id WHERE S.c_status = \"Resolved\" AND C.user_id="+userId+";");
        while (rs.next())
        {
            System.out.println("Complaint Id          : "+rs.getInt(1));
            System.out.println("User Id               : "+rs.getInt(2));
            System.out.println("Complaint Description : "+rs.getString(3));
            System.out.println("Complaint Status      : "+rs.getString(4));
        }
    }
}
