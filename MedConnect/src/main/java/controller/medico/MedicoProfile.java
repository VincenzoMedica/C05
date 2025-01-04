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

@WebServlet("/medico-profile")
public class MedicoProfile extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Medico medico = new MedicoDAO().doRetrieveById(request.getParameter("id"));
        List<Disponibilita> disponibilitaList = new DisponibilitaDAO().doRetrieveByIdMedico(medico.getId());

        request.setAttribute("medico", medico);
        request.setAttribute("disponibilitaList", disponibilitaList);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String address = "/WEB-INF/results/medico/medico-profile.jsp";

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
