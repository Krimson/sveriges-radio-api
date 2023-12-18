package bjes.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties("server.servlet")
public class ApplicationContextPath {
    private String contextPath;

}