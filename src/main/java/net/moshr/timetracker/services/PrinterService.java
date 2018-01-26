package net.moshr.timetracker.services;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.moshr.timetracker.tables.PrintableColumn;
import net.moshr.timetracker.tables.PrintableTable;

@Service
public class PrinterService {

	public void printTable(PrintableTable table) {
		
		System.out.println(StringUtils.repeat("=", table.getWidth()));
		printHeaderRow(table);
		System.out.println(StringUtils.repeat("-", table.getWidth()));
		for (Map<String, String> dataRow : table.getData()) {
			printDataRow(table, dataRow);
		}
		System.out.println(StringUtils.repeat("=", table.getWidth()));
		
	}

	private void printDataRow(PrintableTable table, Map<String, String> dataRow) {
		for (PrintableColumn column : table.getColumns()) {
			String data = dataRow.get(column.getTitle());
			if (data.length() > (column.getWidth() - 2)) {
				data = data.substring(0, (column.getWidth() - 2));
			}
			data = StringUtils.rightPad(data, (column.getWidth() - 1));
			data = " " + data;
			String columnOutput = String.format("|%s", data);
			System.out.print(columnOutput);
		}
		System.out.println("|");
	}

	private void printHeaderRow(PrintableTable table) {
		for (PrintableColumn column : table.getColumns()) {
			String title = column.getTitle();
			if (column.getTitle().length() > column.getWidth()) {
				title = title.substring(0, column.getWidth());
			}
			title = StringUtils.center(title, column.getWidth());
			String columnOutput = String.format("|%s", title);
			System.out.print(columnOutput);
		}
		System.out.println("|");
	}
	
}
