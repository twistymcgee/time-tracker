package net.moshr.timetracker.commands;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.services.ProjectService;
import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ProjectCommand extends TimeTrackerCommand {

	@Autowired
	private ProjectService projectService;

	@ShellMethod(key = "listprojects", value = "List projects")
	public AttributedString listProjects() {
		return outputResult(projectService.listProjects());
	}

	@ShellMethod(key = "addproject", value = "Add a project")
	public AttributedString addProject(String projectName) {
		Project project = projectService.addProject(projectName);
		StringBuilder sb = new StringBuilder();
		sb.append("New project added:").append(System.lineSeparator());
		sb.append("ID: ").append(project.getId()).append(System.lineSeparator());
		sb.append("Name: ").append(project.getProjectName()).append(System.lineSeparator());
		return outputResult(sb.toString());
	}

}
