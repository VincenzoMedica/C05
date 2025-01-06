<%--
  Created by IntelliJ IDEA.
  User: 39340
  Date: 06/01/2025
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="header.jsp" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - FAQ</title>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>

<div class="faq_container">

    <div class="faq_p">

        <h2> FAQ: prenotazione </h2>

        <ol>
            <li class="li_faq">
                <h4>Come posso cercare un medico per effettuare una prenotazione?</h4>
                <p>Accedi a MedConnect, vai sulla homepage e usa la funzione di ricerca per trovare un medico in base a
                    ruolo, nome o località. Dopo aver trovato un medico, puoi visualizzarne i dettagli e scegliere una
                    disponibilità da prenotare.</p>
            </li>

            <li class="li_faq">
                <h4>Cosa succede dopo che seleziono un medico?</h4>
                <p>MedConnect ti mostrerà i dettagli del medico selezionato e le disponibilità per gli appuntamenti.
                    Potrai scegliere una data e un orario tra quelli disponibili e procedere con la prenotazione.</p>
            </li>

            <li class="li_faq">
                <h4>Posso aggiungere una nota alla mia prenotazione?</h4>
                <p>Sì, puoi inserire una nota facoltativa (massimo 255 caratteri) durante il riepilogo della
                    prenotazione, prima di confermarla.</p>
            </li>

            <li class="li_faq">
                <h4>Come posso confermare la mia prenotazione?</h4>
                <p>Dopo aver scelto data, orario e aggiunto una nota (se necessario), verifica i dettagli nel riepilogo
                    e clicca su "Conferma" per completare la prenotazione.</p>
            </li>

            <li class="li_faq">
                <h4>Riceverò una notifica di conferma della prenotazione?</h4>
                <p>Sì, dopo la conferma, MedConnect mostrerà una notifica (pagina) di conferma e la prenotazione sarà
                    visibile
                    nella sezione "Le mie prenotazioni".</p>
            </li>

            <li class="li_faq">
                <h4>Posso annullare una prenotazione prima di confermarla?</h4>
                <p>Sì, puoi annullare la prenotazione prima della conferma chiudendo la finestra del riepilogo. Sarai
                    riportato ai dettagli del medico precedentemente selezionato.</p>
            </li>

            <li class="li_faq">
                <h4>Cosa succede se MedConnect non riesce a salvare la prenotazione?</h4>
                <p>In caso di errore, MedConnect mostrerà un messaggio che segnala il problema. Potrai riprovare a
                    completare la prenotazione.</p>
            </li>

            <li class="li_faq">
                <h4>Dove posso visualizzare le prenotazioni effettuate?</h4>
                <p>Tutte le prenotazioni effettuate sono visibili nella sezione "Le mie prenotazioni", accessibile dalla
                    parte superiore di ogni pagina della piattaforma.</p>
            </li>

            <li class="li_faq">
                <h4>Come posso modificare o cancellare una prenotazione già effettuata?</h4>
                <p>Puoi gestire la modifica o la cancellazione delle prenotazioni nella sezione "Le mie
                    prenotazioni".</p>
            </li>

            <li class="li_faq">
                <h4>Cosa devo fare se non trovo medici?</h4>
                <p>Se non trovi medici che soddisfano i tuoi criteri, MedConnect ti suggerirà alternative. Puoi anche
                    effettuare una nuova ricerca modificando i parametri.</p>
            </li>
        </ol>

    </div>

    <div class="faq_r">

        <h2> FAQ: recensione </h2>

        <ol>
            <li class="li_faq">
                <h4>Come posso inserire una recensione sulla piattaforma?</h4>
                <p>Accedi a MedConnect e vai nella sezione "Le mie prenotazioni". Qui troverai tutte le prenotazioni
                    (completate e non). Per scrivere una recensione, individua una prenotazione completata e clicca su
                    "Scrivi una recensione".</p>
            </li>

            <li class="li_faq">
                <h4>Quali informazioni devo inserire nella recensione?</h4>
                <p>Il modulo della recensione include un punteggio in stelle (da 1 a 5) e una nota facoltativa, lunga
                    massimo 255 caratteri, in cui puoi descrivere la tua esperienza.</p>
            </li>

            <li class="li_faq">
                <h4>Posso lasciare più di una recensione per lo stesso medico?</h4>
                <p>Sì, puoi lasciare una recensione per ogni prenotazione completata con un medico.</p>
            </li>

            <li class="li_faq">
                <h4>Cosa succede dopo che compilo la recensione?</h4>
                <p>Il sistema verifica che tutti i campi obbligatori siano stati compilati correttamente, salva la
                    recensione e la rende visibile nella sezione "Le mie recensioni". Riceverai una notifica (pagina) di conferma
                    dell’inserimento.</p>
            </li>

            <li class="li_faq">
                <h4>Cosa succede se non compilo tutti i campi obbligatori?</h4>
                <p>Se mancano dei campi obbligatori, MedConnect ti notificherà l’errore e ti chiederà di completare la
                    compilazione prima di procedere.</p>
            </li>

            <li class="li_faq">
                <h4>Cosa succede se inserisco dati in un formato errato?</h4>
                <p>Se i dati non rispettano il formato richiesto, MedConnect segnalerà l’errore, evidenzierà i campi
                    sbagliati e ti permetterà di correggerli.</p>
            </li>

            <li class="li_faq">
                <h4>Cosa succede se il sistema non riesce a salvare la recensione?</h4>
                <p>In caso di errore durante il salvataggio, MedConnect mostrerà un messaggio (pagina) di errore. Potrai
                    riprovare a inviare la recensione in un secondo momento.</p>
            </li>

            <li class="li_faq">
                <h4>Dove posso vedere le recensioni che ho lasciato?</h4>
                <p>Le recensioni salvate con successo sono visibili nella sezione "Le mie recensioni", accessibile dalla
                    parte superiore di ogni pagina della piattaforma.</p>
            </li>

            <li class="li_faq">
                <h4>Posso modificare o eliminare una recensione già pubblicata?</h4>
                <p>Sì, nella sezione "Le mie recensioni" puoi trovare tutte le recensioni pubblicate. Qui troverai
                    pulsanti dedicati per modificarle o eliminarle.</p>
            </li>
        </ol>


    </div>
</div>

</body>

</html>

<%@include file="footer.jsp" %>