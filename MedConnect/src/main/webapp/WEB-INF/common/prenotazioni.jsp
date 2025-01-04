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
                    } else{
                        %>
                        <p class="posizione-text-center">Numero di prenotazioni: <%= prenotaziones.size() %></p>
                        <br>
                        <%
                        int indice = 0;
                        for (Prenotazione prenotazione : prenotaziones) {

                            %>

                            <p>
                            <h3><b>
                                Id: <%= prenotazione.getId() %></b><br>
                                Stato: <%= prenotazione.getStato() %> <br>
                                Nota: <%= prenotazione.getNota() %> <br>

                                Data: <%= disponibilitas.get(indice).getData() %> <br>
                                Ora Inizio: <%= disponibilitas.get(indice).getOraIn() %> <br>
                                Ora Fine: <%= disponibilitas.get(indice).getOraFi() %> <br>


                                Nome: <%= medicos.get(indice).getNome() %> <br>
                                Cognome: <%= medicos.get(indice).getCognome() %> <br>


                                Via: <%= medicos.get(indice).getVia() %> <br>
                                Civico: <%= medicos.get(indice).getCivico() %> <br>
                                Citta: <%= medicos.get(indice).getCitta() %> <br>



                                <%
                                if(prenotazione.getStato().equals("Completata") && (!esistenaRecensionePerPrenotazione.get(indice))){
                                    %>
                                    <form action="creazione-inserimento-recensione-servlet" method="post">
                                        <button type="submit" name="idPrenotazione" value="<%=prenotazione.getId()%>">
                                            Scrivi una recensione
                                        </button>
                                    </form>
                                    <%
                                }%>
                            </h3>
                            </p>
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


