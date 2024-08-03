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
import javax.servlet.http.HttpSession;
import project.ConnectionProvider;

@WebServlet("/changeSecurityQuestionAction")
public class changeSecurityQuestionAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String securityQuestion = request.getParameter("securityQuestion");
        String newAnswer = request.getParameter("newAnswer");
        String password = request.getParameter("password");
        int check = 0;

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users where email='" + email + "' and password='" + password + "'");

            while (rs.next()) {
                check = 1;
                st.executeUpdate("update users set securityQuestion='" + securityQuestion + "', answer='" + newAnswer + "' where email='" + email + "'");
                response.sendRedirect("changeSecurityQuestion.jsp?msg=done");
                return; // exit the method to prevent further execution
            }

            if (check == 0) {
                response.sendRedirect("changeSecurityQuestion.jsp?msg=wrong");
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
