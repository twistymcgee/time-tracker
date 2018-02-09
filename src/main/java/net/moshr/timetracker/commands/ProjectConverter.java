package net.moshr.timetracker.commands;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter implements Converter<String, Project> {

    @Autowired
    private ProjectService projectService;

    @Override
    public Project convert(String source) {
        return projectService.findByName(source);
    }

}
