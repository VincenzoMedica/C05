package model.prenotazione;

public class Prenotazione {
    private int id;
    private String stato;
    private String nota;
    private int idPaziente;
    private int idDisponibilita;

    public Prenotazione() {}

    public Prenotazione(String nota, int idPaziente, int idDisponibilita) {
        this.nota = nota;
        this.idPaziente = idPaziente;
        this.idDisponibilita = idDisponibilita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }

    public int getIdDisponibilita() {
        return idDisponibilita;
    }

    public void setIdDisponibilita(int idDisponibilita) {
        this.idDisponibilita = idDisponibilita;
    }

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
