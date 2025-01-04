<jsp:useBean id="medico" scope="request" type="model.medico.Medico"/>
<jsp:useBean id="utente" scope="session" type="model.utente.Utente"/>
<jsp:useBean id="disponibilitaList" scope="request" type="java.util.List"/>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jsp" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> MedConnect - ${medico.nome} ${medico.cognome}</title>
    <link rel="stylesheet" href="css/styles.css"/>

    <style>
        /* Stile per il dialog         #dialog {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            z-index: 1000;
            width: 60%;
            max-width: 800px;
            height: 70%;
            max-height: 600px;
            overflow-y: auto;
            border-radius: 10px;
        }*/


        #dialog-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 999;
        }

        .close-button {
            background: none;
            border: none;
            font-size: 1.5em;
            cursor: pointer;
            float: right;
        }
    </style>

    <script>
        function openDialog(id, data, oraIn, oraFi) {
            document.getElementById("dialog-id").value = id;
            document.getElementById("dialog-data").value = data;
            document.getElementById("dialog-oraIn").value = oraIn;
            document.getElementById("dialog-oraFi").value = oraFi;
            document.getElementById("dialog").style.display = 'block';
            document.getElementById("dialog-overlay").style.display = 'block';
        }

        function closeDialog() {
            document.getElementById("dialog").style.display = 'none';
            document.getElementById("dialog-overlay").style.display = 'none';
        }
    </script>

</head>

<body>

<%--Dialog--%>
<div id="dialog-overlay" onclick="closeDialog()"></div>
<div id="dialog">
    <button class="close-button" onclick="closeDialog()">×</button>
    <div class="dialog_content">
        <h3>Modifica Disponibilità</h3>
        <form method="post" action="effettua-prenotazione">
            <label>Data: <input type="text" id="dialog-data" name="data" readonly/></label><br>
            <label>Ora Inizio: <input type="text" id="dialog-oraIn" name="oraIn" readonly/></label><br>
            <label>Ora Fine: <input type="text" id="dialog-oraFi" name="oraFi" readonly/></label><br>
            <label>Nota: <textarea name="nota" placeholder="Inserisci una nota per la tua prenotazione (max 255 caratteri)."></textarea></label><br>
            <input type="hidden" id="dialog-id" name="idDisponibilita">
            <input type="hidden" name="idPaziente" value="${utente.id}">
            <button type="submit">Conferma</button>
        </form>
    </div>
</div>
<%--Fine Dialog--%>

<div class="profilo_medico">

    <c:if test="${medico != null}">

        <h2>${medico.nome} ${medico.cognome}</h2>
        <p>
            <b>Ruolo :</b> ${medico.ruolo} <br>
            <b>Specializzazione :</b> ${medico.specializzazione} <br>
        </p>

        <hr>

        <div class="medico_biografia">
            <h3>Biografia</h3>
            <p>${medico.biografia}</p>
        </div>

        <hr>

        <div class="medico_info">
            <h3>Informazioni</h3>
            <ul>
                <li><b>Genere :</b> ${medico.genere}</li>
                <li><b>Email :</b> ${medico.email}</li>
                <li><b>Cellulare :</b> ${medico.num_cellulare}</li>
                <li><b>Indirizzo :</b> ${medico.via} ${medico.civico}, ${medico.citta}</li>
            </ul>
        </div>

        <hr>

        <div class="disponibilita_medico">
            <h3>scegli tra le disponibilità e prenota una visita!</h3>
            <div class="availability-container">
                <c:forEach items="${disponibilitaList}" var="disponibilita">
                    <div class="availability-card"
                         onclick="openDialog('${disponibilita.id}','${disponibilita.data}', '${disponibilita.oraIn}', '${disponibilita.oraFi}')">
                        <span class="availability-date">${disponibilita.data}</span>
                        <span class="availability-time">${disponibilita.oraIn} - ${disponibilita.oraFi}</span>
                    </div>
                </c:forEach>
            </div>
        </div>

    </c:if>

    <c:if test="${medico == null}">
        <h1>ERROR: medico null</h1>
    </c:if>

</div>

</body>
</html>

<%@include file="/footer.jsp" %>
