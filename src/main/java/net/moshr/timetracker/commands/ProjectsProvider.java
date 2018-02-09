package net.moshr.timetracker.commands;

import net.moshr.timetracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectsProvider extends ValueProviderSupport {

	@Autowired
	private ProjectService projectService;

	@Override
	public List<CompletionProposal> complete(MethodParameter arg0, CompletionContext arg1, String[] arg2) {
		return projectService.searchProjects(arg1.currentWord()).stream().map(CompletionProposal::new)
				.collect(Collectors.toList());
	}

}
