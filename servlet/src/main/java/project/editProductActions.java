package project;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/editProductActions")
@MultipartConfig
public class editProductActions extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String active = request.getParameter("active");
        Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
        InputStream imageInputStream = filePart.getInputStream();

        try {
        ConnectionProvider conProvider = new ConnectionProvider();
                Connection con = conProvider.getCon(); 
            PreparedStatement pstmt = con.prepareStatement("UPDATE product SET name=?, category=?, price=?, active=?, image=? WHERE id=?");
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setString(3, price);
            pstmt.setString(4, active);
            pstmt.setBlob(5, imageInputStream);
            pstmt.setString(6, id);
            pstmt.executeUpdate();
            if ("No".equals(active)) {
                PreparedStatement deleteCartStmt = con.prepareStatement("DELETE FROM cart WHERE product_id=? AND address IS NULL");
                deleteCartStmt.setString(1, id);
                deleteCartStmt.executeUpdate();
            }
            response.sendRedirect("allProductEditProduct.jsp?msg=done");
        } catch (SQLException e) {
            e.printStackTrace(); // Logging the exception to the server console
            response.sendRedirect("allProductEditProduct.jsp?msg=wrong");
        }
    }
}
