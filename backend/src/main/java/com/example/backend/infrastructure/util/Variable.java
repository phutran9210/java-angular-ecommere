package com.example.backend.infrastructure.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "myapp")
public class Variable {
    private String secretKey;
    private String refreshTokenExpirationTime;
    private String accessTokenExpirationTime;
}
