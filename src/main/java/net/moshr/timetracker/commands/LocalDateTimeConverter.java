package net.moshr.timetracker.commands;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

    @Override
    public LocalDateTime convert(String source) {
        if ("now".equals(source)) {
            return LocalDateTime.now();
        }
        if (source.startsWith("t:")) {
            LocalTime time = LocalTime.parse(source.substring(2).toUpperCase(), timeFormatter);
            return LocalDateTime.now().with(time);
        }
        if (source.startsWith("y:")) {
            LocalTime time = LocalTime.parse(source.substring(2).toUpperCase(), timeFormatter);
            return LocalDateTime.now().minusDays(1).with(time);
        }
        return LocalDateTime.parse(source, formatter);
    }
}
