package bjes.config;

import bjes.api.EchoController;
import bjes.api.SverigesRadioController;
import bjes.restclient.filter.ClientRequestLoggingFilter;
import bjes.restclient.filter.ClientResponseLoggingFilter;
import io.swagger.v3.jaxrs2.SwaggerSerializers;
import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@Primary
@EnableConfigurationProperties({ApplicationContextPath.class, ApplicationProperties.class})
public class JerseyConfig extends ResourceConfig {
    Logger LOG = LoggerFactory.getLogger(JerseyConfig.class);
    private final ApplicationContextPath applicationContextPath;
    private final ApplicationProperties applicationProperties;
    private ServletConfig servletConfig;

    public JerseyConfig(ApplicationContextPath applicationContextPath, ApplicationProperties applicationProperties) {
        this.applicationContextPath = applicationContextPath;
        this.applicationProperties = applicationProperties;
        registerEndpoints();
        configureSwagger();
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }

    private void registerEndpoints() {
        register(EchoController.class);
        register(SverigesRadioController.class);
        register(ClientRequestLoggingFilter.class);
        register(ClientResponseLoggingFilter.class);
    }

    public void configureSwagger() {
        this.register(OpenApiResource.class);
        this.register(SwaggerSerializers.class);

        OpenAPI oas = new OpenAPI();
        Info info = new Info()
                .title(applicationProperties.getName())
                .version(applicationProperties.getVersion())
                .description(applicationProperties.getDescription());

        oas.info(info);
        oas.servers(List.of(new Server().url(applicationContextPath.getContextPath())));
        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(oas)
                .prettyPrint(true)
                .resourcePackages(Stream.of(this.getClass().getPackageName()).collect(Collectors.toSet()));

        LOG.info("Configuring swagger: trying to create context builder");
        LOG.debug("DEBUG LOG LEVEL");
        try {
            new JaxrsOpenApiContextBuilder()
                    .servletConfig(servletConfig)
                    .application(this)
                    .openApiConfiguration(oasConfig)
                    .buildContext(true);
            LOG.info("Configuring swagger: done");
        } catch (OpenApiConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}