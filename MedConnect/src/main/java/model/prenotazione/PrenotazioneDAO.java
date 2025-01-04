package model.prenotazione;

import model.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class PrenotazioneDAO {

    public static int doSave(Prenotazione prenotazione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into prenotazione(nota, ID_paziente, ID_disponibilita) values (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prenotazione.getNota());
            ps.setInt(2, prenotazione.getIdPaziente());
            ps.setInt(3, prenotazione.getIdDisponibilita());
            if (ps.executeUpdate() != 1) {
                System.out.println("Prenotazione not saved");
                return -1;
            }
            return 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
                prenotazione.setId(Integer.parseInt(rs.getString("id_prenotazione")));
                prenotazione.setStato(rs.getString("stato"));
                prenotazione.setNota(rs.getString("nota"));
                prenotazione.setIdPaziente(Integer.parseInt(rs.getString("id_paziente")));
                prenotazione.setIdDisponibilita(Integer.parseInt(rs.getString("id_disponibilita")));


                prenotazioni.add(prenotazione);
            }
            return prenotazioni;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }

}
