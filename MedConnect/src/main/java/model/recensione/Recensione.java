package model.recensione;

public class Recensione {
    private int id_recensione;
    private int id_prenotazione;
    private int id_medico;
    private int id_paziente;
    private String nota;
    private int stelle;

    public Recensione() {
        id_recensione = -1;
    }

    public Recensione(int id_recensione, int id_prenotazione, int id_medico, int id_paziente, String nota, int stelle) {
        this.id_recensione = id_recensione;
        this.id_prenotazione = id_prenotazione;
        this.id_medico = id_medico;
        this.id_paziente = id_paziente;
        this.nota = nota;
        this.stelle = stelle;
    }

    public int getId_recensione() {
        return id_recensione;
    }

    public void setId_recensione(int id_recensione) {
        this.id_recensione = id_recensione;
    }

    public int getId_prenotazione() {
        return id_prenotazione;
    }

    public void setId_prenotazione(int id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_paziente() {
        return id_paziente;
    }

    public void setId_paziente(int id_paziente) {
        this.id_paziente = id_paziente;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }
}
