<%-- 
    Document   : show
    Created on : Aug 19, 2024, 5:58:10 PM
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
      
    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Phone Number</th>
                <th scope="col">Email</th>
                <th scope="col">Actions</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.id}</td>                    
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.phone}</td>
                    <td>${u.email}</td>
                    <td>
                        <a  href="<c:url value="/user/${u.id}" />"
                            class="btn btn-success"><i class="fa fa-eye"></i></a>
                        <a  href="<c:url value="/user/delete/${u.id}" />"
                            class="btn btn-danger"><i class="fa-solid fa-trash"></i></a>
                    </td>
                </tr> 
            </c:forEach>

        </tbody>
    </table>

</div>

