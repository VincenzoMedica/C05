package model;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;

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


    public Utente() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public GregorianCalendar getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(GregorianCalendar data_nascita) {
        this.data_nascita = data_nascita;
    }

    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    public void setLuogo_nascita(String luogo_nascita) {
        this.luogo_nascita = luogo_nascita;
    }

    public String getNum_cellulare() {
        return num_cellulare;
    }

    public void setNum_cellulare(String num_cellulare) {
        this.num_cellulare = num_cellulare;
    }
}

