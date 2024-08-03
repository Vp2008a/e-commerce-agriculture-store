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

@WebServlet("/addChangeAddressAction")
public class addChangeAddressAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            PreparedStatement ps = con.prepareStatement("update users set address=?, city=?, state=?, country=? where email=?");
            ps.setString(1, address);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, country);
            ps.setString(5, email);
            ps.executeUpdate();
            response.sendRedirect("addChangeAddress.jsp?msg=valid");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addChangeAddress.jsp?msg=invalid");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Delegate GET requests to POST method
    }
}
