import net.logstash.logback.encoder.LogstashEncoder
import PatternLayoutEncoder

import java.nio.charset.Charset

statusListener(NopStatusListener)

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        Pattern = "%black(%d{ISO8601}) %green([%thread]) %highlight(%-5level) [%blue(%logger{36})]: %msg%n%throwable"
        charset = Charset.forName("UTF-8")
    }
}

appender("LOGSTASH", RollingFileAppender) {
    file = "logs/logstash.json"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "logs/logstash.%d{dd-MM-yyyy}.%i.zip"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "1GB"
        }
        maxHistory = 7
    }
    encoder(LogstashEncoder)
}

root(ch.qos.logback.classic.Level.INFO, ["CONSOLE", "LOGSTASH"])