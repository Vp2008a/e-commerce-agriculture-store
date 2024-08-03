package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.ConnectionProvider;

@WebServlet("/signUpAction")
public class signUpAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobileNumber");
        String securityQuestion = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        String password = request.getParameter("password");
        String address = "";
        String city = "";
        String state = "";
        String country = "";

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobileNumber);
            ps.setString(4, securityQuestion);
            ps.setString(5, answer);
            ps.setString(6, password);
            ps.setString(7, address);
            ps.setString(8, city);
            ps.setString(9, state);
            ps.setString(10, country);
            ps.executeUpdate();
            response.sendRedirect("signup.jsp?msg=valid");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?msg=invalid");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Delegate GET requests to POST method
    }
}
