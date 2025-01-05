<%--
  Created by IntelliJ IDEA.
  User: Vincenzo
  Date: 03/01/2025
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jsp" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - Scrivi recensione</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>

<div class="sr_content">

    <form action="inserimento-recensione-servlet" method="post">

        <!-- Campo nascosto per l'id della prenotazione -->
        <input type="hidden" name="idPrenotazione" value="${idPrenotazione}">

        <!-- Campo per le stelle -->
        <div class="sr_stelle">
            <label for="stelle">Stelle (1-5):</label>
            <select id="stelle" name="stelle" required>
                <option value="" disabled selected>Seleziona</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>

        <!-- Campo per la nota -->
        <div class="sr_nota">
            <label for="nota">Nota:</label>
            <textarea id="nota" name="nota" cols="60" rows="5" maxlength="255"
                      placeholder="Scrivi qui la tua recensione! (max 255 caratteri)."
                      required></textarea>
        </div>

        <!-- Pulsante per inviare il form -->
        <button type="submit">Pubblica</button>

    </form>
</div>

<c:choose>
    <c:when test="${not empty esito}">
        <p id="error-paragraph">
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

<%@include file="/footer.jsp" %>