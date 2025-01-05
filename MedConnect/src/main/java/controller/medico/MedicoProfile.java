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
 * Servlet per gestire la visualizzazione del profilo di un medico.
 * Recupera le informazioni del medico e le sue disponibilità, quindi le passa alla vista.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet("/medico-profile")
public class MedicoProfile extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP GET per visualizzare il profilo di un medico.
     * Recupera le informazioni del medico e la lista delle sue disponibilità.
     * Imposta gli attributi necessari nella richiesta e reindirizza alla pagina del profilo medico.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera l'ID del medico dalla richiesta
        Medico medico = new MedicoDAO().doRetrieveById(request.getParameter("id"));

        // Recupera le disponibilità del medico tramite il suo ID
        List<Disponibilita> disponibilitaList = new DisponibilitaDAO().doRetrieveByIdMedico(medico.getId());

        // Imposta gli attributi della richiesta per il medico e le sue disponibilità
        request.setAttribute("medico", medico);
        request.setAttribute("disponibilitaList", disponibilitaList);

        // Impedisce la memorizzazione della pagina nella cache
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Invia la richiesta al dispatcher per inoltrarla alla vista del profilo medico
        String address = "/WEB-INF/results/medico/medico-profile.jsp";

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
