package project;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/addToCartAction")
public class addToCartAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String productId = request.getParameter("id");
        int quantity = 1;
        int productPrice = 0;
        int productTotal = 0;
        int cartTotal = 0;

        int z = 0;

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM product WHERE id='" + productId + "'");
            if (rs.next()) {
                productPrice = rs.getInt("price");
                productTotal = productPrice;
            }

            ResultSet rs1 = st.executeQuery("SELECT * FROM cart WHERE product_id='" + productId + "' AND email='" + email + "' AND address IS NULL");
            if (rs1.next()) {
                cartTotal = rs1.getInt("total");
                cartTotal += productTotal;
                quantity = rs1.getInt("quantity");
                quantity += 1;
                z = 1;
            }

            if (z == 1) {
                st.executeUpdate("UPDATE cart SET total='" + cartTotal + "', quantity='" + quantity + "' WHERE product_id='" + productId + "' AND email='" + email + "' AND address IS NULL");
                response.sendRedirect("home.jsp?msg=exist");
            } else {
                PreparedStatement ps = con.prepareStatement("INSERT INTO cart(email, product_id, quantity, price, total) VALUES(?, ?, ?, ?, ?)");
                ps.setString(1, email);
                ps.setString(2, productId);
                ps.setInt(3, quantity);
                ps.setInt(4, productPrice);
                ps.setInt(5, productTotal);
                ps.executeUpdate();
                response.sendRedirect("home.jsp?msg=added");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home.jsp?msg=invalid");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}