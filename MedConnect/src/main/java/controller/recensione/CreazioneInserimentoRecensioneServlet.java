package controller.recensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "creazione-inserimento-recensione-servlet", value = "/creazione-inserimento-recensione-servlet")
public class CreazioneInserimentoRecensioneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera l'id della prenotazione
        String indirizzo;
        if (request.getParameter("idPrenotazione") == null || request.getParameter("idPrenotazione").isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id della prenotazione a cui scrivere la recensione e' mancante o non valido");
            return;
        }

        int idPrenotazione;
        try {
            idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
        } catch (NumberFormatException e) {
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


