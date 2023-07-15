package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/updateBookingPostgres")
public class updateBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public updateBooking() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ic = request.getParameter("ic");

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

            String sql = "SELECT * FROM booking WHERE ic = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ic);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String pdatetime = rs.getString("pdatetime");
                String rdatetime = rs.getString("rdatetime");
                String paddress = rs.getString("paddress");
                String daddress = rs.getString("daddress");
                String cartype = rs.getString("cartype");
                int bookingID = rs.getInt("bookingID");

                request.setAttribute("name", name);
                request.setAttribute("ic", ic);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("pdatetime", pdatetime);
                request.setAttribute("rdatetime", rdatetime);
                request.setAttribute("paddress", paddress);
                request.setAttribute("daddress", daddress);
                request.setAttribute("cartype", cartype);
                request.setAttribute("bookingID", bookingID);

                RequestDispatcher view = request.getRequestDispatcher("updateBooking.jsp");
                view.forward(request, response);
            } else {
                response.getWriter().println("No booking found with IC: " + ic);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
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
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
            connection.setAutoCommit(false);

            String sql = "UPDATE booking SET name=?, email=?, phone=?, pdatetime=?, rdatetime=?, paddress=?, daddress=?, cartype=? WHERE ic=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, pdatetime);
            stmt.setString(5, rdatetime);
            stmt.setString(6, paddress);
            stmt.setString(7, daddress);
            stmt.setString(8, cartype);
            stmt.setString(9, ic);

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
            } else {
                response.getWriter().println("Failed to update data");
            }

            stmt.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
