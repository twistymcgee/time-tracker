package net.moshr.timetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.moshr.timetracker.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
