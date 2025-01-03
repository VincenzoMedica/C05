package controller.recensione;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Recensione;
import model.RecensioneDAO;
import model.Utente;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "inserimento-recensione-servlet", value = "/inserimento-recensione-servlet")
public class InserimentoRecensioneServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo;
        String message = "";
        boolean flagControlli = true;

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

        HttpSession session = request.getSession();

        if (session == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sessione non valida");
            return;
        }
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null) {
            if(RecensioneDAO.existsRecensioneForPrenotazione(idPrenotazione)){
                message += "Esiste gia' una recensione per la prenotazione selezionata.";
                flagControlli = false;
                request.setAttribute("esito", "Riprovare, l'inserimento della recensione non ha avuto successo ("+message+").");
                RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
                dispatcher.forward(request, response);
            }else{
                Recensione recensione = new Recensione();
                recensione.setId_prenotazione(idPrenotazione);
                recensione.setId_paziente(utente.getId());
            }


        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

}

