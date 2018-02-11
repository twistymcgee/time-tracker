package net.moshr.timetracker.services;

import net.moshr.timetracker.entities.WorkEntry;
import net.moshr.timetracker.tables.PrintableColumn;
import net.moshr.timetracker.tables.PrintableTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrinterService {

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

}
