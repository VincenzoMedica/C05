package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Prenotazione;
import model.PrenotazioneDAO;
import model.Utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "areaUtenteServlet", value = "/area-utente-servlet")
public class AreaUtenteServlet extends HttpServlet {




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String indirizzo;
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");


        if(utente != null) {
            ArrayList<Prenotazione> prenotaziones = PrenotazioneDAO.doRetrieveById_utente(utente.getId());
            indirizzo = "/WEB-INF/common/areaUtente.jsp";
        }else{
            indirizzo = "/login.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(indirizzo);
        requestDispatcher.forward(request, response);

    }

    public void destroy() {
    }
}