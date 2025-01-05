
<%@ page contentType = "text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<jsp:useBean id="utente" scope="session" type="model.utente.Utente"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jsp" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>

<div id="success-container" align="center">
    <table class="title">
        <tr>
            <th>
                Success!
                <c:if test="${message != null}">
                    <br> ${message}
                </c:if>
            </th>
        </tr>
    </table>
    <p/>
    <img src="./images/success.png"/><br clear="all"/>

</div>

</body>
</html>

<%@include file="/footer.jsp" %>