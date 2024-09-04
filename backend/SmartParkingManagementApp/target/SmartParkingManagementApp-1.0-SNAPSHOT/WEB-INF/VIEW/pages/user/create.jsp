<%-- 
    Document   : create
    Created on : Aug 19, 2024, 9:09:59 PM
    Author     : trucn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container mt-3">
    <div class="row">
        <div class="col-md-6 col-12 mx-auto">
            <h3>Create a user</h3>
            <hr />
            <c:url value="create" var="action" />
            <form:form class="row" method="POST" action="${action}" enctype="multipart/form-data"
                       modelAttribute="newUser">
                <div class="mb-3 col-12 col-md-6">
                    <c:set var="errorEmail">
                        <form:errors path="email" cssClass="invalid-feedback" />
                    </c:set>
                    <label class="form-label">Email:</label>
                    <form:input type="email" class="form-control ${not empty errorEmail ? 'is-invalid'
                                                                   : ''}" path="email" />
                                ${errorEmail}
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <c:set var="errorPassword">
                            <form:errors path="password" cssClass="invalid-feedback" />
                        </c:set>
                        <label class="form-label">Password:</label>
                        <form:input type="password"
                                    class="form-control ${not empty errorPassword ? 'is-invalid' : ''}" path="password" />
                        ${errorPassword}
                    </div>

                    <div class="mb-3 col-12 col-md-6">
                        <c:set var="errorFirstName">
                            <form:errors path="firstName" cssClass="invalid-feedback" />
                        </c:set>
                        <label class="form-label">First Name:</label>
                        <form:input type="text" class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                    path="firstName" />
                        ${errorFirstName}
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <c:set var="errorLastName">
                            <form:errors path="lastName" cssClass="invalid-feedback" />
                        </c:set>
                        <label class="form-label">Last Name:</label>
                        <form:input type="text" class="form-control ${not empty errorLastName ? 'is-invalid' : ''}"
                                    path="lastName" />
                        ${errorLastName}
                    </div>
                    <div class="mb-3 col-12">
                        <c:set var="errorPhone">
                            <form:errors path="lastName" cssClass="invalid-feedback" />
                        </c:set>
                        <label class="form-label">Phone number</label>
                        <form:input type="number" class="form-control ${not empty errorPhone ? 'is-invalid' : ''}" path="phone" />
                        ${errorPhone}
                    </div>
                    <div class="mb-3 col-12">
                        <label class="form-label">Address:</label>
                        <form:input type="text" class="form-control" path="address" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label for="form-label" class="form-label">Role:</label>

                        <form:select class="form-select" path="roleId">
                            <c:forEach items="${roles}" var="r" >
                                <form:option value="${r.id}">${r.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label for="formFile" class="form-label">Avatar</label>
                        <form:input path="file" type="file" accept=".jpg,.png" class="form-control" id="file" name="file" />
                    </div>
                    <div class="mb-3 col-12">
                        <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview">
                    </div>
                    <div class="mb-5 col-12">
                        <button type="submit" class="btn btn-success">Create</button>
                    </div>
                </form:form>
            </div>

        </div>

    </div>
    <script>
        $(document).ready(() => {
            const avatarFile = $("#file");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({"display": "block"});
            });
        });
    </script>
