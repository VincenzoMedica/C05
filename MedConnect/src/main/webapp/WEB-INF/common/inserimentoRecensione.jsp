<%--
  Created by IntelliJ IDEA.
  User: Vincenzo
  Date: 03/01/2025
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inserimento Recensione</title>
</head>
<body>
<form action="inserimento-recensione-servlet" method="post">
    <!-- Campo nascosto per l'id della prenotazione -->
    <input type="hidden" name="idPrenotazione" value="${idPrenotazione}">

    <!-- Campo per la nota -->
    <label for="nota">Nota:</label>
    <textarea id="nota" name="nota" maxlength="255" required></textarea>
    <small>Max 255 caratteri.</small>

    <!-- Campo per le stelle -->
    <label for="stelle">Stelle (1-5):</label>
    <select id="stelle" name="stelle" required>
        <option value="" disabled selected>Seleziona</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
    </select>

    <!-- Pulsante per inviare il form -->
    <button type="submit">Invia</button>
</form>
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


</body>
</html>
