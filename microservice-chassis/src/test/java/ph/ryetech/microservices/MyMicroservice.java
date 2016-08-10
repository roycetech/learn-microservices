package ph.ryetech.microservices;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Module;
import com.google.inject.Scopes;

import ph.ryetech.microservices.Microservice;

import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;


public class MyMicroservice extends Microservice {

    public static void main(String... args) {
        new MyMicroservice().run();
    }

    @Override
    public Module[] getModules() {
        return new Module[] {
            new RequestScopeModule() {
                @Override
                protected void configure()
                {
                    bind(DumbResource.class).in(Scopes.SINGLETON);
                }
            }
        };
    }

    @Path("/")
    public static class DumbResource {

        @GET
        @Produces("application/json")
        public Map<String, Boolean> getResource() {
            return ImmutableMap.of("value", true);
        }
    }
}
