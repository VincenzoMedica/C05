package controller.logout;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Servlet per gestire il logout dell'utente.
 * Rimuove l'attributo utente dalla sessione e invalida la sessione stessa.
 *
 * @author [C05]
 * @version 1.0
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP GET per il logout dell'utente.
     * Invalida la sessione corrente rimuovendo l'attributo "utente" e poi la invalida.
     * Dopo il logout, l'utente viene reindirizzato alla pagina principale.
     *
     * @param request  La richiesta HTTP ricevuta.
     * @param response La risposta HTTP da inviare.
     * @throws ServletException Se si verifica un errore nel processo di servlet.
     * @throws IOException      Se si verifica un errore nella gestione della risposta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        session.removeAttribute("utente");
        session.invalidate();

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Gestisce la richiesta HTTP POST per il logout dell'utente.
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
