package net.moshr.timetracker.services;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.entities.WorkEntry;
import net.moshr.timetracker.tables.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class PrinterService {

    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH'h' mm'm'");

	public String printTable(PrintableTable table) {
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.repeat("=", table.getWidth())).append(System.lineSeparator());
		sb.append(printHeaderRow(table));
		sb.append(StringUtils.repeat("-", table.getWidth())).append(System.lineSeparator());
		for (Map<String, String> dataRow : table.getData()) {
			sb.append(printDataRow(table, dataRow));
		}
		sb.append(StringUtils.repeat("=", table.getWidth())).append(System.lineSeparator());
		return sb.toString();
	}

	private String printDataRow(PrintableTable table, Map<String, String> dataRow) {
		StringBuilder sb = new StringBuilder();
		for (PrintableColumn column : table.getColumns()) {
			String data = dataRow.get(column.getTitle());
			if (data.length() > (column.getWidth() - 2)) {
				data = data.substring(0, (column.getWidth() - 2));
			}
			data = StringUtils.rightPad(data, (column.getWidth() - 1));
			data = " " + data;
			String columnOutput = String.format("|%s", data);
			sb.append(columnOutput);
		}
		sb.append("|").append(System.lineSeparator());
		return sb.toString();
	}

	private String printHeaderRow(PrintableTable table) {
		StringBuilder sb = new StringBuilder();
		for (PrintableColumn column : table.getColumns()) {
			String title = column.getTitle();
			if (column.getTitle().length() > column.getWidth()) {
				title = title.substring(0, column.getWidth());
			}
			title = StringUtils.center(title, column.getWidth());
			String columnOutput = String.format("|%s", title);
			sb.append(columnOutput);
		}
		sb.append("|").append(System.lineSeparator());
		return sb.toString();
	}

	public String printWorkEntry(WorkEntry workEntry, String message) {
        StringBuilder sb = new StringBuilder();
        if (message != null) {
            sb.append(message).append(System.lineSeparator());
        }
        sb.append("ID: ").append(workEntry.getId()).append(System.lineSeparator());
        sb.append("Project: ").append(workEntry.getProject().getProjectName()).append(System.lineSeparator());
        sb.append("Issue Num: ").append(workEntry.getIssueNum()).append(System.lineSeparator());
        sb.append("Start: ").append(workEntry.getStart()).append(System.lineSeparator());
        if (workEntry.getEnd() != null) {
            sb.append("End: ").append(workEntry.getEnd()).append(System.lineSeparator());
        }
        return sb.toString();
	}

	public String printWorkSummary(SummaryTable summaryTable) {
	    StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat("=", 80)).append(System.lineSeparator());
        for (SummarySection section : summaryTable.getSectionList()) {
            Duration totalDuration = null;
            for (SummaryRow summaryRow : section.getSummaryRows()) {
                String data = summaryRow.getIssueNum();
                if (data.length() > 72) {
                    data = data.substring(0, 72);
                }
                data = StringUtils.rightPad(data, 72);
                sb.append(data).append("| ");
                long s = summaryRow.getDuration().getSeconds();
                sb.append(String.format("%dh %02dm", s/3600, (s%3600)/60)).append(System.lineSeparator());
                if (totalDuration == null) {
                    totalDuration = summaryRow.getDuration();
                } else {
                    totalDuration = totalDuration.plus(summaryRow.getDuration());
                }
            }
            sb.append(StringUtils.repeat("-", 80)).append(System.lineSeparator());
            String data = section.getProjectName();
            if (data.length() > 72) {
                data = data.substring(0, 72);
            }
            data = StringUtils.leftPad(data, 72);
            sb.append(data).append("| ");
            long s = totalDuration.getSeconds();
            sb.append(String.format("%dh %02dm", s/3600, (s%3600)/60)).append(System.lineSeparator());
        }
        return sb.toString();
    }

}
