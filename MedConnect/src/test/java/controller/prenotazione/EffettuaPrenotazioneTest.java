package controller.prenotazione;

import static org.mockito.Mockito.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import model.prenotazione.Prenotazione;
import model.prenotazione.PrenotazioneDAO;
import model.utente.Utente;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EffettuaPrenotazioneTest {

    @Test
    void testDoPost_Success() throws Exception {
        // Crea i mock necessari
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class); // Mock della sessione

        // Mock per simulare la sessione
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1); // Imposta un id utente fittizio
        when(session.getAttribute("utente")).thenReturn(utente); // Mock per restituire l'utente

        // Mock per PrenotazioneDAO
        try (MockedStatic<PrenotazioneDAO> mockedDAO = mockStatic(PrenotazioneDAO.class)) {
            mockedDAO.when(() -> PrenotazioneDAO.doSave(any(Prenotazione.class))).thenReturn(1);

            EffettuaPrenotazione servlet = new EffettuaPrenotazione();

            // Mock per i parametri della richiesta
            when(request.getParameter("nota")).thenReturn("Nota di test");
            when(request.getParameter("idPaziente")).thenReturn("123");
            when(request.getParameter("idDisponibilita")).thenReturn("456");

            // Mock per il dispatcher
            when(request.getRequestDispatcher("/WEB-INF/results/success.jsp")).thenReturn(dispatcher);

            // Esegui la servlet
            servlet.doPost(request, response);

            // Verifica che il messaggio di successo sia stato impostato correttamente
            verify(request).setAttribute("message", "Prenotazione salvata con successo.");

            // Verifica che il forward al success.jsp sia stato eseguito
            verify(dispatcher).forward(request, response);
        }
    }


    @Test
    void testDoPost_FailureCreazionePrenotazione() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class); // Mock della sessione

        // Mock per simulare la sessione
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1); // Imposta un id utente fittizio
        when(session.getAttribute("utente")).thenReturn(utente); // Mock per restituire l'utente


        try (MockedStatic<PrenotazioneDAO> mockedDAO = mockStatic(PrenotazioneDAO.class)) {
            mockedDAO.when(() -> PrenotazioneDAO.doSave(any(Prenotazione.class))).thenReturn(-1);

            EffettuaPrenotazione servlet = new EffettuaPrenotazione();

            when(request.getParameter("nota")).thenReturn("Nota di test");
            when(request.getParameter("idPaziente")).thenReturn("123");
            when(request.getParameter("idDisponibilita")).thenReturn("456");

            when(request.getRequestDispatcher("/WEB-INF/results/error.jsp")).thenReturn(dispatcher);

            servlet.doPost(request, response);

            verify(request).setAttribute("message", "Errore durante la creazione della prenotazione.");

            verify(dispatcher).forward(request, response);
        }
    }


    @Test
    void testDoPost_FailureParametroNotaPrenotazione() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class); // Mock della sessione

        // Mock per simulare la sessione
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1); // Imposta un id utente fittizio
        when(session.getAttribute("utente")).thenReturn(utente); // Mock per restituire l'utente



        try (MockedStatic<PrenotazioneDAO> mockedDAO = mockStatic(PrenotazioneDAO.class)) {
            mockedDAO.when(() -> PrenotazioneDAO.doSave(any(Prenotazione.class))).thenReturn(-1);

            EffettuaPrenotazione servlet = new EffettuaPrenotazione();

            when(request.getParameter("nota")).thenReturn(null);
            when(request.getParameter("idPaziente")).thenReturn("123");
            when(request.getParameter("idDisponibilita")).thenReturn("456");

            when(request.getRequestDispatcher("/WEB-INF/results/error.jsp")).thenReturn(dispatcher);

            servlet.doPost(request, response);

            verify(request).setAttribute("message", "Errore nei parametri.");

            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    void testDoPost_FailureParametroIdPazientePrenotazione() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class); // Mock della sessione

        // Mock per simulare la sessione
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1); // Imposta un id utente fittizio
        when(session.getAttribute("utente")).thenReturn(utente); // Mock per restituire l'utente



        try (MockedStatic<PrenotazioneDAO> mockedDAO = mockStatic(PrenotazioneDAO.class)) {
            mockedDAO.when(() -> PrenotazioneDAO.doSave(any(Prenotazione.class))).thenReturn(-1);

            EffettuaPrenotazione servlet = new EffettuaPrenotazione();

            when(request.getParameter("nota")).thenReturn("Nota di test");
            when(request.getParameter("idPaziente")).thenReturn("-1");
            when(request.getParameter("idDisponibilita")).thenReturn("456");

            when(request.getRequestDispatcher("/WEB-INF/results/error.jsp")).thenReturn(dispatcher);

            servlet.doPost(request, response);

            verify(request).setAttribute("message", "Errore nei parametri.");

            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    void testDoPost_FailureParametroIdDisponibilitaPrenotazione() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class); // Mock della sessione

        // Mock per simulare la sessione
        when(request.getSession(false)).thenReturn(session);
        Utente utente = new Utente();
        utente.setId(1); // Imposta un id utente fittizio
        when(session.getAttribute("utente")).thenReturn(utente); // Mock per restituire l'utente



        try (MockedStatic<PrenotazioneDAO> mockedDAO = mockStatic(PrenotazioneDAO.class)) {
            mockedDAO.when(() -> PrenotazioneDAO.doSave(any(Prenotazione.class))).thenReturn(-1);

            EffettuaPrenotazione servlet = new EffettuaPrenotazione();

            when(request.getParameter("nota")).thenReturn(null);
            when(request.getParameter("idPaziente")).thenReturn("123");
            when(request.getParameter("idDisponibilita")).thenReturn("-1");

            when(request.getRequestDispatcher("/WEB-INF/results/error.jsp")).thenReturn(dispatcher);

            servlet.doPost(request, response);

            verify(request).setAttribute("message", "Errore nei parametri.");

            verify(dispatcher).forward(request, response);
        }
    }
}
