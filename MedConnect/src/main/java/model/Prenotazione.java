package model;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;

public class Prenotazione {
    private int id_prenotazione;
    private String stato;
    private String nota;
    private int id_paziente;
    private int id_disponibilita;

    public Prenotazione() {
    }

    public Prenotazione(int id_prenotazione, String stato, String nota, int id_paziente, int id_disponibilita) {
        this.id_prenotazione = id_prenotazione;
        this.stato = stato;
        this.nota = nota;
        this.id_paziente = id_paziente;
        this.id_disponibilita = id_disponibilita;
    }

    public int getId_prenotazione() {
        return id_prenotazione;
    }

    public void setId_prenotazione(int id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getId_paziente() {
        return id_paziente;
    }

    public void setId_paziente(int id_paziente) {
        this.id_paziente = id_paziente;
    }

    public int getId_disponibilita() {
        return id_disponibilita;
    }

    public void setId_disponibilita(int id_disponibilita) {
        this.id_disponibilita = id_disponibilita;
    }
}
