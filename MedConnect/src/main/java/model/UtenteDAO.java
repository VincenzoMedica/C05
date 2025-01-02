package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtenteDAO {


    public Utente doRetrieveByUsernamePassword(String email, String password) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utente utente = null;

        try (Connection connection = ConPool.getConnection()) {
            statement = connection.prepareStatement(
                    "SELECT id, nome, cognome, genere, data_nascita, luogo_nascita, email, pass, num_cellulare, cf, biografia\n" +
                        "FROM utenteregistrato WHERE email=? AND pass=SHA1(?)"
            );
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utente = new Utente();
                utente.setId(resultSet.getInt("id"));
                utente.setNome(resultSet.getString("nome"));
                utente.setCognome(resultSet.getString("cognome"));
                utente.setGenere(resultSet.getString("genere"));

                // Estrae la data e la trasforma in un'istanza di GregorianCalendar
                Date dataNascitaSql = resultSet.getDate("data_nascita");
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                if (dataNascitaSql != null) {
                    gregorianCalendar.setTime(new Date(dataNascitaSql.getTime()));
                }
                utente.setData_nascita(gregorianCalendar);

                utente.setLuogo_nascita(resultSet.getString("luogo_nascita"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPass(password); // Forniamo la password non hashata come input
                utente.setNum_cellulare(resultSet.getString("num_cellulare"));
                utente.setCf(resultSet.getString("cf"));
                utente.setBiografia(resultSet.getString("biografia"));
            }
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return utente;
    }

}
