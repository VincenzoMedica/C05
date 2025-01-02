package controller.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "login-servlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String message = "";
        boolean flagControlli = true;

        // Controllo se i campi richiesti non sono nulli o vuoti
        if (username == null || username.trim().isEmpty()) {
            message += "L'username non può essere vuoto. ";
            flagControlli = false;
        }
        if (password == null || password.trim().isEmpty()) {
            message += "La password non può essere vuota. ";
            flagControlli = false;
        }

        String address = "";
        if(flagControlli){
            UtenteDAO utenteDAO = new UtenteDAO();
            Utente utente = utenteDAO.doRetrieveByUsernamePassword(username, password);
            if (utente!=null){
                // Salva l'utente nella sessione
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);


            } else {
                // Redirect alla pagina con un messaggio di errore
                request.setAttribute("esito", "Riprovare, il login non ha avuto successo.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            request.setAttribute("esito", "Riprovare, il login non ha avuto successo ("+message+").");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
