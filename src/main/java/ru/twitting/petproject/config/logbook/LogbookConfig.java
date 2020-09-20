package ru.twitting.petproject.config.logbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HttpLogWriter;
import ru.twitting.petproject.config.property.LogbookProperties;

@Configuration
public class LogbookConfig {

    @Bean
    public HttpLogWriter writer(LogbookProperties properties) {
        return new CustomHttpLogWriter(properties);
    }

}