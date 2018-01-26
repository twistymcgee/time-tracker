package net.moshr.timetracker.tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PrintableColumn {
	
	@Getter @Setter
	private String title;
	
	@Getter @Setter
	private int width;
	
	
}
