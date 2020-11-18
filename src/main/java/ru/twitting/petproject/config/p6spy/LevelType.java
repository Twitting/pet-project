package ru.twitting.petproject.config.p6spy;


import com.p6spy.engine.logging.Category;
import org.slf4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public enum LevelType {

    ERROR {
        @Override
        boolean isEnabled(Logger logger) {
            return logger.isErrorEnabled();
        }

        @Override
        void log(String message, Logger logger) {
            logger.error(message);
        }
    },
    WARN {
        @Override
        boolean isEnabled(Logger logger) {
            return logger.isWarnEnabled();
        }

        @Override
        void log(String message, Logger logger) {
            logger.warn(message);
        }
    },
    DEBUG {
        @Override
        boolean isEnabled(Logger logger) {
            return logger.isDebugEnabled();
        }

        @Override
        void log(String message, Logger logger) {
            logger.debug(message);
        }
    },
    INFO,
    UNKNOWN;

    private static final String POOL_CONNECT_MESSAGE_REGEX = "^(SELECT|select)\\s[1]\\s([{]executed\\sin\\s\\d{0,2}\\smsec[}])$";
    private static final Pattern POOL_CONNECT_MESSAGE_PATTERN = Pattern.compile(POOL_CONNECT_MESSAGE_REGEX);
    private static final Predicate<String> POOL_CONNECT_MESSAGE_MATCHER = message -> !POOL_CONNECT_MESSAGE_PATTERN.matcher(message).find();


    public static boolean isLevelEnabled(Category category, Logger logger) {
        Assert.notNull(category, "A category shouldn't be null!");
        return of(category).isEnabled(logger);
    }

    public static void toLog(String message, Logger logger, Category category, Predicate<Category> predicate) {
        Assert.notNull(predicate, "A predicate shouldn't be null!");
        Assert.notNull(logger, "A logger shouldn't be null!");
        if (isLog(message, category, predicate)) {
            LevelType.of(category).log(message, logger);
        }
    }

    private static LevelType of(Category category) {
        return EnumSet.allOf(LevelType.class)
                .stream()
                .filter(level -> Objects.equals(level.name().toLowerCase(), category.getName()))
                .findFirst()
                .orElse(UNKNOWN);
    }

    private static boolean isLog(String message, Category category, Predicate<Category> predicate) {
        return predicate.test(category) && !StringUtils.isEmpty(message) && notConnectionPoolMessage(message);
    }

    private static boolean notConnectionPoolMessage(String message) {
        return POOL_CONNECT_MESSAGE_MATCHER.test(message);
    }

    void log(String message, Logger logger) {
        logger.debug(message);
    }

    boolean isEnabled(Logger logger) {
        return logger.isDebugEnabled();
    }
}