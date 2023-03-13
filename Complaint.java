import java.sql.SQLException;

abstract public class Complaint {

     abstract int fileComplaint(int c_id, int userid, String cdesc) throws SQLException;
     abstract void displayComplaint(int userid) throws SQLException;
     abstract void viewComplaint(int userId) throws SQLException;

}
