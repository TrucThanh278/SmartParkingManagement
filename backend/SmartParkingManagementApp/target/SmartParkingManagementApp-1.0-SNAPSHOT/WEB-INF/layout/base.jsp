<%-- 
    Document   : base
    Created on : Aug 2, 2024, 10:16:17 AM
    Author     : trucn
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <!-- FullCalendar Scheduler CSS -->
        <link href='https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@5.10.1/main.min.css' rel='stylesheet' />

        <!-- jQuery -->
        <script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>

        <!-- FullCalendar Scheduler JS -->
        <script src='https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@5.10.1/main.min.js'></script>
    
    
        
        <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
        <script src="<c:url value="/js/scripts.js"/>" ></script>
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
