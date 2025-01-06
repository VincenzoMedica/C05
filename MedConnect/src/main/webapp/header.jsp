<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: 39340
  Date: 04/01/2025
  Time: 09:44
  To change this template use File | Settings | File Templates.
--%>

<html>

<body>

<div class="header">

    <div class="hl">
        <strong>
            <a href="index.jsp"> MedConnect </a>
        </strong>
    </div>

    <div class="hr">
        <a href="faq.jsp">FAQ</a>
        <a href="prenotazioni-servlet">Le mie prenotazioni</a>
        <a href="workInProgress.jsp">Le mie recensioni</a>
        <a href="workInProgress.jsp">Profilo</a>
        <c:choose>
            <c:when test="${not empty sessionScope.utente}">
                    <a href="logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="login.jsp">Login</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
