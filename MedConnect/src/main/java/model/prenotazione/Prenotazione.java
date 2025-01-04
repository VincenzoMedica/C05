package model.prenotazione;

import java.util.regex.Pattern;

public class Prenotazione {
    private int id;
    private String stato;
    private String nota;
    private int idPaziente;
    private int idDisponibilita;

    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(id)).matches()) {
            this.id = id;
            return true;
        }
        else return false;
    }

    public String getStato() {
        return stato;
    }

    public boolean setStato(String stato) {
        if(stato != null && Pattern.compile("^(Da Completare|Completato)$").matcher(stato).matches()) {
            this.stato = stato;
            return true;
        }
        else return false;
    }

    public String getNota() {
        return nota;
    }

    public boolean setNota(String nota) {
        if (nota != null && Pattern.compile("^.{0,255}$").matcher(nota).matches()) {
            this.nota = nota;
            return true;
        }
        return false;
    }

    public int getIdPaziente() {
        return idPaziente;
    }

    public boolean setIdPaziente(int idPaziente) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(idPaziente)).matches()) {
            this.idPaziente = idPaziente;
            return true;
        }
        else return false;
    }

    public int getIdDisponibilita() {
        return idDisponibilita;
    }

    public boolean setIdDisponibilita(int idDisponibilita) {
        if (Pattern.compile("^\\d+$").matcher(String.valueOf(idDisponibilita)).matches()) {
            this.idDisponibilita = idDisponibilita;
            return true;
        }
        else return false;
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
