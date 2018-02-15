package net.moshr.timetracker.tables;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SummaryTable {

    @Getter @Setter
    private List<SummarySection> sectionList = new ArrayList<>(0);

}
