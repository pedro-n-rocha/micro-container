package net.cortex.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import net.cortex.configuration.ConfigurationModule;
import net.cortex.logging.SLF4JTypeListener;
import net.cortex.persistence.PersistenceModule;

public class Registry extends AbstractModule {

	@Override
	protected void configure() {

		bindListener(Matchers.any(), new SLF4JTypeListener());
		install(new ConfigurationModule());
		install(new PersistenceModule());
	}
	
}
