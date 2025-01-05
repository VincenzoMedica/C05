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

/**
 * Servlet per l'inserimento di una recensione relativa a una prenotazione.
 * Gestisce la creazione di una recensione, validando i dati, verificando l'esistenza di recensioni precedenti per la prenotazione
 * e salvando la recensione nel database.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet(name = "InserimentoRecensioneServlet", value = "/inserimento-recensione-servlet")
public class InserimentoRecensioneServlet extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP POST per l'inserimento di una recensione.
     * Effettua una serie di validazioni sul formato dell'ID prenotazione, verifica la sessione utente,
     * controlla se una recensione è già presente per la prenotazione e infine salva la recensione.
     * Se ci sono errori nei dati inseriti o nella sessione, viene inviata una risposta di errore.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
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
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sessione non valida.");
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

        // Creazione e validazione della recensione
        Recensione recensione = new Recensione();
        if (!recensione.setId_prenotazione(idPrenotazione)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: id_prenotazione non valido.");
            return;
        }
        if (!recensione.setId_paziente(utente.getId())) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: id_paziente non valido.");
            return;
        }

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

        if (!recensione.setNota(nota)) {
            message += "La nota non può essere vuota. ";
            isValid = false;
        }

        int stelle;
        try {
            stelle = Integer.parseInt(stelleParam);
            if (!recensione.setStelle(stelle)) {
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

        // Salvataggio recensione
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
