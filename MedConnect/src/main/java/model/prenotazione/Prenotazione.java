package model.prenotazione;

import java.util.regex.Pattern;

/**
 * La classe {@code Prenotazione} rappresenta una prenotazione effettuata da un paziente per una determinata disponibilità.
 * Include informazioni come lo stato, eventuale nota e i riferimenti agli identificativi del paziente e della disponibilità.
 * Contiene metodi per gestire e validare i dati associati.
 *
 * @author [C05]
 * @version 1.0
 */
public class Prenotazione {
    private int id;
    private String stato;
    private String nota;
    private int idPaziente;
    private int idDisponibilita;

    /**
     * Restituisce l'ID della prenotazione.
     *
     * @return l'ID della prenotazione.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'ID della prenotazione.
     *
     * @param id l'ID della prenotazione.
     * @return {@code true} se l'ID è valido, {@code false} altrimenti.
     */
    public boolean setId(int id) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(id)).matches()) {
            this.id = id;
            return true;
        } else return false;
    }

    /**
     * Restituisce lo stato della prenotazione.
     *
     * @return lo stato della prenotazione.
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta lo stato della prenotazione.
     * Gli stati validi sono "Da Completare" e "Completata".
     *
     * @param stato lo stato della prenotazione.
     * @return {@code true} se lo stato è valido, {@code false} altrimenti.
     */
    public boolean setStato(String stato) {
        if (stato != null && Pattern.compile("^(Da Completare|Completata)$").matcher(stato).matches()) {
            this.stato = stato;
            return true;
        } else return false;
    }

    /**
     * Restituisce la nota associata alla prenotazione.
     *
     * @return la nota associata alla prenotazione.
     */
    public String getNota() {
        return nota;
    }

    /**
     * Imposta una nota per la prenotazione.
     * La nota può essere lunga al massimo 255 caratteri.
     *
     * @param nota la nota della prenotazione.
     * @return {@code true} se la nota è valida, {@code false} altrimenti.
     */
    public boolean setNota(String nota) {
        if (nota != null && Pattern.compile("^.{0,255}$").matcher(nota).matches()) {
            this.nota = nota;
            return true;
        }
        return false;
    }

    /**
     * Restituisce l'ID del paziente associato alla prenotazione.
     *
     * @return l'ID del paziente.
     */
    public int getIdPaziente() {
        return idPaziente;
    }

    /**
     * Imposta l'ID del paziente associato alla prenotazione.
     *
     * @param idPaziente l'ID del paziente.
     * @return {@code true} se l'ID del paziente è valido, {@code false} altrimenti.
     */
    public boolean setIdPaziente(int idPaziente) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(idPaziente)).matches()) {
            this.idPaziente = idPaziente;
            return true;
        } else return false;
    }

    /**
     * Restituisce l'ID della disponibilità associata alla prenotazione.
     *
     * @return l'ID della disponibilità.
     */
    public int getIdDisponibilita() {
        return idDisponibilita;
    }

    /**
     * Imposta l'ID della disponibilità associata alla prenotazione.
     *
     * @param idDisponibilita l'ID della disponibilità.
     * @return {@code true} se l'ID della disponibilità è valido, {@code false} altrimenti.
     */
    public boolean setIdDisponibilita(int idDisponibilita) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(idDisponibilita)).matches()) {
            this.idDisponibilita = idDisponibilita;
            return true;
        } else return false;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto {@code Prenotazione}.
     *
     * @return una stringa contenente i dettagli della prenotazione.
     */
    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", stato='" + stato + '\'' +
                ", nota='" + nota + '\'' +
                ", idPaziente=" + idPaziente +
                ", idDisponibilita=" + idDisponibilita +
                '}';
    }
}
