package controller.prenotazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.disponibilita.Disponibilita;
import model.disponibilita.DisponibilitaDAO;
import model.medico.Medico;
import model.prenotazione.Prenotazione;
import model.prenotazione.PrenotazioneDAO;
import model.recensione.RecensioneDAO;
import model.utente.Utente;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Servlet per gestire le prenotazioni di un utente.
 * Recupera le prenotazioni associate all'utente, inclusi i dettagli dei medici e delle disponibilità,
 * e verifica l'esistenza di recensioni per ciascuna prenotazione.
 * In base ai risultati, inoltra la risposta alla pagina delle prenotazioni o alla pagina di errore.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet(name = "prenotazioni-servlet", value = "/prenotazioni-servlet")
public class PrenotazioniServlet extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP GET per visualizzare le prenotazioni dell'utente.
     * Recupera le prenotazioni associate all'utente, i dettagli dei medici e delle disponibilità,
     * e verifica l'esistenza di recensioni per ciascuna prenotazione.
     * In base ai risultati, inoltra la risposta alla pagina delle prenotazioni o alla pagina di errore.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo;
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sessione non valida.");
        }
        Utente utente = (Utente) session.getAttribute("utente");

        // Verifica se l'utente è autenticato
        if (utente != null) {
            ArrayList<Prenotazione> prenotaziones = new ArrayList<>();
            ArrayList<Disponibilita> disponibilitas = new ArrayList<>();
            ArrayList<Medico> medicos = new ArrayList<>();
            ArrayList<Boolean> esistenaRecensionePerPrenotazione = new ArrayList<>();

            // Recupera le prenotazioni dell'utente
            if (PrenotazioneDAO.doRetrieveByIdUtente(utente.getId(), prenotaziones, disponibilitas, medicos)) {
                // Recupera i dettagli delle disponibilità e verifica se esiste una recensione per ogni prenotazione
                for (Prenotazione prenotazione : prenotaziones) {
                    disponibilitas.add(DisponibilitaDAO.doRetrieveById_disponibilita(prenotazione.getIdDisponibilita()));
                    esistenaRecensionePerPrenotazione.add(RecensioneDAO.existsRecensioneForPrenotazione(prenotazione.getId()));
                }

                // Imposta gli attributi per la pagina delle prenotazioni
                request.setAttribute("prenotaziones", prenotaziones);
                request.setAttribute("disponibilitas", disponibilitas);
                request.setAttribute("medicos", medicos);
                request.setAttribute("esistenaRecensionePerPrenotazione", esistenaRecensionePerPrenotazione);

                indirizzo = "/WEB-INF/common/prenotazioni.jsp";
            } else {
                indirizzo = "/WEB-INF/results/error.jsp";
            }

        } else {
            indirizzo = "/login.jsp";
        }
        // Inoltra la risposta alla pagina corretta
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(indirizzo);
        requestDispatcher.forward(request, response);

    }

    /**
     * Gestisce la richiesta HTTP POST, inoltrando la richiesta al metodo doGet.
     *
     * @param req  La richiesta HTTP ricevuta.
     * @param resp La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
