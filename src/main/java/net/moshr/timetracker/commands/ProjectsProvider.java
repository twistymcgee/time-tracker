package net.moshr.timetracker.commands;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import net.moshr.timetracker.services.ProjectService;

@Component
public class ProjectsProvider extends ValueProviderSupport {

	@Autowired
	private ProjectService projectService;

	private final static String[] VALUES = new String[] { "hello world", "I'm quoting \"The Daily Mail\"",
			"10 \\ 3 = 3" };

	@Override
	public List<CompletionProposal> complete(MethodParameter arg0, CompletionContext arg1, String[] arg2) {
		// System.out.println("arg0: " + arg0);
		// System.out.println("arg1: " + arg1.currentWord());
		// System.out.println("arg2: " + arg2);

		return projectService.searchProjects(arg1.currentWord()).stream().map(CompletionProposal::new)
				.collect(Collectors.toList());

		// return
		// Arrays.stream(VALUES).map(CompletionProposal::new).collect(Collectors.toList());
	}

}
