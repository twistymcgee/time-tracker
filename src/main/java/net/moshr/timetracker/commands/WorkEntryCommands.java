package net.moshr.timetracker.commands;

import org.jline.utils.AttributedString;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class WorkEntryCommands extends TimeTrackerCommand {

	@ShellMethod(key = "addworkentry", value = "Add a work entry")
	public AttributedString addWorkEntry(@ShellOption(valueProvider = ProjectsProvider.class) String project) {
		return outputResult("test");
	}

}
