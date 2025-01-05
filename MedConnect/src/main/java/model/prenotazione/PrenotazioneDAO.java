package model.prenotazione;

import model.ConPool;
import model.disponibilita.Disponibilita;
import model.medico.Medico;
import model.utente.Utente;

import java.sql.*;
import java.util.ArrayList;

/**
 * La classe {@code PrenotazioneDAO} gestisce le operazioni di accesso al database
 * per la tabella delle prenotazioni.
 *
 * @author [C05]
 * @version 1.0
 */
public class PrenotazioneDAO {

    /**
     * Salva una prenotazione nel database.
     *
     * @param prenotazione l'oggetto {@code Prenotazione} da salvare.
     * @return l'ID generato della prenotazione se il salvataggio ha successo, -1 altrimenti.
     */
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

    /**
     * Recupera le prenotazioni di un utente in base al suo ID.
     *
     * @param idUtente       l'ID dell'utente.
     * @param prenotaziones  una lista in cui verranno aggiunte le prenotazioni recuperate.
     * @param disponibilitas una lista in cui verranno aggiunte le disponibilità associate.
     * @param medicos        una lista in cui verranno aggiunti i medici associati.
     * @return {@code true} se le prenotazioni sono state recuperate correttamente, {@code false} altrimenti.
     */
    public static boolean doRetrieveByIdUtente(int idUtente, ArrayList<Prenotazione> prenotaziones, ArrayList<Disponibilita> disponibilitas, ArrayList<Medico> medicos) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT DISTINCT P.id_prenotazione, P.stato, P.nota, P.id_paziente, P.id_disponibilita, " +
                            "D.ID_disponibilita, D.data, D.ora_in, D.ora_fi, D.ID_medico, " +
                            "U.ID AS id_utente, U.nome, U.cognome, " +
                            "M.ID AS id_medico, M.via, M.civico, M.citta " +
                            "FROM Prenotazione P " +
                            "JOIN Disponibilita D ON P.ID_disponibilita = D.ID_disponibilita " +
                            "JOIN Medico M ON D.ID_medico = M.ID " +
                            "JOIN utenteregistrato U ON M.ID = U.ID " +
                            "WHERE P.id_paziente = ?");

            ps.setInt(1, idUtente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Creazione dell'oggetto Disponibilita
                Disponibilita disponibilita = new Disponibilita();
                disponibilita.setId(rs.getInt("ID_disponibilita"));
                disponibilita.setData(rs.getDate("data"));
                disponibilita.setOraIn(rs.getString("ora_in"));
                disponibilita.setOraFi(rs.getString("ora_fi"));
                disponibilitas.add(disponibilita);

                // Creazione dell'oggetto Medico
                Medico medico = new Medico();
                medico.setId(rs.getInt("id_medico"));
                medico.setVia(rs.getString("via"));
                medico.setCivico(rs.getString("civico"));
                medico.setCitta(rs.getString("citta"));


                // Creazione dell'oggetto Utente

                medico.setId(rs.getInt("id_utente"));
                medico.setNome(rs.getString("nome"));
                medico.setCognome(rs.getString("cognome"));

                medicos.add(medico);
                // Creazione dell'oggetto Prenotazione
                Prenotazione prenotazione = new Prenotazione();
                if (prenotazione.setId(rs.getInt("id_prenotazione")) && prenotazione.setStato(rs.getString("stato")) && prenotazione.setNota(rs.getString("nota")) && prenotazione.setIdPaziente(rs.getInt("id_paziente")))
                    prenotaziones.add(prenotazione);
                else
                    return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero delle prenotazioni", e);
        }
    }


}
