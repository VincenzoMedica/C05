package controller.recensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.recensione.Recensione;
import model.recensione.RecensioneDAO;
import model.utente.Utente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Classe di test per la servlet {@link InserimentoRecensioneServlet}.
 * Verifica il comportamento della servlet in diversi scenari, come validazione dei parametri,
 * gestione delle sessioni e interazione con il DAO.
 */
public class InserimentoRecensioneTest {

    private InserimentoRecensioneServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private MockedStatic<RecensioneDAO> mockedRecensioneDAO;
    /**
     * Configura il contesto di test prima di ogni metodo.
     * Inizializza la servlet e crea oggetti mock per request, response, session e dispatcher.
     */
    @BeforeEach
    void setUp() {
        servlet = new InserimentoRecensioneServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        mockedRecensioneDAO = mockStatic(RecensioneDAO.class);

    }

    @AfterEach
    void tearDown() {
        mockedRecensioneDAO.close(); // Chiudi il mock statico
    }

    /**
     * Test per il caso in cui il parametro "idPrenotazione" è mancante.
     * Verifica che venga restituito un errore 400.
     */
    @Test
    void testDoPost_IdPrenotazioneMancante() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn(null);
        servlet.doPost(request, response);
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Id della prenotazione mancante o non valido.");
    }

    /**
     * Test per il caso in cui il parametro "idPrenotazione" non è numerico.
     * Verifica che venga restituito un errore 400.
     */
    @Test
    void testDoPost_IdPrenotazioneNonValido() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("abc");
        servlet.doPost(request, response);
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato ID della prenotazione non valido.");
    }
    /**
     * Test per il caso in cui la sessione utente è assente.
     * Verifica che venga restituito un errore 401.
     */
    @Test
    void testDoPost_SessioneNonValida() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getSession(false)).thenReturn(null);

        servlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sessione non valida.");
    }

    /**
     * Test per il caso in cui l'utente non è autenticato.
     * Verifica che la richiesta venga reindirizzata alla pagina di login.
     */
    @Test
    void testDoPost_UtenteNonAutenticato() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(dispatcher).forward(request, response);
    }

    /**
     * Test per il caso in cui esiste già una recensione per l'ID prenotazione.
     * Verifica che venga mostrato un messaggio di errore e che la richiesta venga inoltrata.
     */
    @Test
    void testDoPost_RecensioneEsistente() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(true);
        when(request.getRequestDispatcher("prenotazioni-servlet")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("esito"), contains("Esiste già una recensione"));
        verify(dispatcher).forward(request, response);
    }

    /**
     * Test per il caso in cui il salvataggio della recensione fallisce.
     * Verifica che venga mostrato un messaggio di errore e che la richiesta venga inoltrata.
     */
    @Test
    void testDoPost_CreazioneRecensioneFallita() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn("Ottimo medico");
        when(request.getParameter("stelle")).thenReturn("5");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        /*
        * when() è un metodo di Mockito utilizzato per definire il comportamento di un mock.
        * Quando si chiama un metodo su un oggetto mockato, si può specificare cosa deve restituire
        * quel metodo per una determinata chiamata.
        * In questo caso, si sta dicendo: "Quando il metodo existsRecensioneForPrenotazione viene
        * chiamato con il parametro 1".
        *
        * Si sta simulando la chiamata al metodo existsRecensioneForPrenotazione(int idPrenotazione)
        * della classe RecensioneDAO.
        *
        *thenReturn() è un metodo che specifica quale valore il mock deve restituire quando il metodo
        * existsRecensioneForPrenotazione(1) viene chiamato con il parametro 1.
        *
         */
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);
        when(RecensioneDAO.getIdMedicoByPrenotazione(1)).thenReturn(2);
        when(RecensioneDAO.doSave(any(Recensione.class))).thenReturn(false);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("esito"), contains("L'inserimento non ha avuto successo"));
        verify(dispatcher).forward(request, response);
    }

    /**
     * Test per il caso in cui l'inserimento della recensione ha successo.
     * Verifica che venga mostrato un messaggio di successo e che la richiesta venga inoltrata.
     */
    @Test
    void testDoPost_InserimentoRiuscito() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn("Ottimo medico");
        when(request.getParameter("stelle")).thenReturn("5");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);
        when(RecensioneDAO.getIdMedicoByPrenotazione(1)).thenReturn(2);
        when(RecensioneDAO.doSave(any(Recensione.class))).thenReturn(true);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class); // Usa il mock
        when(request.getRequestDispatcher("/WEB-INF/results/success.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("message"), contains("Inserimento: Inserimento effettuato con successo"));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_IdMedicoNonValido() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn("Ottimo medico");
        when(request.getParameter("stelle")).thenReturn("5");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);
        when(RecensioneDAO.getIdMedicoByPrenotazione(1)).thenReturn(0);
        when(request.getRequestDispatcher("prenotazioni-servlet")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("esito"), contains("Riprovare, l'inserimento della recensione non ha avuto successo "));
    }

    @Test
    void testDoPost_isValidTrue() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn(null);
        when(request.getParameter("stelle")).thenReturn("5");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);
        when(RecensioneDAO.getIdMedicoByPrenotazione(1)).thenReturn(2);

        when(request.getRequestDispatcher("creazione-inserimento-recensione-servlet")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("esito"), contains("Errore nei dati inseriti"));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_IdPrenotazioneRecensioneNonValido() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("-1");
        when(request.getParameter("nota")).thenReturn("Ottimo medico");
        when(request.getParameter("stelle")).thenReturn("5");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);

        servlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: id_prenotazione non valido.");
    }

    @Test
    void testDoPost_IdPazienteRecensioneNonValido() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn("Ottimo medico");
        when(request.getParameter("stelle")).thenReturn("5");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(-1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);

        servlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: id_paziente non valido.");
    }

    @Test
    void testDoPost_StelleMinore1() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn(null);
        when(request.getParameter("stelle")).thenReturn("-1");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);
        when(RecensioneDAO.getIdMedicoByPrenotazione(1)).thenReturn(2);

        when(request.getRequestDispatcher("creazione-inserimento-recensione-servlet")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("esito"), contains("Il numero di stelle deve essere compreso tra 1 e 5."));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_StelleNonValida() throws ServletException, IOException {
        when(request.getParameter("idPrenotazione")).thenReturn("1");
        when(request.getParameter("nota")).thenReturn(null);
        when(request.getParameter("stelle")).thenReturn("abc");
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1);
        when(session.getAttribute("utente")).thenReturn(utente);
        when(RecensioneDAO.existsRecensioneForPrenotazione(1)).thenReturn(false);
        when(RecensioneDAO.getIdMedicoByPrenotazione(1)).thenReturn(2);

        when(request.getRequestDispatcher("creazione-inserimento-recensione-servlet")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("esito"), contains("Il numero di stelle deve essere un numero valido. "));
        verify(dispatcher).forward(request, response);
    }
}