<%-- 
    Document   : detail
    Created on : Aug 14, 2024, 10:54:24 PM
    Author     : trucn
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <form class="form-horizontal m-5" action="/action_page.php">
        <h2>${parkingLot.name}</h2>
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">ID: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" value="${parkingLot.id}">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Name: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.name}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Address: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.address}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Total spots: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.totalSpots}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Price per hour: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.pricePerHour}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Description: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.description}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Start time </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.startTime}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">End time: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${parkingLot.endTime}">
            </div>
        </div>
            
                <a class="btn btn-primary" href="<c:url value="/parkingSpots/?parkingLotID=${parkingLot.id}" />">List of parking spot of ${parkingLot.name}</a>
    </form>
            
</div>
