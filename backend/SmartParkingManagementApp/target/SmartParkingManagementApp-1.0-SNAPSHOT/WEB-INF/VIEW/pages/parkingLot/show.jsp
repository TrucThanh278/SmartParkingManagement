<%-- 
    Document   : show
    Created on : Aug 12, 2024, 8:56:52 AM
    Author     : trucn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row mt-3 mb-3 justify-content-end ">
       <div class="input-group input-custom-width">
           <input class="form-control border-end-0 border rounded-pill" type="text" value="" id="example-search-input" placeholder="Enter the keyword...">
            <span class="input-group-append">
                <button class="btn btn-outline-primary border-start-0 border rounded-pill ms-n3" type="button">
                    <i class="fa fa-search"></i>
                </button>
            </span>
     </div> 
    </div>
    
    <div class="d-flex justify-content-end">
        <a class="btn btn-primary" href="<c:url value="/parkingLot/create"/>">Add parking lot</a>
    </div>
      
    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Address</th>
                <th scope="col">Total Spots</th>
                <th scope="col">Price Per Hour</th>
                <th scope="col">Start time</th>
                <th scope="col">End time</th>
                <th scope="col">Actions</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${parkingLots}">
                <tr>
                    <td>${p.id}</td>                    
                    <td>${p.name}</td>
                    <td>${p.address}</td>
                    <td>${p.totalSpots}</td>
                    <td>${p.pricePerHour}</td>
                    <td>${p.startTime}</td>
                    <td>${p.endTime}</td>
                    <td>
                        <a  href="<c:url value="/parkingLot/${p.id}" />"
                            class="btn btn-success"><i class="fa fa-eye"></i></a>
                        <a  href="<c:url value="/parkingLot/update/${p.id}" />"
                            class="btn btn-warning"><i class='fas fa-edit'></i></a>
                            <a href="<c:url value="/parkingLot/delete/${p.id}" />"
                            class="btn btn-danger"><i class="fa-solid fa-trash"></i></a>
                    </td>
                </tr> 
            </c:forEach>

        </tbody>
    </table>

</div>
