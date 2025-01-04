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

@WebServlet("/effettua-prenotazione")
public class EffettuaPrenotazione extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nota = request.getParameter("nota");
        int idPaziente = Integer.parseInt(request.getParameter("idPaziente"));
        int idDisponibilita = Integer.parseInt(request.getParameter("idDisponibilita"));

        Prenotazione prenotazione = new Prenotazione();

        if (prenotazione.setNota(nota) && prenotazione.setIdPaziente(idPaziente) && prenotazione.setIdDisponibilita(idDisponibilita)) {
            if (PrenotazioneDAO.doSave(prenotazione) != -1) {
                request.setAttribute("message", "Prenotazione salvata con successo.");
                String address = "/WEB-INF/results/success.jsp";

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Errore durante la creazione della prenotazione.");
                String address = "/WEB-INF/results/error.jsp";

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        }
        else {
            request.setAttribute("message", "Errore nei parametri.");
            String address = "/WEB-INF/results/error.jsp";

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }


    }
}
