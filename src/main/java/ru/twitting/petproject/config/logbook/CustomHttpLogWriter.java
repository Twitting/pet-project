package ru.twitting.petproject.config.logbook;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;
import ru.twitting.petproject.config.property.LogbookProperties;

public class CustomHttpLogWriter implements HttpLogWriter {

    private final Logger logger;

    public CustomHttpLogWriter(LogbookProperties properties) {
        logger = LoggerFactory.getLogger(properties.getName());
        configure(properties);
    }

    @Override
    public boolean isActive() {
        return logger.isInfoEnabled();
    }

    @Override
    public void write(Precorrelation precorrelation, String request) {
        logger.info(request);
    }

    @Override
    public void write(Correlation correlation, String response) {
        logger.info(response);
    }

    private void configure(LogbookProperties properties) {
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger(properties.getName()).setLevel(properties.getLevel());
    }
}