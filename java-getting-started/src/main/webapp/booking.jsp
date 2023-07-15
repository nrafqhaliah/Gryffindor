<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Details</title>
</head>
<body>
<%
String ic = request.getParameter("ic");
String cartype = request.getParameter("cartype");

%>

    <h1>Booking Details</h1>
    <ul>
        <li>Name: ${requestScope.name}</li>
        <li>IC Number: ${requestScope.ic}</li>
        <li>Email: ${requestScope.email}</li>
        <li>Phone Number: ${requestScope.phone}</li>
        <li>Pickup Date & Time: ${requestScope.pdatetime}</li>
        <li>Return Date & Time: ${requestScope.rdatetime}</li>
        <li>Pickup Address: ${requestScope.paddress}</li>
        <li>Destination Address: ${requestScope.daddress}</li>
        <li>Car Type: ${cartype}</li>
    </ul>

	<a class="btn btn-primary" href="updateBookingPostgres?ic=<%= java.net.URLEncoder.encode(ic, "UTF-8") %>">
    <input type="button" value="UPDATE">
</a>
    
      <input type="button" value="DELETE" onclick="confirmation('<%= java.net.URLEncoder.encode(ic, "UTF-8") %>')">

 
    <script>
        function confirmation(ic) {
            var r = confirm("Are you sure you want to delete?");
            if (r == true) {
                location.href = 'deleteBookingPostgres?ic=' + ic;
            }
        } 
    </script>
</body>
</html>
