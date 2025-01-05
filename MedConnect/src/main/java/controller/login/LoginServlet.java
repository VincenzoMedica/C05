package controller.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utente.Utente;
import model.utente.UtenteDAO;

import java.io.IOException;

/**
 * Servlet per la gestione del login dell'utente.
 * Verifica le credenziali dell'utente e gestisce la sessione di login.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet(name = "login-servlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP GET per il login dell'utente.
     * Esegue il controllo dei parametri (username e password) e, se validi,
     * autentica l'utente e crea una sessione.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
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
        if (flagControlli) {
            UtenteDAO utenteDAO = new UtenteDAO();
            Utente utente = utenteDAO.doRetrieveByUsernamePassword(username, password);
            if (utente != null) {
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
        } else {
            request.setAttribute("esito", "Riprovare, il login non ha avuto successo (" + message + ").");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }


    }

    /**
     * Gestisce la richiesta HTTP POST per il login dell'utente.
     * In realtà, invoca il metodo doGet per la gestione delle richieste POST.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
