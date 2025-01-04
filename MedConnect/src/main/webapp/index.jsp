<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>

<div class="content">

    <div class="cl">

        <%-- Controllo se l'utente è loggato --%>
        <c:choose>
            <c:when test="${not empty sessionScope.utente}">
                <p>Benvenuto, <strong>${sessionScope.utente.email}</strong>!</p>
                <a href="area-utente-servlet">AreaUtente</a> <!-- da levare -->

                <%--  search medico  --%>
                <div class="form_search">
                <form action="search-medico">

                    <div class="form1">
                    <label for="ruoloNome"> Ruolo/Nome </label>
                    <input type="text" id="ruoloNome" name="ruoloNome" placeholder="es. Oculista Mario Rossi">
                    </div>

                    <div class="form2">
                    <label for="citta"> Città </label>
                    <input type="text" id="citta" name="citta" placeholder="es. Napoli">
                    </div>

                    <input type="submit" value="Ricerca">

                </form>
                </div>
            </c:when>
            <c:otherwise>
                <p>Accedi per usufruire dei servizi.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="cr">
        <!-- img vicino al form di ricerca? -->
    </div>

</div>

</body>
</html>

<%@include file="footer.jsp" %>