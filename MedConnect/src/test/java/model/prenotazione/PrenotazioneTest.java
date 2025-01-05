package model.prenotazione;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrenotazioneTest {

    @Test
    public void setNotaTestInvalid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertFalse(prenotazione.setNota(null));
        Assertions.assertNull(prenotazione.getNota());
    }

    @Test
    public void setNotaTestValid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertTrue(prenotazione.setNota("Nota valida"));
        Assertions.assertEquals("Nota valida", prenotazione.getNota());
    }

    @Test
    public void setNotaTestTooLong() {
        Prenotazione prenotazione = new Prenotazione();
        String notaTroppoLunga = "a".repeat(256);

        Assertions.assertFalse(prenotazione.setNota(notaTroppoLunga));
        Assertions.assertNull(prenotazione.getNota());
    }

    @Test
    public void setIdTestValid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertTrue(prenotazione.setId(123));
        Assertions.assertEquals(123, prenotazione.getId());
    }

    @Test
    public void setIdTestInvalid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertFalse(prenotazione.setId(-1));
        Assertions.assertEquals(0, prenotazione.getId());
    }

    @Test
    public void setStatoTestValid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertTrue(prenotazione.setStato("Completata"));
        Assertions.assertEquals("Completata", prenotazione.getStato());
    }

    @Test
    public void setStatoTestInvalid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertFalse(prenotazione.setStato("Invalid"));
        Assertions.assertNull(prenotazione.getStato());
    }

    @Test
    public void setIdPazienteTestValid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertTrue(prenotazione.setIdPaziente(456));
        Assertions.assertEquals(456, prenotazione.getIdPaziente());
    }

    @Test
    public void setIdPazienteTestInvalid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertFalse(prenotazione.setIdPaziente(-456));
        Assertions.assertEquals(0, prenotazione.getIdPaziente());
    }

    @Test
    public void setIdDisponibilitaTestValid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertTrue(prenotazione.setIdDisponibilita(789));
        Assertions.assertEquals(789, prenotazione.getIdDisponibilita());
    }

    @Test
    public void setIdDisponibilitaTestInvalid() {
        Prenotazione prenotazione = new Prenotazione();

        Assertions.assertFalse(prenotazione.setIdDisponibilita(-789));
        Assertions.assertEquals(0, prenotazione.getIdDisponibilita());
    }

    @Test
    public void toStringTest() {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setId(4);
        prenotazione.setNota("Testing Nota 4");
        prenotazione.setStato("Da Completare");
        prenotazione.setIdPaziente(4);
        prenotazione.setIdDisponibilita(4);

        Assertions.assertEquals(prenotazione.toString(), "Prenotazione{id=4, stato='Da Completare', nota='Testing Nota 4', idPaziente=4, idDisponibilita=4}");
    }

}
