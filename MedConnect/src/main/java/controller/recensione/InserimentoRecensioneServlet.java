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
import model.UtenteDAO;


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
                request.setAttribute("esito", "Riprovare, l'inserimento della recensione non ha avuto successo ("+message+").");
                RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
                dispatcher.forward(request, response);
            }else{
                boolean flagControllo = true;
                Recensione recensione = new Recensione();
                recensione.setId_prenotazione(idPrenotazione);
                recensione.setId_paziente(utente.getId());
                int id_medico = RecensioneDAO.getIdMedicoByPrenotazione(idPrenotazione);
                if(id_medico >= 1){
                    recensione.setId_medico(id_medico);

                    String nota = request.getParameter("nota");
                    String stelleString = request.getParameter("stelle");

                    // Controllo se i campi richiesti non sono nulli o vuoti
                    if (nota == null || nota.trim().isEmpty()) {
                        message += "La nota non può essere vuota. ";
                        flagControllo = false;
                    }
                    if (stelleString == null || stelleString.trim().isEmpty()) {
                        message += "Errore: Il campo stelle è obbligatorio. ";
                        flagControllo = false;
                    }
                    int stelle = 0;
                    try {
                        stelle = Integer.parseInt(stelleString);
                        if (stelle <= 0 || stelle >= 6) {
                            message += "Errore: Il numero di stelle deve essere maggiore di zero e minore di sei. ";
                            flagControllo = false;
                        }
                    } catch (NumberFormatException e) {
                        message += "Errore:  Il numero di stelle deve essere un numero valido. ";
                        flagControllo = false;
                    }

                    if(flagControllo) {
                        recensione.setNota(nota);
                        recensione.setStelle(stelle);



                        if (!RecensioneDAO.doSave(recensione)) {
                            message = "Inserimento: L'inserimento non ha avuto successo, nessuna riga inserita.";
                        } else {
                            message = "Inserimento: Inserimento effettuato con successo.";

                        }
                        request.setAttribute("esito", message);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
                        dispatcher.forward(request, response);
                    }else{
                        request.setAttribute("esito", "Errore, nei dati inseriti ("+message+"). ");
                        request.getRequestDispatcher("creazione-inserimento-recensione-servlet").forward(request, response);
                    }
                }else{
                    message += "Medico non trovato. ";
                    //flagControllo = false;
                    request.setAttribute("esito", "Riprovare, l'inserimento della recensione non ha avuto successo ("+message+").");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni-servlet");
                    dispatcher.forward(request, response);
                }
            }


        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

}

