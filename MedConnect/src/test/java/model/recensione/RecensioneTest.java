package model.recensione;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecensioneTest {

    @Test
    public void testSetIdRecensione_ValidId() {
        Recensione recensione = new Recensione();
        assertTrue(recensione.setId_recensione(1));
        assertEquals(1, recensione.getId_recensione());
    }

    @Test
    public void testSetIdRecensione_InvalidId() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setId_recensione(-1));
        assertEquals(0, recensione.getId_recensione()); // Valore predefinito
    }

    @Test
    public void testSetIdPrenotazione_ValidId() {
        Recensione recensione = new Recensione();
        assertTrue(recensione.setId_prenotazione(10));
        assertEquals(10, recensione.getId_prenotazione());
    }

    @Test
    public void testSetIdPrenotazione_InvalidId() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setId_prenotazione(-1));
        assertEquals(0, recensione.getId_prenotazione()); // Valore predefinito
    }

    @Test
    public void testSetIdMedico_ValidId() {
        Recensione recensione = new Recensione();
        assertTrue(recensione.setId_medico(123));
        assertEquals(123, recensione.getId_medico());
    }

    @Test
    public void testSetIdMedico_InvalidId() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setId_medico(-5));
        assertEquals(0, recensione.getId_medico()); // Valore predefinito
    }

    @Test
    public void testSetIdPaziente_ValidId() {
        Recensione recensione = new Recensione();
        assertTrue(recensione.setId_paziente(999));
        assertEquals(999, recensione.getId_paziente());
    }

    @Test
    public void testSetIdPaziente_InvalidId() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setId_paziente(-20));
        assertEquals(0, recensione.getId_paziente()); // Valore predefinito
    }

    @Test
    public void testSetNota_ValidNota() {
        Recensione recensione = new Recensione();
        String nota = "Ottimo servizio!";
        assertTrue(recensione.setNota(nota));
        assertEquals(nota, recensione.getNota());
    }

    @Test
    public void testSetNota_InvalidNotaTooLong() {
        Recensione recensione = new Recensione();
        String notaLunga = "a".repeat(256); // Stringa lunga 256 caratteri
        assertFalse(recensione.setNota(notaLunga));
        assertNull(recensione.getNota()); // Valore predefinito
    }

    @Test
    public void testSetNota_InvalidNotaNull() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setNota(null));
        assertNull(recensione.getNota()); // Valore predefinito
    }

    @Test
    public void testSetStelle_ValidValue() {
        Recensione recensione = new Recensione();
        assertTrue(recensione.setStelle(3));
        assertEquals(3, recensione.getStelle());
    }

    @Test
    public void testSetStelle_InvalidValueTooLow() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setStelle(0));
        assertEquals(0, recensione.getStelle()); // Valore predefinito
    }

    @Test
    public void testSetStelle_InvalidValueTooHigh() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setStelle(6));
        assertEquals(0, recensione.getStelle()); // Valore predefinito
    }

    @Test
    public void testSetStelle_InvalidNegativeValue() {
        Recensione recensione = new Recensione();
        assertFalse(recensione.setStelle(-1));
        assertEquals(0, recensione.getStelle()); // Valore predefinito
    }
}
