package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet("/datadisplay")

public class displayRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //make Connection
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","svist@123");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from csef");
            resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>NAME</th>");
            out.println("<th>marks</th>");
            out.println("</tr>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id")+"</td>");
                out.println("<td>" + rs.getString("name")+"</td>");
                out.println("<td>" + rs.getInt("marks")+"</td>");

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
