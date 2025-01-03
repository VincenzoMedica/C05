package controller.recensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

import java.io.IOException;

@WebServlet(name = "inserimento-recensione-servlet", value = "/inserimento-recensione-servlet")
public class InserimentoRecensioneServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";

        // Validazione ID prenotazione
        String idPrenotazioneParam = request.getParameter("idPrenotazione");
        if (idPrenotazioneParam == null || idPrenotazioneParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id della prenotazione mancante o non valido.");
            return;
        }

        int idPrenotazione;
        try {
            idPrenotazione = Integer.parseInt(idPrenotazioneParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato ID della prenotazione non valido.");
            return;
        }

        // Recupero sessione utente
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sessione non valida.");
            return;
        }

        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Verifica esistenza recensione
        if (RecensioneDAO.existsRecensioneForPrenotazione(idPrenotazione)) {
            message = "Esiste già una recensione per la prenotazione selezionata.";
            request.setAttribute("esito", "Riprovare, l'inserimento della recensione non ha avuto successo (" + message + ").");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
            dispatcher.forward(request, response);
            return;
        }

        // Recupero dati recensione
        Recensione recensione = new Recensione();
        recensione.setId_prenotazione(idPrenotazione);
        recensione.setId_paziente(utente.getId());

        int idMedico = RecensioneDAO.getIdMedicoByPrenotazione(idPrenotazione);
        if (idMedico < 1) {
            message = "Medico non trovato.";
            request.setAttribute("esito", "Riprovare, l'inserimento della recensione non ha avuto successo (" + message + ").");
            RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
            dispatcher.forward(request, response);
            return;
        }
        recensione.setId_medico(idMedico);

        // Validazione parametri nota e stelle
        String nota = request.getParameter("nota");
        String stelleParam = request.getParameter("stelle");
        boolean isValid = true;

        if (nota == null || nota.trim().isEmpty()) {
            message += "La nota non può essere vuota. ";
            isValid = false;
        }

        int stelle = 0;
        try {
            stelle = Integer.parseInt(stelleParam);
            if (stelle <= 0 || stelle >= 6) {
                message += "Il numero di stelle deve essere compreso tra 1 e 5. ";
                isValid = false;
            }
        } catch (NumberFormatException e) {
            message += "Il numero di stelle deve essere un numero valido. ";
            isValid = false;
        }

        if (!isValid) {
            request.setAttribute("esito", "Errore nei dati inseriti (" + message + ").");
            RequestDispatcher dispatcher = request.getRequestDispatcher("creazione-inserimento-recensione-servlet");
            dispatcher.forward(request, response);
            return;
        }

        // Imposta dati recensione e salvataggio
        recensione.setNota(nota);
        recensione.setStelle(stelle);

        if (!RecensioneDAO.doSave(recensione)) {
            message = "Inserimento: L'inserimento non ha avuto successo, nessuna riga inserita.";
        } else {
            message = "Inserimento: Inserimento effettuato con successo.";
        }

        // Esito finale
        request.setAttribute("esito", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
        dispatcher.forward(request, response);
    }
}
