package project;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/addNewProductAction")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class addNewProductAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String active = request.getParameter("active");
        InputStream inputStream = null;

        Part filePart = request.getPart("image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            PreparedStatement ps = con.prepareStatement("INSERT INTO product (id, name, category, price, active, image) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, category);
            ps.setString(4, price);
            ps.setString(5, active);
            if (inputStream != null) {
                ps.setBlob(6, inputStream);
            }

            ps.executeUpdate();
            response.sendRedirect("addNewProduct.jsp?msg=done");
        } catch (Exception e) {
            response.sendRedirect("addNewProduct.jsp?msg=wrong");
        }
    }
}
