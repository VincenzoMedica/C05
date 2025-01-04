package controller.recensione;

import controller.prenotazione.EffettuaPrenotazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.prenotazione.Prenotazione;
import model.prenotazione.PrenotazioneDAO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class InserimentoRecensioneTest {
/*
    @Test
    void testDoPost_Success() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        try (MockedStatic<PrenotazioneDAO> mockedDAO = mockStatic(PrenotazioneDAO.class)) {
            mockedDAO.when(() -> PrenotazioneDAO.doSave(any(Prenotazione.class))).thenReturn(1);

            EffettuaPrenotazione servlet = new EffettuaPrenotazione();

            when(request.getParameter("nota")).thenReturn("Nota di test");
            when(request.getParameter("idPaziente")).thenReturn("123");
            when(request.getParameter("idDisponibilita")).thenReturn("456");

            when(request.getRequestDispatcher("/WEB-INF/results/success.jsp")).thenReturn(dispatcher);

            servlet.doPost(request, response);

            verify(request).setAttribute("message", "Prenotazione salvata con successo.");

            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    void testDoPost_Failure() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

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
*/
}
