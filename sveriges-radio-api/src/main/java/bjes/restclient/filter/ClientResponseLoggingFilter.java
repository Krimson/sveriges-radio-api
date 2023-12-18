package bjes.restclient.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ClientResponseLoggingFilter implements ClientResponseFilter {
    Logger LOG = LoggerFactory.getLogger(ClientResponseLoggingFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        long now = System.currentTimeMillis();
        long duration = System.currentTimeMillis() - now;

        final StringBuilder b = new StringBuilder();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final InputStream inputStream = responseContext.getEntityStream();
        ReaderWriter.writeTo(inputStream, out);
        byte[] requestEntity = out.toByteArray();
        b.append(new String(requestEntity));

        LOG.debug("{}ms response status: {} response body: {} on Request {} {} Body: {}", duration, responseContext.getStatus(), b, requestContext.getMethod(), requestContext.getUri(), requestContext.getEntity());
        responseContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    }
}
