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

<div class="login_content">

    <div class="lc_text">
        <h2>Effettua il login al tuo account</h2>
    </div>

    <div class="form_login">
        <form action="login-servlet" method="post">

            <input type="text" id="username" name="username" PLACEHOLDER="Inserisci l'e-mail" required>

            <input type="password" id="password" name="password" PLACEHOLDER="Inserisci la password" required>

            <input type="submit" value="Login">

        </form>
    </div>

    <div class="lc_bot">

        <a href="workInProgress.jsp">Hai dimenticato la password?</a>
        <hr>

        <div class="lc_bot_register">
            <p>Non hai ancora un account?</p> <a href="workInProgress.jsp"> Registrati!</a> <br>
        </div>

        <p class="privacy"> Accedendo acconsenti ai nostri <a href="workInProgress.jsp">Termini e Condizioni</a> e
            confermi di aver letto e compreso la nostra <a href="workInProgress.jsp">Privacy Policy</a>. </p>

    </div>

</div>

<br><br><br>
<c:choose>
    <c:when test="${not empty esito}">
        <p>
        <h4><c:out value="${esito}"/></h4>
        </p>
    </c:when>
    <c:otherwise>
        <p>
            &nbsp;
        </p>
    </c:otherwise>
</c:choose>

</body>
</html>

<%@include file="footer.jsp" %>


