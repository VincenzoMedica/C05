package model;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConPool {
	private static DataSource datasource;
	private static final Environment environment = new Environment();

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
