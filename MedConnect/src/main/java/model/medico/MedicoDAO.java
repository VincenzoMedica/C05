package model.medico;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * La classe {@code MedicoDAO} gestisce le operazioni di accesso ai dati per gli oggetti {@link Medico}.
 * Consente di recuperare informazioni sui medici dal database utilizzando vari criteri di ricerca.
 *
 * @author [C05]
 * @version 1.0
 * @see Medico
 */
public class MedicoDAO {

    /**
     * Popola un oggetto {@code Medico} con i dati recuperati da un {@code ResultSet}.
     *
     * @param medico    l'oggetto {@code Medico} da popolare.
     * @param resultSet il {@code ResultSet} contenente i dati del medico.
     * @throws SQLException se si verifica un errore durante l'accesso ai dati.
     */
    private void setMedicoFromDb(Medico medico, ResultSet resultSet) throws SQLException {
        medico.setId(resultSet.getInt(1));
        medico.setRuolo(resultSet.getString(2));
        medico.setSpecializzazione(resultSet.getString(3));
        medico.setVia(resultSet.getString(4));
        medico.setCivico(resultSet.getString(5));
        medico.setCitta(resultSet.getString(6));

        medico.setEmail(resultSet.getString(7));
        medico.setNome(resultSet.getString(8));
        medico.setCognome(resultSet.getString(9));
        medico.setBiografia(resultSet.getString(10));

        Date dataNascitaSql = resultSet.getDate(11);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (dataNascitaSql != null) {
            gregorianCalendar.setTime(new Date(dataNascitaSql.getTime()));
        }
        medico.setData_nascita(gregorianCalendar);

        medico.setLuogo_nascita(resultSet.getString(12));
        medico.setNum_cellulare(resultSet.getString(13));
        medico.setGenere(resultSet.getString(14));
    }

    /**
     * Recupera un medico dal database utilizzando il suo ID.
     *
     * @param id l'ID del medico da recuperare.
     * @return l'oggetto {@code Medico} associato all'ID specificato o {@code null} se non esiste.
     * @throws RuntimeException se si verifica un errore di accesso al database.
     */
    public Medico doRetrieveById(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select m.*, u.email, u.nome, u.cognome, u.biografia, u.data_nascita, u.luogo_nascita, u.num_cellulare, u.genere " +
                            "from medico m " +
                            "inner join utenteregistrato u on m.ID = u.ID " +
                            "where m.ID=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Medico m = new Medico();
                setMedicoFromDb(m, rs);
                return m;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera una lista di medici dal database che corrispondono ai criteri di ricerca specificati.
     *
     * @param ruoloNome una stringa contenente il ruolo o il nome del medico come criterio di ricerca.
     * @param citta     il nome della città in cui cercare i medici.
     * @return una lista di oggetti {@code Medico} che corrispondono ai criteri di ricerca.
     * @throws RuntimeException se si verifica un errore di accesso al database.
     */
    public List<Medico> doRetrieve(String ruoloNome, String citta) {
        try (Connection con = ConPool.getConnection()) {
            List<Medico> medicoList = new ArrayList<>();

            // Dividi ruoloNome in parole chiave
            String[] keywords = ruoloNome.split("\\s+");

            // Aggiungi condizioni per ogni parola chiave
            String queryBuilder = "select m.*, u.email, u.nome, u.cognome, u.biografia, u.data_nascita, u.luogo_nascita, u.num_cellulare, u.genere " +
                    "from medico m " +
                    "inner join utenteregistrato u on m.ID = u.ID " +
                    "where m.citta like ? " + "AND (m.Ruolo like ? OR u.nome like ? OR u.cognome like ?) ".repeat(keywords.length);

            PreparedStatement ps = con.prepareStatement(queryBuilder);

            // Imposta i parametri della query
            ps.setString(1, '%' + citta + '%');
            int paramIndex = 2;
            for (String keyword : keywords) {
                ps.setString(paramIndex++, '%' + keyword + '%'); // m.Ruolo
                ps.setString(paramIndex++, '%' + keyword + '%'); // u.nome
                ps.setString(paramIndex++, '%' + keyword + '%'); // u.cognome
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medico m = new Medico();
                setMedicoFromDb(m, rs);
                medicoList.add(m);
            }
            return medicoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
