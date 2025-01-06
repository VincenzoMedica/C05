<jsp:useBean id="medicoList" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jsp" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - trova un medico e prenota la tua visita!</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>

<div class="lista_medici">

    <h1>Risultati della ricerca</h1>

<c:if test="${medicoList != null}">
    <ul>
        <c:forEach items="${medicoList}" var="medico">
            <form action="medico-profile" onclick="this.submit()" style="cursor: pointer;">
                <li id="medico-${medico.id}">
                    <h2>${medico.nome} ${medico.cognome}</h2>
                    <h3>${medico.ruolo}</h3>
                    <input type="hidden" name="id" value=${medico.id}>
                </li>
            </form>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${medicoList == null}">
    <h1>ERROR: medicoList null</h1>
</c:if>

</div>

</body>
</html>

<%@include file="/footer.jsp" %>
