package controller.medico;

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

import java.io.IOException;
import java.util.List;

/**
 * Servlet per gestire la ricerca dei medici.
 * Riceve i parametri di ricerca per ruolo/nome e città e restituisce la lista dei medici che corrispondono ai criteri.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet("/search-medico")
public class SearchMedico extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP GET per la ricerca di medici.
     * Recupera i parametri di ricerca dalla richiesta e utilizza il DAO per ottenere i medici che soddisfano i criteri.
     * Imposta la lista dei medici come attributo della richiesta e inoltra la risposta alla vista di ricerca.
     *
     * @param request La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException Se si verifica un errore nella gestione della risposta.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Medico> medicoList = new MedicoDAO().doRetrieve(request.getParameter("ruoloNome"), request.getParameter("citta"));

        request.setAttribute("medicoList", medicoList);
        String address = "/WEB-INF/results/medico/search-medico.jsp";

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
