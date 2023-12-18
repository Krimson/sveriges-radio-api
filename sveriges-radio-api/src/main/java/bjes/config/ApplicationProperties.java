package bjes.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties("application.compile")
public class ApplicationProperties {
    private String version;
    private String name;
    private String description;
}

