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

@WebServlet("/addressPaymentForOrderAction")
public class addressPaymentForOrderAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String mobileNumber = request.getParameter("mobileNumber");
        String paymentMethod = request.getParameter("paymentMethod");
        String transactionId = request.getParameter("transactionId");
        String status = "bill";

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();

            // Update users table
            PreparedStatement ps = con.prepareStatement("update users set address=?, city=?, state=?, country=?, mobileNumber=? where email=?");
            ps.setString(1, address);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, country);
            ps.setString(5, mobileNumber);
            ps.setString(6, email);
            ps.executeUpdate();

            // Update cart table
            PreparedStatement ps1 = con.prepareStatement("update cart set address=?, city=?, state=?, country=?, mobileNumber=?, orderDate=now(), deliveryDate=DATE_ADD(orderDate, INTERVAL 7 DAY), paymentMethod=?, transactionId=?, status=? where email=? and address is NULL");
            ps1.setString(1, address);
            ps1.setString(2, city);
            ps1.setString(3, state);
            ps1.setString(4, country);
            ps1.setString(5, mobileNumber);
            ps1.setString(6, paymentMethod);
            ps1.setString(7, transactionId);
            ps1.setString(8, status);
            ps1.setString(9, email);
            ps1.executeUpdate();

            // Redirect to bill.jsp
            response.sendRedirect("bill.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page if needed
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Delegates GET requests to POST method
    }
}
