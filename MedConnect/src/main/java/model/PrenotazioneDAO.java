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
                    "SELECT id_prenotazione, id_prenotazione, stato, nota, id_paziente, id_disponibilita " +
                        "FROM Prenotazione " +
                        "WHERE ID_paziente = ? ");
            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();

            ArrayList<Prenotazione> ordines = new ArrayList<>();
            while (rs.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setId_prenotazione(Integer.parseInt(rs.getString("id_prenotazione")));
                ordine.setPrezzo_totale(Double.parseDouble(rs.getString("prezzo_totale")));
                ordine.setVia(rs.getString("via"));
                ordine.setCivico(rs.getString("civico"));
                ordine.setCitta(rs.getString("citta"));

                //Estrae la data e la trasforma in un'istanza di GregorianCalendar
                Date dataNascitaSql = rs.getDate("data");
                Date dataNascitaJava = new Date(dataNascitaSql.getTime());
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(dataNascitaJava);
                ordine.setData(gregorianCalendar);

                ordine.setId_utenete(rs.getInt("id_utente"));
                ordines.add(ordine);
            }
            return ordines;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

}
