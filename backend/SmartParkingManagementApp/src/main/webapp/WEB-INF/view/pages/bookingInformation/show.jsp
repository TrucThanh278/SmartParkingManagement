<%-- 
    Document   : show
    Created on : Sep 4, 2024, 11:50:13 PM
    Author     : trucn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <h3>Booking Information</h3>
    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">Booking ID</th>
                <th scope="col">Start Time</th>
                <th scope="col">End Time</th>
                <th scope="col">Vehicle</th>
                <th scope="col">Payment Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr>
                    <td>${booking.id}</td>
                    <td>${booking.startTime}</td>
                    <td>${booking.endTime}</td>
                    <td>${booking.vehicleId.plateNumber}</td>
                    <td>${booking.paymentStatus ? "Paid" : "Unpaid"}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
