package net.moshr.timetracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.moshr.timetracker.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value = "select p.projectName from Project p where LOWER(p.projectName) like LOWER(concat(:text,'%'))")
	List<String> findByNameStartsWith(@Param("text") String searchText);

}
