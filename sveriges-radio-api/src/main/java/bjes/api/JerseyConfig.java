package bjes.api;

import jakarta.annotation.PostConstruct;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        registerEndpoints();
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }

    private void registerEndpoints() {
        register(EchoController.class);
    }

}