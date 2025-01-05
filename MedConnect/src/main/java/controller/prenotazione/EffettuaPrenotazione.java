package controller.prenotazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.disponibilita.Disponibilita;
import model.disponibilita.DisponibilitaDAO;
import model.medico.Medico;
import model.medico.MedicoDAO;
import model.prenotazione.Prenotazione;
import model.prenotazione.PrenotazioneDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet per gestire la prenotazione di una visita medica.
 * Riceve i dati relativi alla prenotazione, li valida e li salva nel sistema.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet("/effettua-prenotazione")
public class EffettuaPrenotazione extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP POST per effettuare una prenotazione.
     * Recupera i dati dalla richiesta, valida e salva la prenotazione nel sistema.
     * In base al risultato, inoltra la risposta a una pagina di successo o errore.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera i dati della prenotazione dalla richiesta
        String nota = request.getParameter("nota");
        int idPaziente = Integer.parseInt(request.getParameter("idPaziente"));
        int idDisponibilita = Integer.parseInt(request.getParameter("idDisponibilita"));

        Prenotazione prenotazione = new Prenotazione();

        // Imposta i dati nella prenotazione e verifica che siano corretti
        if (prenotazione.setNota(nota) && prenotazione.setIdPaziente(idPaziente) && prenotazione.setIdDisponibilita(idDisponibilita)) {
            // Salva la prenotazione nel database
            if (PrenotazioneDAO.doSave(prenotazione) != -1) {
                // Prenotazione salvata con successo
                request.setAttribute("message", "Prenotazione salvata con successo.");
                String address = "/WEB-INF/results/success.jsp";

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            } else {
                // Errore nel salvataggio della prenotazione
                request.setAttribute("message", "Errore durante la creazione della prenotazione.");
                String address = "/WEB-INF/results/error.jsp";

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        } else {
            // Errore nei parametri della prenotazione
            request.setAttribute("message", "Errore nei parametri.");
            String address = "/WEB-INF/results/error.jsp";

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }


    }
}
