package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class PrenotazioneDAO {


    public static ArrayList<Prenotazione> doRetrieveById_utente(int id_utente) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT id_prenotazione, stato, nota, id_paziente, id_disponibilita " +
                        "FROM Prenotazione " +
                        "WHERE id_paziente = ?");
            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();

            ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
            while (rs.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setId_prenotazione(Integer.parseInt(rs.getString("id_prenotazione")));
                prenotazione.setStato(rs.getString("stato"));
                prenotazione.setNota(rs.getString("nota"));
                prenotazione.setId_paziente(Integer.parseInt(rs.getString("id_paziente")));
                prenotazione.setId_disponibilita(Integer.parseInt(rs.getString("id_disponibilita")));


                prenotazioni.add(prenotazione);
            }
            return prenotazioni;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }


}
