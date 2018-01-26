package net.moshr.timetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beust.jcommander.JCommander;

import lombok.extern.slf4j.Slf4j;
import net.moshr.timetracker.commands.ProjectCommand;
import net.moshr.timetracker.services.ProjectService;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private ProjectService projectService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProjectCommand projectCommand = new ProjectCommand();
		JCommander jc = JCommander.newBuilder()
			    .addCommand("project", projectCommand)
			    .build();
		jc.parse(args);
		
		String parsedCommand = jc.getParsedCommand();
		if ("project".equals(parsedCommand)) {
			log.info("Running project code");
			projectService.processProjectCommand(projectCommand);
		} else {
			log.error("Unknown command");
		}
	}
}
