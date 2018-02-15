package net.moshr.timetracker.repositories;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.entities.WorkEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkEntryRepository extends JpaRepository<WorkEntry, Long> {

    @Query(value = "select w from WorkEntry w join fetch w.project where w.end is null")
    WorkEntry findByEndIsNull();

}
