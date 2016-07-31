package net.cortex.persistence;

import javax.sql.DataSource;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DataSourceProvider implements Provider<DataSource> {

	@Inject
	@Named("db.class.name")
	private String dataSourceClassName;

	@Inject
	@Named("db.username")
	private String dbUser;

	@Inject
	@Named("db.password")
	private String dbPassword;

	@Inject
	@Named("db.name")
	private String dbName;

	@Override
	public DataSource get() {
		   HikariConfig config = new HikariConfig();
	        config.setDataSourceClassName(dataSourceClassName);
	        config.addDataSourceProperty("databaseName", dbName);
	        config.addDataSourceProperty("user", dbUser);
	        config.addDataSourceProperty("password", dbPassword);
	        return new HikariDataSource(config);
	}
}
