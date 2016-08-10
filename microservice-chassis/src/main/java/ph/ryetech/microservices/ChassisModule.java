package ph.ryetech.microservices;

import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

import ph.ryetech.microservices.config.ConfigModule;

import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class ChassisModule extends RequestScopeModule {

    @Override
    protected void configure() {
        bind(GuiceResteasyBootstrapServletContextListener.class);

        install(new ConfigModule());
        
        install(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(HttpServletDispatcher.class).in(Scopes.SINGLETON);
                serve("/*").with(HttpServletDispatcher.class);
            }
        });
    }

    @Provides
    @Singleton
    ResteasyJackson2Provider getJacksonJsonProvider() {
        return new ResteasyJackson2Provider();
    }
}
