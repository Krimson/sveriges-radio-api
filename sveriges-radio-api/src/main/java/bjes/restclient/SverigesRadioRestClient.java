package bjes.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.jersey.jackson.internal.DefaultJacksonJaxbJsonProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sveriges.radio.api")
public class SverigesRadioRestClient {
    private Client defaultClient;
    private String url;

    @PostConstruct
    void init(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DefaultJacksonJaxbJsonProvider jacksonJsonProvider = new DefaultJacksonJaxbJsonProvider();
        jacksonJsonProvider.setMapper(mapper);

        this.defaultClient = ClientBuilder.newClient()
                .register(jacksonJsonProvider);
    }

    public WebTarget getDefaultClient() {
        return defaultClient.target(url);
    }

    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url=url;
    }
}
