package model.recensione;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe {@code RecensioneDAO} fornisce metodi per gestire le operazioni
 * di accesso al database relative alle recensioni, come il salvataggio e la verifica di esistenza.
 */
public class RecensioneDAO {

    /**
     * Recupera l'identificativo del medico associato a una specifica prenotazione.
     *
     * @param id_prenotazione l'identificativo della prenotazione.
     * @return l'ID del medico associato se trovato; {@code -1} altrimenti.
     */
    public static int getIdMedicoByPrenotazione(int id_prenotazione) {
        // Prova a stabilire una connessione al database
        try (Connection con = ConPool.getConnection()) {
            // Query per recuperare l'ID del medico
            String query = "SELECT DISTINCT D.id_medico " +
                    "FROM disponibilita D, prenotazione P " +
                    "WHERE P.ID_prenotazione = ? " +
                    "AND D.ID_disponibilita = P.ID_disponibilita";

            // Crea un PreparedStatement per eseguire la query
            try (PreparedStatement ps = con.prepareStatement(query)) {
                // Imposta il parametro ID_prenotazione nella query
                ps.setInt(1, id_prenotazione);

                // Esegui la query e ottieni il risultato
                ResultSet rs = ps.executeQuery();

                // Controlla se il risultato esiste e restituisci l'ID del medico
                if (rs.next()) {
                    return rs.getInt("id_medico");
                }
            }

        } catch (SQLException e) {
            // Gestisce eventuali errori SQL generando un'eccezione runtime
            throw new RuntimeException(e);
        }

        // Se non ci sono risultati, ritorna -1
        return -1;
    }

    /**
     * Salva una nuova recensione nel database.
     *
     * @param recensione l'oggetto {@code Recensione} da salvare.
     * @return {@code true} se la recensione è stata salvata con successo; {@code false} altrimenti.
     */
    public static boolean doSave(Recensione recensione) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensione (ID_prenotazione, ID_medico, ID_paziente, nota, stelle) " +
                    " VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, recensione.getId_prenotazione());
            ps.setInt(2, recensione.getId_medico());
            ps.setInt(3, recensione.getId_paziente());
            ps.setString(4, recensione.getNota());
            ps.setInt(5, recensione.getStelle());

            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Verifica l'esistenza di una recensione associata a una specifica prenotazione.
     *
     * @param id_prenotazione l'identificativo della prenotazione.
     * @return {@code true} se esiste una recensione per la prenotazione; {@code false} altrimenti.
     */
    public static boolean existsRecensioneForPrenotazione(int id_prenotazione) {
        // Prova a stabilire una connessione al database
        try (Connection con = ConPool.getConnection()) {
            // Query per verificare l'esistenza di una recensione per l'ID specificato
            String checkQuery = "SELECT COUNT(*) > 0 AS exists_review " +
                    "FROM recensione WHERE ID_prenotazione = ?";

            // Crea un PreparedStatement per eseguire la query
            try (PreparedStatement checkPs = con.prepareStatement(checkQuery)) {
                // Imposta il parametro ID_prenotazione nella query
                checkPs.setInt(1, id_prenotazione);

                // Esegui la query e ottieni il risultato
                ResultSet rs = checkPs.executeQuery();

                // Controlla se il risultato esiste e restituisci il valore booleano
                if (rs.next()) {
                    return rs.getBoolean("exists_review");
                }
            }

        } catch (SQLException e) {
            // Gestisce eventuali errori SQL generando un'eccezione runtime
            throw new RuntimeException(e);
        }

        // Se non ci sono risultati, ritorna false
        return false;
    }
}



