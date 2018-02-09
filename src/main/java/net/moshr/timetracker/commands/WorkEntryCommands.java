package net.moshr.timetracker.commands;

import net.moshr.timetracker.entities.Project;
import org.jline.utils.AttributedString;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;

@ShellComponent
public class WorkEntryCommands extends TimeTrackerCommand {

	@ShellMethod(key = "addworkentry", value = "Add a work entry")
	public AttributedString addWorkEntry(@ShellOption(valueProvider = ProjectsProvider.class) Project project,
										 LocalDateTime start) {

		return outputResult(String.format("Adding work entry for %d (%s) start %s", project.getId(),
                project.getProjectName(), start));
	}

}
