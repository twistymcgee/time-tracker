package net.moshr.timetracker.services;

import lombok.extern.slf4j.Slf4j;
import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.repositories.ProjectRepository;
import net.moshr.timetracker.tables.PrintableColumn;
import net.moshr.timetracker.tables.PrintableTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private PrinterService printerService;

	public String listProjects() {
		log.info("Listing projects");
		PrintableColumn projectIdColumn = new PrintableColumn("Project ID", 20);
		PrintableColumn projectNameColumn = new PrintableColumn("Project Name", 57);
		PrintableTable printableTable = new PrintableTable();
		printableTable.addColumn(projectIdColumn);
		printableTable.addColumn(projectNameColumn);
		for (Project project : projectRepository.findAll()) {
			Map<String, String> dataRow = new HashMap<>(0);
			dataRow.put("Project ID", project.getId().toString());
			dataRow.put("Project Name", project.getProjectName());
			printableTable.getData().add(dataRow);
		}
		return printerService.printTable(printableTable);
	}

	@Transactional
	public Project addProject(String name) {
		Project project = new Project();
		project.setProjectName(name);
		project = projectRepository.save(project);
		return project;
	}

	public List<String> searchProjects(String searchText) {
		List<String> results = projectRepository.findByNameStartsWith(searchText);
		log.info("Results size: {}", results.size());
		return results;
	}

	public Project findByName(String name) {
		return projectRepository.findByProjectName(name);
	}

	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

}
