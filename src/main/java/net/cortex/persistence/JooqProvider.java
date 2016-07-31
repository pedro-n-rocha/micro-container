package net.cortex.persistence;

import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;

import com.google.inject.Inject;
import com.google.inject.Provider;

import org.jooq.SQLDialect;

import javax.sql.DataSource;
import org.jooq.impl.DSL;
import org.jooq.Configuration;

public class JooqProvider implements Provider<DSLContext> {

	private final DataSource dataSource;

	@Inject
	public JooqProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public DSLContext get() {
		Configuration configuration = new DefaultConfiguration()
				// .set(dataSource)
				.set(SQLDialect.H2);
		return DSL.using(configuration);
	}

}
