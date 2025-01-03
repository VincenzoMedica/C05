package model.disponibilita;

import java.util.Date;

public class Disponibilita {
    private int id;
    private Date data;
    private String oraIn;
    private String oraFi;
    private int idMedico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOraIn() {
        return oraIn;
    }

    public void setOraIn(String oraIn) {
        this.oraIn = oraIn;
    }

    public String getOraFi() {
        return oraFi;
    }

    public void setOraFi(String oraFi) {
        this.oraFi = oraFi;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public String toString() {
        return "Disponibilita{" +
                "id=" + id +
                ", data=" + data +
                ", oraIn='" + oraIn + '\'' +
                ", oraFi='" + oraFi + '\'' +
                ", idMedico=" + idMedico +
                '}';
    }
}
