package model.prenotazione;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PrenotazioneDAO {

    public int doSave(Prenotazione prenotazione) {
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

}
