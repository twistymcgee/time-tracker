package net.moshr.timetracker.commands;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

public abstract class TimeTrackerCommand {

	protected AttributedString outputResult(String result) {
		return new AttributedString(result, AttributedStyle.BOLD.foreground(AttributedStyle.GREEN));
	}

}