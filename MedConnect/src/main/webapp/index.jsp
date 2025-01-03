<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>MedConnect</title>
</head>
<body>
<a href="login.jsp">Login</a>

<%-- Controllo se l'utente è loggato --%>
<c:choose>
  <c:when test="${not empty sessionScope.utente}">
    <p>Benvenuto, <strong>${sessionScope.utente.email}</strong>!</p>
    <a href="area-utente-servlet">AreaUtente</a>
  </c:when>
  <c:otherwise>
    <p>Non sei loggato. </p>
  </c:otherwise>
</c:choose>

</body>
</html>
