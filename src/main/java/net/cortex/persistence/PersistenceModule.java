package net.cortex.persistence;

import javax.sql.DataSource;

import org.jooq.DSLContext;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
	
		bind(DataSource.class).toProvider(DataSourceProvider.class).in(Singleton.class);
		bind(DSLContext.class).toProvider(JooqProvider.class).in(Singleton.class);

	}
}