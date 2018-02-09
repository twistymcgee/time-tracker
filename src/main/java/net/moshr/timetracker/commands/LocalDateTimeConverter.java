package net.moshr.timetracker.commands;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public LocalDateTime convert(String source) {
        if ("now".equals(source)) {
            return LocalDateTime.now();
        }
        return LocalDateTime.parse(source, formatter);
    }
}
