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

@WebServlet(name = "prenotazioni-servlet", value="/prenotazioni-servlet")
public class PrenotazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo;
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");


        if(utente != null) {
            ArrayList<Prenotazione> prenotaziones = new ArrayList<>();
            ArrayList<Disponibilita> disponibilitas = new ArrayList<>();
            ArrayList<Medico> medicos = new ArrayList<>();
            ArrayList<Boolean> esistenaRecensionePerPrenotazione = new ArrayList<>();

            if(PrenotazioneDAO.doRetrieveByIdUtente(utente.getId(), prenotaziones, disponibilitas, medicos)) {
                for (Prenotazione prenotazione : prenotaziones) {
                    disponibilitas.add(DisponibilitaDAO.doRetrieveById_disponibilita(prenotazione.getIdDisponibilita()));
                    esistenaRecensionePerPrenotazione.add(RecensioneDAO.existsRecensioneForPrenotazione(prenotazione.getId()));
                }

                request.setAttribute("prenotaziones", prenotaziones);
                request.setAttribute("disponibilitas", disponibilitas);
                request.setAttribute("medicos", medicos);
                request.setAttribute("esistenaRecensionePerPrenotazione", esistenaRecensionePerPrenotazione);

                indirizzo = "/WEB-INF/common/prenotazioni.jsp";
            }
            else{
                indirizzo = "/WEB-INF/results/error.jsp";
            }

        }else{
            indirizzo = "/login.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(indirizzo);
        requestDispatcher.forward(request, response);

    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
