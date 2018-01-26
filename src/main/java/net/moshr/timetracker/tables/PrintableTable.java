package net.moshr.timetracker.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class PrintableTable {

	@Getter
	private List<PrintableColumn> columns = new ArrayList<>(0);
	
	@Getter @Setter
	private List<Map<String, String>> data = new ArrayList<>(0);
	
	@Getter
	private int width = 0;

	public void addColumn(PrintableColumn column) {
		columns.add(column);
		// table width needs to accomodate separators (1 more than total columns)
		if (columns.size() == 1) {
			// this is the first column, add an extra size block for the extra divider
			width++;
		}
		width += column.getWidth();
		width++;
	}
	
}
