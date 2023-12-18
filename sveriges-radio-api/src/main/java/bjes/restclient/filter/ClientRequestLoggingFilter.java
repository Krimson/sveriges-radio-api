package bjes.restclient.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientRequestLoggingFilter implements ClientRequestFilter {
    Logger LOG = LoggerFactory.getLogger(ClientRequestLoggingFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        LOG.debug("Outgoing Request: {}: {} {} Headers: {} body: {}", requestContext.getMethod(), requestContext.getUri(), requestContext.getMediaType(), requestContext.getHeaders(), requestContext.getEntity());
    }
}
