package model.disponibilita;

import java.util.Date;

/**
 * La classe {@code Disponibilita} rappresenta un'entità che modella la disponibilità di un medico.
 * Contiene informazioni relative all'identificativo della disponibilità, la data, gli orari di inizio
 * e fine della disponibilità, e l'identificativo del medico associato.
 *
 * @author [C05]
 * @version 1.0
 * @see java.util.Date
 */

public class Disponibilita {
    private int id;
    private Date data;
    private String oraIn;
    private String oraFi;
    private int idMedico;

    /**
     * Restituisce l'identificativo della disponibilità.
     *
     * @return l'identificativo della disponibilità.
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'identificativo della disponibilità.
     *
     * @param id l'identificativo univoco della disponibilità.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce la data della disponibilità.
     *
     * @return la data della disponibilità.
     */
    public Date getData() {
        return data;
    }

    /**
     * Imposta la data della disponibilità.
     *
     * @param data la data della disponibilità.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Restituisce l'orario di inizio della disponibilità.
     *
     * @return l'orario di inizio della disponibilità.
     */
    public String getOraIn() {
        return oraIn;
    }

    /**
     * Imposta l'orario di inizio della disponibilità.
     *
     * @param oraIn l'orario di inizio nel formato "HH:mm:ss".
     * @pre oraIn deve essere un orario valido nel formato "HH:mm:ss".
     */
    public void setOraIn(String oraIn) {
        this.oraIn = oraIn;
    }

    /**
     * Restituisce l'orario di fine della disponibilità.
     *
     * @return l'orario di fine della disponibilità.
     */
    public String getOraFi() {
        return oraFi;
    }

    /**
     * Imposta l'orario di fine della disponibilità.
     *
     * @param oraFi l'orario di fine nel formato "HH:mm".
     * @pre oraFi deve essere un orario valido nel formato "HH:mm".
     */
    public void setOraFi(String oraFi) {
        this.oraFi = oraFi;
    }

    /**
     * Restituisce l'identificativo del medico associato alla disponibilità.
     *
     * @return l'identificativo del medico.
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * Imposta l'identificativo del medico associato alla disponibilità.
     *
     * @param idMedico l'identificativo del medico.
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * Restituisce una rappresentazione testuale della disponibilità.
     *
     * @return una stringa che rappresenta i dettagli della disponibilità.
     */
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
