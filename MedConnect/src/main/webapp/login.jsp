


<%--
  Created by IntelliJ IDEA.
  User: Vincenzo Medica
  Date: 04/04/23
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html><html lang="it">
<head>



   <!--
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> è utilizzato per
    controllare come una pagina web viene visualizzata su dispositivi mobili e tablet.

    width=device-width: Imposta la larghezza della viewport per adattarsi alla larghezza dello
    schermo del dispositivo.
    initial-scale=1.0: Definisce il livello di zoom iniziale come 1:1, senza zoom predefinito.
    -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accedi</title>

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

