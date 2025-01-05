package model.medico;

import model.utente.Utente;

/**
 * La classe {@code Medico} rappresenta un medico nel sistema, estendendo le caratteristiche
 * di un utente registrato generico ({@link Utente}). Fornisce informazioni aggiuntive come il ruolo,
 * la specializzazione e l'indirizzo del medico.
 *
 * @author [C05]
 * @version 1.0
 * @see Utente
 */

public class Medico extends Utente {
    private String ruolo;
    private String specializzazione;
    private String via;
    private String civico;
    private String citta;

    /**
     * Restituisce il ruolo del medico.
     *
     * @return una stringa che rappresenta il ruolo del medico.
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo del medico.
     *
     * @param ruolo una stringa che rappresenta il ruolo del medico.
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce la specializzazione del medico.
     *
     * @return una stringa che rappresenta la specializzazione del medico.
     */
    public String getSpecializzazione() {
        return specializzazione;
    }

    /**
     * Imposta la specializzazione del medico.
     *
     * @param specializzazione una stringa che rappresenta la specializzazione del medico.
     */
    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    /**
     * Restituisce la via dell'indirizzo del medico.
     *
     * @return una stringa che rappresenta la via.
     */
    public String getVia() {
        return via;
    }

    /**
     * Imposta la via dell'indirizzo del medico.
     *
     * @param via una stringa che rappresenta la via.
     */
    public void setVia(String via) {
        this.via = via;
    }

    /**
     * Restituisce il numero civico dell'indirizzo del medico.
     *
     * @return una stringa che rappresenta il numero civico.
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Imposta il numero civico dell'indirizzo del medico.
     *
     * @param civico una stringa che rappresenta il numero civico.
     */
    public void setCivico(String civico) {
        this.civico = civico;
    }

    /**
     * Restituisce la città dell'indirizzo del medico.
     *
     * @return una stringa che rappresenta la città.
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Imposta la città dell'indirizzo del medico.
     *
     * @param citta una stringa che rappresenta la città.
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }
}
