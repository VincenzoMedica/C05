<jsp:useBean id="medicoList" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>search medico</title>
</head>
<body>
<c:if test="${medicoList != null}">
    <ul>
        <c:forEach items="${medicoList}" var="medico">
            <form action="medico-profile" onclick="this.submit()" style="cursor: pointer;">
                <li>
                    <h2><b>${medico.nome} ${medico.cognome}</b></h2>
                    <h4>${medico.ruolo}</h4>

                    <input type="hidden" name="id" value=${medico.id}>
                </li>
            </form>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${medicoList == null}">
    <h1>ERROR: medicoList null</h1>
</c:if>

</body>
</html>
