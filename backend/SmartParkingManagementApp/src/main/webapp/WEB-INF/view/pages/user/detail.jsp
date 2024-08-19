<%-- 
    Document   : detail
    Created on : Aug 19, 2024, 6:17:54 PM
    Author     : trucn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <form class="form-horizontal m-5" action="/action_page.php ">
        <h2>${user.firstName} ${user.lastName}</h2>
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">ID: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" value="${user.id}">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">First Name: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${user.firstName}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Last Name: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${user.lastName}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Address: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${user.address}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Email: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${user.email}">
            </div>
        </div>

        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Phone Number: </label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${user.phone}">
            </div>
        </div>


        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Avatar: </label>
            <div class="col-sm-10">
                <image src="${user.avatar}" width="200"/>
            </div>
        </div>

        <h3>List of ${user.firstName} ${user.lastName}'s vehicles</h3>
        <table class="table table-sm vehicle-list">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Plate number</th>                    
                    <th scope="col">Vehicle category</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${vehicles}" >
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.plateNumber}</td>
                        <td>${v.vehicleCategoryId.name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>      
    </form>


</div>
