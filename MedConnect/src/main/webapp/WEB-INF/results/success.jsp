
<%@ page contentType = "text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<jsp:useBean id="utente" scope="session" type="model.utente.Utente"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>MedConnect - Success</title>
</head>
<body>
Sei loggato come "${utente.email}"
<div align="center">
    <table class="title">
        <tr>
            <th>
                Success!
                <c:if test="${message != null}">
                    <br> Message: ${message}
                </c:if>
            </th>
        </tr>
    </table>
    <p/>
    <img src="./images/success.png"/><br clear="all"/>
</div>
</body>
</html>