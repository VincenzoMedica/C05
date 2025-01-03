package controller.prenotazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "prenotazioni-servlet", value="/prenotazioni-servlet")
public class Prenotazioniservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indirizzo;
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");


        if(utente != null) {
            ArrayList<Prenotazione> prenotaziones = PrenotazioneDAO.doRetrieveById_utente(utente.getId());
        /*    ArrayList<ArrayList<RiferimentoArticolo>> riferimentoArticolos = new ArrayList<>();
            for(Ordine ordine : ordines) {
                riferimentoArticolos.add(RiferimentoArticoloDAO.doRetrieveById_ordine(ordine.getId_ordine()));
            }*/

            request.setAttribute("prenotaziones", prenotaziones);
            /*request.setAttribute("riferimentoArticolos", riferimentoArticolos);
*/
            indirizzo = "/WEB-INF/common/prenotazioni.jsp";


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
