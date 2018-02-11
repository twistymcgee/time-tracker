package net.moshr.timetracker.commands;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.entities.WorkEntry;
import net.moshr.timetracker.exceptions.ConcurrentActiveException;
import net.moshr.timetracker.services.PrinterService;
import net.moshr.timetracker.services.WorkEntryService;
import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;

@ShellComponent
public class WorkEntryCommands extends TimeTrackerCommand {

    @Autowired
    private WorkEntryService workEntryService;

    @Autowired
    private PrinterService printerService;

	@ShellMethod(key = "addworkentry", value = "Add a work entry")
	public AttributedString addWorkEntry(@ShellOption(valueProvider = ProjectsProvider.class) Project project,
										 String issueNum,
										 @ShellOption(defaultValue = ShellOption.NULL) LocalDateTime start,
										 @ShellOption(defaultValue = ShellOption.NULL) LocalDateTime end) {

	    // if no start was provided we assume now for shorthand
	    if (start == null) {
	        start = LocalDateTime.now();
        }
        WorkEntry workEntry = null;
        try {
            workEntry = workEntryService.addWorkEntry(project, issueNum, start, end);
            return outputResult(printerService.printWorkEntry(workEntry, "New workentry added:"));
        } catch (ConcurrentActiveException e) {
            return outputResult(printerService.printWorkEntry(e.getWorkEntry(),
                    "Unable to have more than one work entry without an end date/time. Currently active:"));
        }



	}

}
