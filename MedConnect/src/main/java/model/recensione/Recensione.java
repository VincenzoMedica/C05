package model.recensione;

import java.util.regex.Pattern;

/**
 * La classe {@code Recensione} rappresenta una recensione relativa a una prenotazione medica.
 * Contiene informazioni sull'identificativo della recensione, della prenotazione, del medico, del paziente,
 * una nota descrittiva e un numero di stelle assegnato.
 *
 * @author [C05]
 * @version 1.0
 */
public class Recensione {
    private int id_recensione;
    private int id_prenotazione;
    private int id_medico;
    private int id_paziente;
    private String nota;
    private int stelle; // Numero di stelle assegnate nella recensione (da 1 a 5)

    /**
     * Costruttore di default.
     */
    public Recensione() {
    }

    /**
     * Restituisce l'identificativo della recensione.
     *
     * @return l'ID della recensione.
     */
    public int getId_recensione() {
        return id_recensione;
    }

    /**
     * Imposta l'identificativo della recensione.
     *
     * @param id_recensione l'ID della recensione.
     * @return {@code true} se l'ID è valido (valore numerico non negativo), {@code false} altrimenti.
     */
    public boolean setId_recensione(int id_recensione) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(id_recensione)).matches()) {
            this.id_recensione = id_recensione;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restituisce l'identificativo della prenotazione associata.
     *
     * @return l'ID della prenotazione.
     */
    public int getId_prenotazione() {
        return id_prenotazione;
    }

    /**
     * Imposta l'identificativo della prenotazione associata.
     *
     * @param id_prenotazione l'ID della prenotazione.
     * @return {@code true} se l'ID è valido (valore numerico non negativo), {@code false} altrimenti.
     */
    public boolean setId_prenotazione(int id_prenotazione) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(id_prenotazione)).matches()) {
            this.id_prenotazione = id_prenotazione;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restituisce l'identificativo del medico associato.
     *
     * @return l'ID del medico.
     */
    public int getId_medico() {
        return id_medico;
    }

    /**
     * Imposta l'identificativo del medico associato.
     *
     * @param id_medico l'ID del medico.
     * @return {@code true} se l'ID è valido (valore numerico non negativo), {@code false} altrimenti.
     */
    public boolean setId_medico(int id_medico) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(id_medico)).matches()) {
            this.id_medico = id_medico;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restituisce l'identificativo del paziente autore della recensione.
     *
     * @return l'ID del paziente.
     */
    public int getId_paziente() {
        return id_paziente;
    }

    /**
     * Imposta l'identificativo del paziente autore della recensione.
     *
     * @param id_paziente l'ID del paziente.
     * @return {@code true} se l'ID è valido (valore numerico non negativo), {@code false} altrimenti.
     */
    public boolean setId_paziente(int id_paziente) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(id_paziente)).matches()) {
            this.id_paziente = id_paziente;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restituisce la nota descrittiva della recensione.
     *
     * @return la nota della recensione.
     */
    public String getNota() {
        return nota;
    }

    /**
     * Imposta la nota descrittiva della recensione.
     *
     * @param nota la nota della recensione (massimo 255 caratteri).
     * @return {@code true} se la nota è valida (non nulla e di lunghezza massima 255 caratteri), {@code false} altrimenti.
     */
    public boolean setNota(String nota) {
        if (nota != null && Pattern.compile("^.{0,255}$").matcher(nota).matches()) {
            this.nota = nota;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restituisce il numero di stelle assegnate nella recensione.
     *
     * @return il numero di stelle.
     */
    public int getStelle() {
        return stelle;
    }

    /**
     * Imposta il numero di stelle assegnate nella recensione.
     *
     * @param stelle il numero di stelle (da 1 a 5).
     * @return {@code true} se il valore è valido (compreso tra 1 e 5), {@code false} altrimenti.
     */
    public boolean setStelle(int stelle) {
        if (Pattern.compile("^[1-5]$").matcher(String.valueOf(stelle)).matches()) {
            this.stelle = stelle;
            return true;
        } else {
            return false;
        }
    }
}
