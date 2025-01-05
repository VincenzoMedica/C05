package model.utente;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;

/**
 * La classe {@code Utente} rappresenta un'entità utente con le sue informazioni personali e metodi di gestione.
 *
 * @author [C05]
 * @version 1.0
 */
public class Utente {
    private int id;
    private String email;
    private String pass;
    private String nome;
    private String cognome;
    private String cf;
    private String genere;
    private String biografia;
    private GregorianCalendar data_nascita;
    private String luogo_nascita;
    private String num_cellulare;

    /**
     * Costruttore vuoto di default.
     */
    public Utente() {
    }

    /**
     * Costruttore completo per creare un'istanza di {@code Utente} con tutte le proprietà.
     *
     * @param id            l'ID dell'utente.
     * @param email         l'email dell'utente.
     * @param pass          la password dell'utente.
     * @param nome          il nome dell'utente.
     * @param cognome       il cognome dell'utente.
     * @param cf            il codice fiscale dell'utente.
     * @param genere        il genere dell'utente.
     * @param biografia     la biografia dell'utente.
     * @param data_nascita  la data di nascita dell'utente.
     * @param luogo_nascita il luogo di nascita dell'utente.
     * @param num_cellulare il numero di cellulare dell'utente.
     */
    public Utente(int id, String email, String pass, String nome, String cognome, String cf, String genere, String biografia, GregorianCalendar data_nascita, String luogo_nascita, String num_cellulare) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.genere = genere;
        this.biografia = biografia;
        this.data_nascita = data_nascita;
        this.luogo_nascita = luogo_nascita;
        this.num_cellulare = num_cellulare;
    }

    /**
     * Restituisce l'ID dell'utente.
     *
     * @return l'ID dell'utente.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'utente.
     *
     * @param id l'ID da impostare.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce l'email dell'utente.
     *
     * @return l'email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'utente.
     *
     * @param email l'email da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente (cifrata).
     *
     * @return la password cifrata.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Imposta la password dell'utente, cifrandola con l'algoritmo SHA-1.
     *
     * @param pass la password da impostare (non cifrata).
     * @throws RuntimeException se si verifica un errore durante la cifratura.
     */
    public void setPass(String pass) {
        // password è inserita dall’utente
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes(StandardCharsets.UTF_8));
            this.pass = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome il nome da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome il cognome da impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il codice fiscale dell'utente.
     *
     * @return il codice fiscale dell'utente.
     */
    public String getCf() {
        return cf;
    }

    /**
     * Imposta il codice fiscale dell'utente.
     *
     * @param cf il codice fiscale da impostare.
     */
    public void setCf(String cf) {
        this.cf = cf;
    }

    /**
     * Restituisce il genere dell'utente.
     *
     * @return il genere dell'utente.
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Imposta il genere dell'utente.
     *
     * @param genere il genere da impostare.
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * Restituisce la biografia dell'utente.
     *
     * @return la biografia dell'utente.
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * Imposta la biografia dell'utente.
     *
     * @param biografia la biografia da impostare.
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * Restituisce la data di nascita dell'utente.
     *
     * @return la data di nascita.
     */
    public GregorianCalendar getData_nascita() {
        return data_nascita;
    }

    /**
     * Imposta la data di nascita dell'utente.
     *
     * @param data_nascita la data di nascita da impostare.
     */
    public void setData_nascita(GregorianCalendar data_nascita) {
        this.data_nascita = data_nascita;
    }

    /**
     * Restituisce il luogo di nascita dell'utente.
     *
     * @return il luogo di nascita.
     */
    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    /**
     * Imposta il luogo di nascita dell'utente.
     *
     * @param luogo_nascita il luogo di nascita da impostare.
     */
    public void setLuogo_nascita(String luogo_nascita) {
        this.luogo_nascita = luogo_nascita;
    }

    /**
     * Restituisce il numero di cellulare dell'utente.
     *
     * @return il numero di cellulare.
     */
    public String getNum_cellulare() {
        return num_cellulare;
    }

    /**
     * Imposta il numero di cellulare dell'utente.
     *
     * @param num_cellulare il numero di cellulare da impostare.
     */
    public void setNum_cellulare(String num_cellulare) {
        this.num_cellulare = num_cellulare;
    }
}

