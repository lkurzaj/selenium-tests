package org.example.alpinizm.core.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class AlpinizmLogger {

    private final Logger logger;

    private AlpinizmLogger(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    private AlpinizmLogger(String name) {
        Objects.requireNonNull(name);
        this.logger = LogManager.getLogger(name);
    }

    public static AlpinizmLogger getLogger(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return new AlpinizmLogger(clazz);
    }

    public void info(String info) {
        Objects.requireNonNull(info);
        this.logger.info(info);
    }

    public void warn(String info) {
        Objects.requireNonNull(info);
        this.logger.warn(info);
    }

    public void debug(String info) {
        Objects.requireNonNull(info);
        this.logger.debug(info);
    }

    public void logWaitForElementTime(String logMessage, Instant startInstant) {
        Objects.requireNonNull(logMessage);
        Objects.requireNonNull(startInstant);

        Duration time = Duration.between(startInstant, Instant.now());
        this.info(logMessage + " took: " + time.toString());
    }

}
