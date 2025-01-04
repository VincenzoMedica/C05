
<%--
  Created by IntelliJ IDEA.
  User: Vincenzo Medica
  Date: 04/04/23
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="header.jsp" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - Login</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

    <body>



        <main>

            <form action="login-servlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <input type="submit" value="Login">

            </form>
            <br><br><br>
            <c:choose>
                <c:when test="${not empty esito}">
                    <p>
                    <h4><c:out value="${esito}" /></h4>
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        &nbsp;
                    </p>
                </c:otherwise>
            </c:choose>

        </main>

    </body>
</html>

<%@include file="footer.jsp" %>


