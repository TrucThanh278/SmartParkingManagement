<%-- 
    Document   : buyticket
    Created on : Sep 5, 2024, 4:50:22 PM
    Author     : trucn
--%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/payment/initiate" var="action" />
<form action="${action}" method="POST">
    <input type="hidden" name="orderId" value="ORDER1" />
    <input type="hidden" name="amount" value="100000" />
    <button type="submit" class="btn btn-primary">Pay with MoMo</button>
</form>
