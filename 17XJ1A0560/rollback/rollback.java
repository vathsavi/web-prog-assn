import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Connection_rollBack {
   public static void main(String args[]) throws SQLException {
      //Registering the Driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //Getting the connection
      String url = "jdbc:mysql://localhost/mydatabase";
      Connection con = DriverManager.getConnection(url, "root", "password");
      System.out.println("Connection established......");
      //Setting the auto commit false
      con.setAutoCommit(false);
      //Creating a Statement object
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      //Retrieving the data
      ResultSet rs = stmt.executeQuery("select * from MyPlayers");
      System.out.println("Contents of the table initially");
      while(rs.next()) {
         System.out.print("ID: "+rs.getString("ID")+", ");
         System.out.print("First_Name: "+rs.getString("First_Name")+", ");
         System.out.print("Last_Name: "+rs.getString("Last_Name")+", ");
         System.out.print("Date_Of_Birth: "+rs.getString("Date_Of_Birth")+", ");
         System.out.print("Place_Of_Birth: "+rs.getString("Place_Of_Birth")+", ");
         System.out.print("Country: "+rs.getString("Country"));
         System.out.println("");
      }
      PreparedStatement pstmt = con.prepareStatement("INSERT INTO MyPlayers VALUES (?, ?, ?, ?, ?, ?)");
      pstmt.setInt(1, 8);
      pstmt.setString(2, "Ishant");
      pstmt.setString(3, "Sharma");
      pstmt.setDate(4, new Date(904694400000L));
      pstmt.setString(5, "Delhi");
      pstmt.setString(6, "India");
      pstmt.executeUpdate();
      //Committing the transaction
      con.commit();
      //Deleting the record
      stmt.execute("Delete from MyPlayers where id = 8");
      //Rolling back to the save point
      con.rollback();
      //Contents of the table after the roll-back
      System.out.println("Contents of the table");
      rs = stmt.executeQuery("select * from MyPlayers");
      while(rs.next()) {
         System.out.print("ID: "+rs.getString("ID")+", ");
         System.out.print("First_Name: "+rs.getString("First_Name")+", ");
         System.out.print("Last_Name: "+rs.getString("Last_Name")+", ");
         System.out.print("Date_Of_Birth: "+rs.getString("Date_Of_Birth")+", ");
         System.out.print("Place_Of_Birth: "+rs.getString("Place_Of_Birth")+", ");
         System.out.print("Country: "+rs.getString("Country"));
         System.out.println("");
      }
   }
}