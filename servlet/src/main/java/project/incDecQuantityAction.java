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

@WebServlet("/incDecQuantityAction")
public class incDecQuantityAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String id = request.getParameter("id");
        String incdec = request.getParameter("quantity");
        int price = 0;
        int total = 0;
        int quantity = 0;

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cart where email='" + email + "' and product_id='" + id + "' and address is NULL");

            while (rs.next()) {
                price = rs.getInt("price");
                total = rs.getInt("total");
                quantity = rs.getInt("quantity");
            }

            if (quantity == 1 && incdec.equals("dec")) {
                response.sendRedirect("myCart.jsp?msg=notPossible");
            } else if (quantity != 1 && incdec.equals("dec")) {
                total -= price;
                quantity -= 1;
                st.executeUpdate("update cart set total='" + total + "', quantity='" + quantity + "' where email='" + email + "' and product_id='" + id + "' and address is NULL");
                response.sendRedirect("myCart.jsp?msg=dec");
            } else {
                total += price;
                quantity += 1;
                st.executeUpdate("update cart set total='" + total + "', quantity='" + quantity + "' where email='" + email + "' and product_id='" + id + "' and address is NULL");
                response.sendRedirect("myCart.jsp?msg=inc");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("myCart.jsp?msg=error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Delegate GET requests to POST method
    }
}
