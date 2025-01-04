package model.recensione;

import java.util.regex.Pattern;

public class Recensione {
    private int id_recensione;
    private int id_prenotazione;
    private int id_medico;
    private int id_paziente;
    private String nota;
    private int stelle;

    public Recensione() {}

    public int getId_recensione() {
        return id_recensione;
    }

    public boolean setId_recensione(int id_recensione) {
        if(Pattern.compile("^\\d+$").matcher(String.valueOf(id_recensione)).matches()){
            this.id_recensione = id_recensione;
            return true;
        }else{
            return false;
        }
    }

    public int getId_prenotazione() {
        return id_prenotazione;
    }

    public boolean setId_prenotazione(int id_prenotazione) {
        if(Pattern.compile("^\\d+$").matcher(String.valueOf(id_prenotazione)).matches()){
            this.id_prenotazione = id_prenotazione;
            return true;
        }else{
            return false;
        }
    }

    public int getId_medico() {
        return id_medico;
    }

    public boolean setId_medico(int id_medico) {
        if(Pattern.compile("^\\d+$").matcher(String.valueOf(id_medico)).matches()){
            this.id_medico = id_medico;
            return true;
        }else{
            return false;
        }
    }

    public int getId_paziente() {
        return id_paziente;
    }

    public boolean setId_paziente(int id_paziente) {
        if(Pattern.compile("^\\d+$").matcher(String.valueOf(id_paziente)).matches()){
            this.id_paziente = id_paziente;
            return true;
        }else{
            return false;
        }
    }

    public String getNota() {
        return nota;
    }

    public boolean setNota(String nota) {
        if(nota != null && Pattern.compile("^.{0,255}$").matcher(nota).matches()){
            this.nota = nota;
            return true;
        }else{
            return false;
        }
    }

    public int getStelle() {
        return stelle;
    }

    public boolean setStelle(int stelle) {
        if(Pattern.compile("^[1-5]$").matcher(String.valueOf(stelle)).matches()){
            this.stelle = stelle;
            return true;
        } else {
            return false;
        }
    }
}
