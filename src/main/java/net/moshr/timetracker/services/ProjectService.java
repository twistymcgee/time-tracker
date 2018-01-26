package net.moshr.timetracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.moshr.timetracker.commands.ProjectCommand;
import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.repositories.ProjectRepository;

@Service
@Slf4j
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public void processProjectCommand(ProjectCommand projectCommand) {
		if (Boolean.TRUE.equals(projectCommand.getList())) {
			log.info("Listing projects");
			for (Project project : projectRepository.findAll()) {
				System.out.println(project.getProjectName());
			}
		}
	}
	
}
