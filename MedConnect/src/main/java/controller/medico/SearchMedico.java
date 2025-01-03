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

@WebServlet("/search-medico")
public class SearchMedico extends HttpServlet {
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
