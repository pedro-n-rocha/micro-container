package net.cortex.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import net.cortex.configuration.ConfigurationModule;
import net.cortex.controllers.BootController;
import net.cortex.controllers.BootController.InjectedHandler;
import net.cortex.controllers.Controller;
import net.cortex.logging.SLF4JTypeListener;

public class Registry extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new SLF4JTypeListener());
		bind(Controller.class).to(BootController.class).asEagerSingleton();
		bind(InjectedHandler.class);
		install(new ConfigurationModule());
	}
}
