package sesh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class record
 */
@WebServlet("/record")
public class record extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public record() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Statement stmt=null;
        ResultSet rs=null;
        Connection conn= null;
   
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType =
        "<!doctype html" +
        "transitional//en\">\n";
        out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body>");
        try
        {
             // Register JDBC driver
             Class.forName("oracle.jdbc.driver.OracleDriver");

             // Open a connection
             conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "local", "test");

             // Execute SQL query
             stmt = conn.createStatement();
             String sql;
             sql = "SELECT * FROM Employee";
             rs = stmt.executeQuery(sql);
             out.println("<table border=1 >");
             out.println("<caption><h2>Employee Record</h2></caption>");

             out.println("<tr style='background-color:#ffffb3; color:red'>");
             out.println("<th>name</th>");
             out.println("<th>id</th>");
             out.println("<th>gender</th>");
             out.println("</tr>");
             // Extract data from result set
             while(rs.next())
             {
                  //Retrieve by column name
                  String name  = rs.getString("name");                 
                  String id = rs.getString("id");
                  String gender = rs.getString("gender");

                  //Display values
                  out.println("<tr style='background-color:#b3ffd9;'>");
                  out.println("<td>" + id + "</td>");
                  out.println("<td>" + name + "</td>");
                  out.println("<td>" + gender + "</td>");                  
                  out.println("</tr>");
             }
             out.println("</table>");
             out.println("</bod;=y></html>");

             // Clean-up environment
             rs.close();
             stmt.close();
             conn.close();
        }
        catch(SQLException se)
        {
             se.printStackTrace();
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
        finally
        {
             //finally block used to close resources
             try
             {
                  if(stmt!=null)
                       stmt.close();
             }
             catch(SQLException se2)
             {}// nothing we can do
             try
             {
             if(conn!=null)
                  conn.close();
             }
             catch(SQLException se)
             {
             se.printStackTrace();
             }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
