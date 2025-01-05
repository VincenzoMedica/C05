<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/header.jsp" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - Error</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>
<div id="error-container" class="error-container">
    <h1>Oops!</h1>
    <p>Qualcosa è andato storto.</p>
    <p>Error <%= request.getAttribute("statusCode") != null ? request.getAttribute("statusCode") : "Non disponibile" %>
    </p>
    <p>Messaggio
        Errore: <%= request.getAttribute("exception") != null ? ((Exception) request.getAttribute("exception")).getMessage() : "Non disponibile" %>
    </p>
    <p>Tipo
        Eccezione: <%= request.getAttribute("exception") != null ? ((Exception) request.getAttribute("exception")).getClass().getName() : "N/A" %>
    </p>
    </p>
    <a href="<%= request.getContextPath() %>">Torna alla Home</a>
</div>

</body>
</html>

<%@include file="/footer.jsp" %>