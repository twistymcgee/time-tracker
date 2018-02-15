package net.moshr.timetracker.tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@AllArgsConstructor
public class SummaryRow {

    @Getter @Setter
    private String issueNum;

    @Getter @Setter
    private Duration duration;

}
