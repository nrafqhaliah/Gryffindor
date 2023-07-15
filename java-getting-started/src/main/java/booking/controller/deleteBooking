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

@WebServlet("/deleteBookingPostgres")
public class deleteBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public deleteBooking() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ic = request.getParameter("ic");

        try {
            Class.forName("org.postgresql.Driver");
            String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "password";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                String sql = "DELETE FROM booking WHERE ic = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, ic);
                    int affectedRows = stmt.executeUpdate();
                    if (affectedRows > 0) {
                        response.sendRedirect("bookingForm.html");
                    } else {
                        response.getWriter().println("Deletion failed: No records deleted");
                        response.getWriter().println("Check" + ic);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
