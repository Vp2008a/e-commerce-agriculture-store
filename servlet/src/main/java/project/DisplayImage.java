package project;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Get database connection from ConnectionProvider
            ConnectionProvider conProvider = new ConnectionProvider();
            con = conProvider.getCon();
            
            // Query to retrieve image data from the database
            pstmt = con.prepareStatement("SELECT image FROM product WHERE id = ?");
            pstmt.setInt(1, Integer.parseInt(request.getParameter("id")));
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Get the BLOB data
                byte[] imageData = rs.getBytes("image");
                
                // Set content type
                response.setContentType("image/jpeg"); // Change this based on your image type
                
                // Write image data to the response output stream
                OutputStream oStream = response.getOutputStream();
                oStream.write(imageData);
                oStream.flush();
            } else {
                response.getWriter().println("Image not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
