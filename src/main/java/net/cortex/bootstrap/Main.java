package net.cortex.bootstrap;

import org.cfg4j.provider.ConfigurationProvider;
import org.slf4j.Logger;
import com.google.inject.*;
import com.google.inject.name.Named;
import net.cortex.configuration.ConfigurationModule.AppConfig;
import net.cortex.logging.Log;

public class Main {

	@Inject
	@Named("application.logo")
	String logo;

	@Inject
	@Named("application.version")
	String version;

	@Inject
	ConfigurationProvider cfg;

	@Inject
	AppConfig appConfig;

	@Log
	Logger log;

	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new Registry());
		injector.getInstance(Main.class).start();
	}

	private void start() throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("...::: app shutdown started :::...");
			}
		});

	}

}