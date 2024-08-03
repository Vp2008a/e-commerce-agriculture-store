package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.ConnectionProvider;

@WebServlet("/messageUsAction")
public class messageUsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            PreparedStatement ps = con.prepareStatement("insert into message(email, subject, body) values(?, ?, ?)");
            ps.setString(1, email);
            ps.setString(2, subject);
            ps.setString(3, body);
            ps.executeUpdate();
            response.sendRedirect("messageUs.jsp?msg=valid");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("messageUs.jsp?msg=invalid");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Delegate GET requests to POST method
    }
}
