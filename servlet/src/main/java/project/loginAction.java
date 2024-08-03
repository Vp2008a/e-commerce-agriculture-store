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

@WebServlet("/loginAction")
public class loginAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String ee = "admin@gmail.com";
        String ps = "admin";

        HttpSession session = request.getSession();

        if (ee.equals(email) && ps.equals(password)) {
            session.setAttribute("email", email);
            response.sendRedirect("adminHome.jsp");
        } else {
            int z = 0;
            try {
                ConnectionProvider conProvider = new ConnectionProvider();
                Connection con = conProvider.getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from users where email='" + email + "' and password='" + password + "'");
                while (rs.next()) {
                    z = 1;
                    session.setAttribute("email", email);
                    response.sendRedirect("home.jsp");
                    return;  // Important to stop further processing after redirect
                }
                if (z == 0) {
                    response.sendRedirect("login.jsp?msg=notexist");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("login.jsp?msg=invalid");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
