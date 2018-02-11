package net.moshr.timetracker.services;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.entities.WorkEntry;
import net.moshr.timetracker.exceptions.ConcurrentActiveException;
import net.moshr.timetracker.repositories.WorkEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkEntryService {

    @Autowired
    private WorkEntryRepository workEntryRepository;

    public WorkEntry addWorkEntry(Project project, String issueNum, LocalDateTime start, LocalDateTime end)
            throws ConcurrentActiveException {

        if (end == null) {
            // only allowed to have one non-completed task at a time
            WorkEntry currentActive = workEntryRepository.findByEndIsNull();
            if (currentActive != null) {
                throw new ConcurrentActiveException("Unable to have multiple active work entries", currentActive);
            }
        }

        WorkEntry workEntry = new WorkEntry();
        workEntry.setProject(project);
        workEntry.setIssueNum(issueNum);
        workEntry.setStart(start);
        workEntry.setEnd(end);
        workEntry = workEntryRepository.save(workEntry);
        return workEntry;
    }

}
