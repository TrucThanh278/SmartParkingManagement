<%-- 
    Document   : detail
    Created on : Aug 14, 2024, 10:54:24 PM
    Author     : trucn
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <c:choose>
        <c:when test="${parkingLot.id == null}">
            <c:url value="/parkingLot/create" var="action" />
            <h2 class="mt-3">Create parking lot</h2>
        </c:when>
        <c:otherwise>
            <c:url value="/parkingLot/update" var="action" />
            <h2 class="mt-3" >Update parking lot ${parkingLot.name}</h2>
        </c:otherwise>
    </c:choose>



    <form:form class="form-horizontal m-5" method="post" action="${action}"  modelAttribute="parkingLot">

        <div class="mb-3 row">
            <c:set var="errorName">
                <form:errors path="name" cssClass="invalid-feedback" />
            </c:set>
            <label for="staticEmail" class="col-sm-2 col-form-label">Name: </label>
            <div class="col-sm-10">
                <form:input type="text" class="form-control ${not empty errorName ? 'is-invalid'
                                                              : ''}" path="name" />
                            ${errorName}
                </div>
            </div>

            <div class="mb-3 row">
                <c:set var="errorAddress">
                    <form:errors path="address" cssClass="invalid-feedback" />
                </c:set>
                <label for="staticEmail" class="col-sm-2 col-form-label">Address: </label>
                <div class="col-sm-10">
                    <form:input type="text"  class="form-control ${not empty errorAddress ? 'is-invalid'
                                                                   : ''}" id="staticEmail" path="address"/>
                                ${errorAddress}
                    </div>
                </div>

                <div class="mb-3 row">
                    <c:set var="errorTotalSpots">
                        <form:errors path="address" cssClass="invalid-feedback" />
                    </c:set>
                    <label for="staticEmail" class="col-sm-2 col-form-label">Total spots: </label>
                    <div class="col-sm-10">
                        <form:input type="number"  class="form-control ${not empty errorTotalSpots ? 'is-invalid'
                                                                         : ''}" id="staticEmail" path="totalSpots" name="totalSpots"/>
                                    ${errorTotalSpots}
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

                    <button class="btn btn-success" type="submit">
                        <c:choose>
                            <c:when test="${parkingLot.id == null}">
                                Create
                            </c:when>
                            <c:otherwise>
                                Update
                            </c:otherwise>
                        </c:choose>
                    </button>
                </form:form>

            </div>
