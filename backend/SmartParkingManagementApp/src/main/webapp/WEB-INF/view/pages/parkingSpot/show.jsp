<%-- 
    Document   : show
    Created on : Aug 15, 2024, 10:37:09 AM
    Author     : trucn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="row justify-content-center">
        <!-- Danh sách các chỗ đỗ xe -->
        <div class="col-md-8 text-center">
            <h3>Parking Spots of ${parkingLot.name}</h3>
            <div class="row">
            <p>Address: ${parkingLot.address}</p>
            <p>Total Spots: ${parkingLot.totalSpots}</p>
            <p>Price Per Hour: ${parkingLot.pricePerHour}</p>
            </div>
            <div class="row">
                <c:forEach var="spot" items="${parkingSpots}">
                    <div class="col-md-2">
                        <div class="card text-center mb-3"
                             style="background-color: <c:if test="${spot.status}">green</c:if><c:if test="${!spot.status}">grey</c:if>;">
                            <a href="<c:url value='/parkingSpot/${spot.id}/bookings' />" class="text-white">
                                <div class="card-body">
                                    <p>${spot.spotNumber}</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <c:if test="${(loop.index + 1) % 5 == 0}">
                        <div class="w-100"></div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
    
    <!-- Phân trang -->
         <c:if test="${totalPages > 1}">
        <div class="pagination-footer d-flex justify-content-between fixed-bottom p-3">
            <form action="<c:url value='/parkingSpots' />" method="get" class="d-flex justify-content-between w-100">
                <input type="hidden" name="parkingLotID" value="${param.parkingLotID}" />
                
                <!-- Nút Trang Trước ở góc trái -->
                <button type="submit" name="page" value="${currentPage - 1}" class="btn btn-primary"
                        ${currentPage == 1 ? 'disabled' : ''}>Trang Trước</button>

                <!-- Nút Trang Sau ở góc phải -->
                <button type="submit" name="page" value="${currentPage + 1}" class="btn btn-primary"
                        ${currentPage == totalPages ? 'disabled' : ''}>Trang Sau</button>
            </form>
        </div>
    </c:if>         
</div>

