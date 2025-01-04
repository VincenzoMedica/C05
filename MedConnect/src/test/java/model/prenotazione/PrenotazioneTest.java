package model.prenotazione;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrenotazioneTest {

    @Test
    public void testGetterSetterPrenotazione() {
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setId(1);
        prenotazione.setNota("Testing Nota");
        prenotazione.setStato("Da Completare");
        prenotazione.setIdPaziente(1);
        prenotazione.setIdDisponibilita(1);

        Assertions.assertEquals(prenotazione.getId(), 1);
        Assertions.assertEquals(prenotazione.getNota(), "Testing Nota");
        Assertions.assertEquals(prenotazione.getStato(), "Da Completare");
        Assertions.assertEquals(prenotazione.getIdPaziente(), 1);
        Assertions.assertEquals(prenotazione.getIdDisponibilita(), 1);
    }

    @Test
    public void testPrenotazione1() {
        Prenotazione prenotazione = new Prenotazione("Testing Nota 2", 2, 2);

        Assertions.assertEquals(prenotazione.getNota(), "Testing Nota 2");
        Assertions.assertEquals(prenotazione.getIdPaziente(), 2);
        Assertions.assertEquals(prenotazione.getIdDisponibilita(), 2);
    }

    @Test
    public void testPrenotazione2() {
        Prenotazione prenotazione = new Prenotazione(3, "Completato", "Testing Nota 3", 3, 3);

        Assertions.assertEquals(prenotazione.getId(), 3);
        Assertions.assertEquals(prenotazione.getNota(), "Testing Nota 3");
        Assertions.assertEquals(prenotazione.getStato(), "Completato");
        Assertions.assertEquals(prenotazione.getIdPaziente(), 3);
        Assertions.assertEquals(prenotazione.getIdDisponibilita(), 3);
    }

    @Test
    public void testToStringPrenotazione() {
        Prenotazione prenotazione = new Prenotazione(4, "Da Completare", "Testing Nota 4", 4, 4);

        Assertions.assertEquals(prenotazione.toString(), "Prenotazione{id=4, stato='Da Completare', nota='Testing Nota 4', idPaziente=4, idDisponibilita=4}");
    }

}
