package controller.recensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet per la creazione e l'inserimento di una recensione.
 * Gestisce la richiesta di creazione di una recensione associata a una prenotazione,
 * recuperando l'ID della prenotazione e inoltrando la richiesta alla pagina di inserimento della recensione.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet(name = "creazione-inserimento-recensione-servlet", value = "/creazione-inserimento-recensione-servlet")
public class CreazioneInserimentoRecensioneServlet extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP POST per creare una recensione.
     * Recupera l'ID della prenotazione dalla richiesta, verifica la sua validità e inoltra la richiesta alla pagina del form di inserimento.
     * In caso di errori, invia una risposta con un messaggio di errore.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo;

        // Verifica che l'ID della prenotazione sia presente
        if (request.getParameter("idPrenotazione") == null || request.getParameter("idPrenotazione").isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id della prenotazione a cui scrivere la recensione e' mancante o non valido");
            return;
        }

        int idPrenotazione;
        try {
            // Converte l'ID della prenotazione in un intero
            idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
        } catch (NumberFormatException e) {
            // Gestisce il caso di un formato non valido per l'ID della prenotazione
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato Id della prenotazione a cui scrivere la recensione non valido");
            return;
        }

        // Imposta l'id come attributo della richiesta
        request.setAttribute("idPrenotazione", idPrenotazione);

        // Inoltra alla pagina del form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/common/inserimentoRecensione.jsp");
        dispatcher.forward(request, response);
    }
}


