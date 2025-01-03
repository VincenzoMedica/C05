<%@ page import="model.Prenotazione" %>
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

<!DOCTYPE html><html lang="it">
<head>



   <!--
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> è utilizzato per
    controllare come una pagina web viene visualizzata su dispositivi mobili e tablet.

    width=device-width: Imposta la larghezza della viewport per adattarsi alla larghezza dello
    schermo del dispositivo.
    initial-scale=1.0: Definisce il livello di zoom iniziale come 1:1, senza zoom predefinito.
    -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni</title>

        </head>
    <body>

        <main>
            <%
                ArrayList<Prenotazione> prenotaziones = (ArrayList<Prenotazione>) request.getAttribute("prenotaziones");

                if (prenotaziones == null || prenotaziones.isEmpty()) { %>
                    <p>Non ci sono ordini.</p>
                    <%
                } else { %>
                    <p class="posizione-text-center">Numero di prenotazioni: <%= prenotaziones.size() %></p>
                    <br>
                    <%
                }
                for (Prenotazione prenotazione : prenotaziones) {
                    %>

                    <p>
                    <h3><b>
                        Id: <%= prenotazione.getId_prenotazione() %></b><br>
                        Stato: <%= prenotazione.getStato() %> <br>
                        Nota: <%= prenotazione.getNota() %> <br>
                        <%
                        if(prenotazione.getStato().equals("Completata")){
                        %>
                            <form action="creazione-inserimento-recensione-servlet" method="post">
                                <button type="submit" name="idPrenotazione" value="<%=prenotazione.getId_prenotazione()%>">
                                    Scrivi una recensione
                                </button>
                            </form>
                            <%


                        }%>

                    </h3>
                    </p>
                <%}%>
            <br><br><br>
            <c:choose>
                <c:when test="${not empty esito}">
                    <p>
                    <h4><c:out value="${esito}" /></h4>
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        &nbsp;
                    </p>
                </c:otherwise>
            </c:choose>
        </main>

    </body>
</html>


