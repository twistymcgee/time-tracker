package net.moshr.timetracker.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import lombok.Getter;
import lombok.Setter;

@Parameters(commandDescription = "Manage projects")
public class ProjectCommand {

	@Parameter(names = "list", description = "List projects")
	@Getter @Setter
	private Boolean list = false;
	
}
