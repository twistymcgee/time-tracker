package net.moshr.timetracker.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.moshr.timetracker.commands.ProjectCommand;
import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.repositories.ProjectRepository;
import net.moshr.timetracker.tables.PrintableColumn;
import net.moshr.timetracker.tables.PrintableTable;

@Service
@Slf4j
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PrinterService printerService;

	public void processProjectCommand(ProjectCommand projectCommand) {
		if (Boolean.TRUE.equals(projectCommand.getList())) {
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
			printerService.printTable(printableTable);
		}
	}
	
}
