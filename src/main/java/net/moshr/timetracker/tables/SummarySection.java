package net.moshr.timetracker.tables;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SummarySection {

    @Getter @Setter
    private List<SummaryRow> summaryRows = new ArrayList<>(0);

    @Getter @Setter
    private String projectName;

}
