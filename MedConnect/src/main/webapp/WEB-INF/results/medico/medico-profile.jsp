<jsp:useBean id="medico" scope="request" type="model.medico.Medico"/>
<jsp:useBean id="utente" scope="session" type="model.utente.Utente"/>
<jsp:useBean id="disponibilitaList" scope="request" type="java.util.List"/>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${medico.nome} ${medico.cognome}</title>

    <style>
        /* Stile per il dialog */
        #dialog {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            z-index: 1000;
        }
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
        <h3>Modifica Disponibilità</h3>
        <form method="post" action="effettua-prenotazione">
            <label>Data: <input type="text" id="dialog-data" name="data" readonly/></label><br>
            <label>Ora Inizio: <input type="text" id="dialog-oraIn" name="oraIn" readonly/></label><br>
            <label>Ora Fine: <input type="text" id="dialog-oraFi" name="oraFi" readonly/></label><br>
            <label>Nota: <textarea name="nota"></textarea></label><br>
            <input type="hidden" id="dialog-id" name="idDisponibilita">
            <input type="hidden" name="idPaziente" value="${utente.id}">
            <button type="submit">Conferma</button>
        </form>
    </div>
<%--Fine Dialog--%>

<c:if test="${medico != null}">
    <h2><b>${medico.nome} ${medico.cognome}</b></h2>
    <h3><b>Biografia</b></h3>
    <h4>${medico.biografia}</h4>
    <ul>
        <li><b>Ruolo :</b> ${medico.ruolo}</li>
        <li><b>Specializzazione :</b> ${medico.specializzazione}</li>
        <li><b>Email :</b> ${medico.email}</li>
        <li><b>Genere :</b> ${medico.genere}</li>
        <li><b>Cellulare :</b> ${medico.num_cellulare}</li>
        <li><b>Indirizzo :</b> ${medico.via} ${medico.civico}, ${medico.citta}</li>
    </ul>
    <h3><b>Disponibilità</b></h3>
    <ul>
        <c:forEach items="${disponibilitaList}" var="disponibilita">
            <li style="cursor:pointer;" onclick="openDialog('${disponibilita.id}','${disponibilita.data}', '${disponibilita.oraIn}', '${disponibilita.oraFi}')">
                    ${disponibilita.data} ${disponibilita.oraIn} ${disponibilita.oraFi}
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${medico == null}">
    <h1>ERROR: medico null</h1>
</c:if>

</body>
</html>
