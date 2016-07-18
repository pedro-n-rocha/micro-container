package net.cortex.configuration;

import java.util.concurrent.TimeUnit;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.consul.ConsulConfigurationSourceBuilder;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ConfigurationModule extends AbstractModule{
	
	static  ConfigurationSource source = new ConsulConfigurationSourceBuilder()
			.withHost(System.getenv("CONSUL_HOST"))
			.withPort(Integer.parseInt(System.getenv("CONSUL_PORT"))).build();
	
	static ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);
	
	static ConfigurationProvider configProvider ;
	
	private AppConfig appConfig ; 
	
	@Override
	protected void configure() {
		
		configProvider = new ConfigurationProviderBuilder().withConfigurationSource(source).withReloadStrategy(reloadStrategy)
		.build();
		Names.bindProperties(binder(),configProvider.allConfigurationAsProperties());
			
		appConfig = configProvider.bind("application", AppConfig.class);
		
		bind(AppConfig.class).toInstance(appConfig);	
		
		bind(ConfigurationProvider.class).toInstance(configProvider);
		
	}
	
	
	public interface AppConfig {
	
		String logo();
		String version();
	} 

}
