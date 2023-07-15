package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addBookingPostgres")
public class addBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addBooking() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ic = request.getParameter("ic");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String pdatetime = request.getParameter("pdatetime");
        String rdatetime = request.getParameter("rdatetime");
        String paddress = request.getParameter("paddress");
        String daddress = request.getParameter("daddress");
        String cartype = request.getParameter("cartype");

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
            System.out.println("Opened database successfully");
            c.setAutoCommit(false);

            String sql = "INSERT INTO booking (name, ic, email, phone, pdatetime, rdatetime, paddress, daddress, cartype) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = c.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, ic);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, pdatetime);
            stmt.setString(6, rdatetime);
            stmt.setString(7, paddress);
            stmt.setString(8, daddress);
            stmt.setString(9, cartype);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                request.setAttribute("name", name);
                request.setAttribute("ic", ic);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("pdatetime", pdatetime);
                request.setAttribute("rdatetime", rdatetime);
                request.setAttribute("paddress", paddress);
                request.setAttribute("daddress", daddress);
                request.setAttribute("cartype", cartype); 
                request.getRequestDispatcher("booking.jsp").forward(request, response);
                System.out.println("Data inserted successfully");
            } else {
                response.getWriter().println("failed");
            }

            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("error");
        }
    }
}
