import java.sql.*;

public class DBConnection {
    public Connection con = null;

    public Connection DBCon() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRM", "root", "Azula@26");
        return con;
    }

    public int insertUser(int userId, String name, String dob, String phno, String address, String email) throws SQLException {
        PreparedStatement psmt = (con).prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?);");
        Statement smt = (con).createStatement();
        ResultSet rs = smt.executeQuery("SELECT MAX(user_id) FROM user;");
        while (rs.next()) userId = rs.getInt(1) + 1;

        psmt.setInt(1, userId);
        psmt.setString(2, name);
        psmt.setString(3, dob);
        psmt.setString(4, phno);
        psmt.setString(5, address);
        psmt.setString(6, email);

        int in = psmt.executeUpdate();

        return userId;
    }

    public void insertPassword(int userId, String password) throws SQLException {
        PreparedStatement psmt = (con).prepareStatement("INSERT INTO password_details VALUES(?,?);");

        psmt.setInt(1, userId);
        psmt.setString(2, password);

        int in = psmt.executeUpdate();
    }

    public boolean checkPassword(int userId, String password) throws SQLException {
        PreparedStatement p = (con).prepareStatement("SELECT passkey FROM password_details WHERE userId=?;");
        p.setInt(1, userId);
        ResultSet set = p.executeQuery();
        String pass = "";
        while (set.next()) pass = set.getString(1);
        if (pass.equals(password))
            return true;
        return false;
    }



    public boolean checkUser(int id) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT userId FROM password_details WHERE userId=?;");
        p.setInt(1, id);
        ResultSet set = p.executeQuery();
        int userId = 0;
        while (set.next()) userId = set.getInt(1);
        if (id == userId) return true;
        else return false;

    }

    public void updatePassword(int userid, String password) throws SQLException {
        PreparedStatement psmt = (con).prepareStatement("UPDATE password_details set passkey = ? where userId = ?;");
        psmt.setInt(2, userid);
        psmt.setString(1, password);
        int i = psmt.executeUpdate();
    }

    public void updateUser(int userId,String newName) throws SQLException
    {
        PreparedStatement p = con.prepareStatement("UPDATE user SET user_name =? WHERE user_id= ?;");
        p.setString(1,newName);
        p.setInt(2,userId);
        int i = p.executeUpdate();

    }

    public void deleteUser(int userId) throws SQLException
    {
        PreparedStatement p = con.prepareStatement("DELETE FROM user WHERE user_id= ?;");
        p.setInt(1,userId);
        int i = p.executeUpdate();

    }

    public  void inProgress(int userid,int c_id) throws SQLException
    {
        PreparedStatement p = con.prepareStatement("UPDATE compliant_status SET c_status =? WHERE c_id= ? AND user_id=? ;");
        p.setString(1,"In Progress");
        p.setInt(2,c_id);
        p.setInt(3,userid);
        int i = p.executeUpdate();
    }

    public  void resolved(int userid,int c_id) throws SQLException
    {
        PreparedStatement p = con.prepareStatement("UPDATE compliant_status SET c_status =? WHERE c_id= ? AND user_id=? ;");
        p.setString(1,"Resolved");
        p.setInt(2,c_id);
        p.setInt(3,userid);
        int i = p.executeUpdate();
    }
}    