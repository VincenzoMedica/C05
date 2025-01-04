<%@ page import="model.prenotazione.Prenotazione" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: Vincenzo Medica
  Date: 04/04/23
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jsp" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - Le mie prenotazioni</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>

<div class="recap_prenotazioni">
    <div class="counter_prenotazioni">

        <%
            ArrayList<Prenotazione> prenotaziones = (ArrayList<Prenotazione>) request.getAttribute("prenotaziones");
            ArrayList<Boolean> esistenaRecensionePerPrenotazione = (ArrayList<Boolean>) request.getAttribute("esistenaRecensionePerPrenotazione");

            if (prenotaziones == null || prenotaziones.isEmpty()) { %>
        <p>Non ci sono prenotazioni.</p>
        <%
        } else {
            if (esistenaRecensionePerPrenotazione == null || esistenaRecensionePerPrenotazione.isEmpty()) { %>
        <p>Errore, non e' stato possibile individuare la corrispondenza tra recensione e prenotazione.</p>
        <%
        } else {
        %>
        <p class="posizione-text-center">Numero di prenotazioni: <%= prenotaziones.size() %>
        </p>
        <br>
        <%
            int indice = 0;
            for (Prenotazione prenotazione : prenotaziones) {

        %>

    </div>

    <div class="info_prenotazione">

        <div class="info_text">
            Identificativo prenotazione: <%= prenotazione.getId() %><br>
            Stato prenotazione: <%= prenotazione.getStato() %> <br>
            Nota : <%= prenotazione.getNota() %> <br>
        </div>

        <div class="info_button">
            <button type="submit" name="idPrenotazione">
                Dettagli della prenotazione
            </button>

            <button type="submit" name="idPrenotazione">
                Modifica
            </button>

            <button type="submit" name="idPrenotazione">
                Cancella
            </button>
        </div>

        <%
            if (prenotazione.getStato().equals("Completata") && (!esistenaRecensionePerPrenotazione.get(indice))) {
        %>
        <form action="creazione-inserimento-recensione-servlet" method="post">

            <button type="submit" name="idPrenotazione">
                Dettagli della prenotazione
            </button>

            <button type="submit" name="idPrenotazione" value="<%=prenotazione.getId()%>">
                Scrivi una recensione
            </button>
        </form>
        <%
            }%>

        <%
                        indice++;
                    }
                }
            }
        %>

        <c:choose>
            <c:when test="${not empty esito}">
                <p>
                <h4><c:out value="${esito}"/></h4>
                </p>
            </c:when>
            <c:otherwise>
                <p>

                </p>
            </c:otherwise>
        </c:choose>

    </div>

</div>

</body>
</html>

<%@include file="/footer.jsp" %>


