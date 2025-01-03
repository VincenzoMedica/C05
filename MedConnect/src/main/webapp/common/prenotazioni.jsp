


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
            <div class="containerArticoliOrdini">

                <div class="ordine-info">
                    <p>
                    <h3><b>Codice ordine: <%= prenotazione.getId() %></b></h3> <br>

                    <!--QUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII-->



                    Totale: <%= ordine.getPrezzo_totale() %><br>
                    Indirizzo: <%= ordine.getVia() %> <%= ordine.getCivico() %> <%= ordine.getCitta() %> <br>
                    <%
                        GregorianCalendar calendar = ordine.getData();
                        Date date = calendar.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String formattedDate = sdf.format(date);
                    %>
                    Data ordine: <%=formattedDate%><br>
                    </p>
                </div>


                <%
                    ArrayList<RiferimentoArticolo> articoliOrdine = riferimentoArticolos.get(ordines.indexOf(ordine));
                    for (RiferimentoArticolo articoloRiferimentoInternos : articoliOrdine) {
                %>
                <div class="containerArticoloSelezionato">
                    <div class="productArticoloSelezionato carousel-ordine">
                        <img class= "mySlides" src="<%=request.getContextPath()%>/immaginiArticoli/<%=articoloRiferimentoInternos.getRiferimento().getNome_immagine_articolo_ordinato()%>" alt="<%=articoloRiferimentoInternos.getRiferimento().getNome_immagine_articolo_ordinato()%>" ><br>
                    </div>
                    <div class="articolo-info">
                        <p>Dati articolo: <br>

                            Id Articolo: <%= articoloRiferimentoInternos.getArticolo().getId_articolo()%><br>
                            Nome: <%= articoloRiferimentoInternos.getArticolo().getNome()%><br>
                            Descrizione: <%= articoloRiferimentoInternos.getArticolo().getDescrizione()%><br>

                            Colore: <%= articoloRiferimentoInternos.getRiferimento().getColore_articolo_ordine() %><br>
                            Taglia: <%= articoloRiferimentoInternos.getRiferimento().getTaglia_articolo_ordine() %><br>
                            Quantita: <%= articoloRiferimentoInternos.getRiferimento().getQuantita_articolo_ordine()%><br>
                            Prezzo: <%= articoloRiferimentoInternos.getRiferimento().getPrezzo_articolo_ordine() %><br>

                        </p>
                    </div>
                </div>
                <%
                    } // Fine del ciclo degli articoli per questo ordine
                %>
            </div>
            <%
                } // Fine del ciclo degli ordini
            %>

        </main>

    </body>
</html>


