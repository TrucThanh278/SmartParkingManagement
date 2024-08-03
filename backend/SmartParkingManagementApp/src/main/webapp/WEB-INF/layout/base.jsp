<%-- 
    Document   : base
    Created on : Aug 2, 2024, 10:16:17 AM
    Author     : trucn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <script src="${pageContext.request.contextPath}/js/scripts.js"></script>

    </head>
    <body class="sb-nav-fixed">
        <tiles:insertAttribute name="header" />
        <div id="layoutSidenav">
            <tiles:insertAttribute name="sidebar" />
            <div id="layoutSidenav_content">
                <tiles:insertAttribute name="content" /> 
            </div>

        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>
