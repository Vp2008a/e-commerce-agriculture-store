package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.ConnectionProvider;

@WebServlet("/forgotPasswordAction")
public class forgotPasswordAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobileNumber");
        String securityQuestion = request.getParameter("securityQuestion");
        String answer = request.getParameter("answer");
        String newPassword = request.getParameter("newPassword");

        int check = 0;
        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users where email='" + email + "' and mobileNumber='" + mobileNumber + "' and securityQuestion='" + securityQuestion + "' and answer='" + answer + "'");

            while (rs.next()) {
                check = 1;
                st.executeUpdate("update users set password='" + newPassword + "' where email='" + email + "'");
                response.sendRedirect("forgotPassword.jsp?msg=done");
                return; // exit the method to prevent further execution
            }

            if (check == 0) {
                response.sendRedirect("forgotPassword.jsp?msg=invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // redirect to an error page in case of an exception
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // delegate GET requests to POST method
    }
}
