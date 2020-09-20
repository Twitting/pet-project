package ru.twitting.petproject.config.property;

import ch.qos.logback.classic.Level;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Validated
@ConfigurationProperties("logbook.logger")
public class LogbookProperties {

    @NotEmpty
    private String name;

    @NotEmpty
    private String level;

    public Level getLevel() {
        return Level.valueOf(level);
    }
}