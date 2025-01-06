<%@ page import="model.prenotazione.Prenotazione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.disponibilita.Disponibilita" %>
<%@ page import="model.medico.Medico" %>
<%@ page import="model.utente.Utente" %>

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
            ArrayList<Disponibilita> disponibilitas = (ArrayList<Disponibilita>) request.getAttribute("disponibilitas");
            ArrayList<Medico> medicos = (ArrayList<Medico>) request.getAttribute("medicos");
            ArrayList<Utente> utentes = (ArrayList<Utente>) request.getAttribute("utentes");
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
        <p class="posizione-text-center">Totale prenotazioni: <%= prenotaziones.size() %>
        </p>
        <br>
        <%
            int indice = 0;
            for (Prenotazione prenotazione : prenotaziones) {

        %>
    </div>

    <div class="info_prenotazione">

        <div class="info_text">

            <h3> Identificativo prenotazione: <%= prenotazione.getId() %> (<%= prenotazione.getStato() %>)
            </h3><br>

            <div class="it_content">
                <div class="it_l">
                    Medico : <%= medicos.get(indice).getNome() %> <%= medicos.get(indice).getCognome() %> <br>
                    Studio : <%= medicos.get(indice).getCitta() %>, <%= medicos.get(indice).getVia() %> <%= medicos.get(indice).getCivico() %> <br>
                </div>

                <div class="it_r">
                    <p><%= disponibilitas.get(indice).getData() %></p> <br>
                    <p><%= disponibilitas.get(indice).getOraIn() %> - <%= disponibilitas.get(indice).getOraFi() %></p> <br>
                </div>
            </div>

            <div class="it_b">
                Nota: <%= prenotazione.getNota() %> <br>
            </div>

        </div>

        <div class="info_button">

            <!--
            <button type="submit" name="idPrenotazione">
                Dettagli della prenotazione
            </button>
            -->

            <%
                if (prenotazione.getStato().equals("Completata") && (!esistenaRecensionePerPrenotazione.get(indice))) {
            %>
            <form action="creazione-inserimento-recensione-servlet" method="post">

                <button type="submit" name="idPrenotazione" value="<%=prenotazione.getId()%>">
                    Scrivi una recensione
                </button>
            </form>
        </div>
        <% } else if (esistenaRecensionePerPrenotazione.get(indice)) { %>
    </div>
        <% } else { %>

        <button type="submit" name="idPrenotazione">
            Modifica
        </button>

        <button type="submit" name="idPrenotazione">
            Cancella
        </button>

    </div>
    <%
        }%>
    <%
                    indice++;
                }
            }
        }
    %>

    <br><br><br>
    <c:choose>
        <c:when test="${not empty esito}">
            <p>
            <h4><c:out value="${esito}"/></h4>
            </p>
        </c:when>
        <c:otherwise>
            <p class="not_p">
                &nbsp;
            </p>
        </c:otherwise>
    </c:choose>

</div>

</div> <!-- non levare questo tag se no rovina il footer -->

</body>
</html>

<%@include file="/footer.jsp" %>
