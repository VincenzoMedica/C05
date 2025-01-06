package model.disponibilita;

import model.ConPool;
import model.prenotazione.Prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibilitaDAO {
    private void setDisponibilitaFromDb(Disponibilita disponibilita, ResultSet resultSet) throws SQLException {
        disponibilita.setId(resultSet.getInt(1));
        disponibilita.setData(resultSet.getDate(2));
        disponibilita.setOraIn(resultSet.getString(3));
        disponibilita.setOraFi(resultSet.getString(4));
        disponibilita.setIdMedico(resultSet.getInt(5));
    }

    public List<Disponibilita> doRetrieveByIdMedico(int idMedico) {
        try (Connection con = new ConPool().getConnection()) {
            List<Disponibilita> disponibilitaList = new ArrayList<>();
            PreparedStatement ps =
                    con.prepareStatement("select * from disponibilita d where d.ID_medico=? and d.ID_disponibilita not in (select p.ID_disponibilita from prenotazione p) order by data, ora_in;");
            ps.setInt(1, idMedico);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Disponibilita d = new Disponibilita();
                setDisponibilitaFromDb(d, rs);
                disponibilitaList.add(d);
            }
            return disponibilitaList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Disponibilita doRetrieveById_disponibilita(int id_disponibilita) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT DISTINCT D.ID_disponibilita, D.data, D.ora_in, D.ora_fi, D.ID_medico\n" +
                        "FROM disponibilita D\n" +
                        "WHERE D.ID_disponibilita = ?");
            ps.setInt(1, id_disponibilita);
            ResultSet rs = ps.executeQuery();

            Disponibilita disponibilita = new Disponibilita();
            while (rs.next()) {
                disponibilita.setId(Integer.parseInt(rs.getString("ID_disponibilita")));
                disponibilita.setData(rs.getDate("data"));
                disponibilita.setOraIn(rs.getString("ora_in"));
                disponibilita.setOraFi(rs.getString("ora_fi"));
                disponibilita.setIdMedico(Integer.parseInt(rs.getString("ID_medico")));
            }
            return disponibilita;

        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
    }
}
