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

public class MedicoDAO {
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

    public List<Medico> doRetrieve(String ruoloNome, String citta) {
        try (Connection con = ConPool.getConnection()) {
            List<Medico> medicoList = new ArrayList<>();
            PreparedStatement ps =
                    con.prepareStatement("select m.*, u.email, u.nome, u.cognome, u.biografia, u.data_nascita, u.luogo_nascita, u.num_cellulare, u.genere\n" +
                            "    from medico m\n" +
                            "        inner join utenteregistrato u on m.ID = u.ID\n" +
                            "    where (m.Ruolo like ? OR u.nome like ? OR u.cognome like ?) AND m.citta like ?;");
            ps.setString(1, '%'+ruoloNome+'%');
            ps.setString(2, '%'+ruoloNome+'%');
            ps.setString(3, '%'+ruoloNome+'%');
            ps.setString(4, '%'+citta+'%');
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
