package net.cortex.controllers;

import org.slf4j.Logger;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import net.cortex.logging.Log;
import ratpack.guice.Guice;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.server.RatpackServer;


public class BootController implements Controller{
	
	@Log Logger log ;
	
	@Inject
	Injector inj ;

	@Override
	public void init() throws Exception {
	
		 RatpackServer	 
		 .start(server -> server 
				// .serverConfig(ServerConfig.embedded())
				 .registry(Guice.registry(inj))
			     .handlers(chain -> chain
			       .get( "hello" , InjectedHandler.class)
			       //.get(ctx -> ctx.render("Hello World!"))
			       //.get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))     
			     )
			   );
		
	}
		
	@Singleton
	 public static class InjectedHandler implements Handler {
	    //private final SomeService service;
		
		@Log Logger log ;
				
	    @Inject
	    public InjectedHandler() {
	      //this.service = service;
	    }

	    public void handle(Context ctx) {
	    	log.info("handler");
	    	ctx.render("Hello World!");
	      //ctx.render(service.getValue() + "-" + ctx.get(SomeOtherService.class).getValue());
	    }
	  }
}
