<%-- 
    Document   : update
    Created on : Aug 23, 2024, 9:50:56 PM
    Author     : trucn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <c:url value="/parkingLot/update" var="action" />
    <form:form class="form-horizontal m-5" method="post" action="${action}"  modelAttribute="parkingLot">
        <h2>Update ParkingLot</h2>
        <c:if test="${errMsg != null}">
            <div class="alert alert-danger">
                ${errMsg}
            </div>
        </c:if>
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Name: </label>
            <div class="col-sm-10">
                <form:input type="text" class="form-control" path="name"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Address: </label>
            <div class="col-sm-10">
                <form:input type="text"  class="form-control" id="staticEmail" path="address"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Total spots: </label>
            <div class="col-sm-10">
                <form:input type="number"  class="form-control" id="staticEmail" path="totalSpots"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Price per hour: </label>
            <div class="col-sm-10">
                <form:input type="text"  class="form-control" id="staticEmail" path="pricePerHour"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Description: </label>
            <div class="col-sm-10">
            <form:input type="text"  class="form-control" id="staticEmail" path="description"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Start time </label>
            <div class="col-sm-10">
                <form:input type="datetime-local"  class="form-control" id="staticEmail" path="startTime"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">End time: </label>
            <div class="col-sm-10">
                <form:input type="datetime-local"  class="form-control" id="staticEmail" path="endTime" />
            </div>
        </div>
            
            <button class="btn btn-warning" type="submit">Update</button>
    </form:form>
            
</div>
