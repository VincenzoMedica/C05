package model;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Classe di utilità per la gestione della connessione al database tramite un pool di connessioni.
 * Utilizza Apache Tomcat JDBC Connection Pool per la gestione delle connessioni.
 *
 * @author [C05]
 * @version 1.0
 */

public class ConPool {

	/**
	 * L'oggetto DataSource che gestisce il pool di connessioni.
	 */
	private static DataSource datasource;

	/**
	 * L'oggetto Environment che fornisce le configurazioni per il database.
	 */
	private static final Environment environment = new Environment();

	/**
	 * Restituisce una connessione dal pool di connessioni.
	 * Se il pool non è stato inizializzato, lo crea utilizzando le informazioni presenti nell'oggetto {@link Environment}.
	 *
	 * @return Una connessione al database.
	 * @throws SQLException Se si verifica un errore durante l'ottenimento della connessione.
	 */
	public static Connection getConnection() throws SQLException {
		if (datasource == null) {
			PoolProperties p = new PoolProperties();
			p.setUrl(environment.getDbUrl() + environment.getDbName() + "?serverTimezone=" + TimeZone.getDefault().getID());
			p.setDriverClassName(environment.getDbDriver());
			p.setUsername(environment.getDbUser());
			p.setPassword(environment.getDbPassword());
			p.setMaxActive(100);
			p.setInitialSize(10);
			p.setMinIdle(10);
			p.setRemoveAbandonedTimeout(60);
			p.setRemoveAbandoned(true);
			datasource = new DataSource();
			datasource.setPoolProperties(p);
		}
		return datasource.getConnection();
	}
}
