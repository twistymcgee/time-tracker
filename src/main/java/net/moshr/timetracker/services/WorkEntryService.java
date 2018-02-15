package net.moshr.timetracker.services;

import net.moshr.timetracker.entities.Project;
import net.moshr.timetracker.entities.WorkEntry;
import net.moshr.timetracker.exceptions.ConcurrentActiveException;
import net.moshr.timetracker.repositories.WorkEntryRepository;
import net.moshr.timetracker.tables.SummaryRow;
import net.moshr.timetracker.tables.SummarySection;
import net.moshr.timetracker.tables.SummaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;

@Service
public class WorkEntryService {

    @Autowired
    private WorkEntryRepository workEntryRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PrinterService printerService;

    @Transactional
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
        if (end != null) {
            workEntry.setEnd(end);
        }
        workEntry = workEntryRepository.save(workEntry);
        return workEntry;
    }

    public WorkEntry getActiveWorkEntry() {
        return workEntryRepository.findByEndIsNull();
    }

    @Transactional
    public WorkEntry completeWorkEntry(LocalDateTime end) {
        if (end == null) {
            end = LocalDateTime.now();
        }
        WorkEntry workEntry = getActiveWorkEntry();
        if (workEntry == null) {
            return null;
        }
        workEntry.setEnd(end);
        workEntryRepository.save(workEntry);
        return workEntry;
    }

    public String summarizeDay(LocalDate day) {
        SummaryTable summaryTable = new SummaryTable();
        for (Project project : projectService.getProjects()) {
            SummarySection section = new SummarySection();
            section.setProjectName(project.getProjectName());
            // TODO: use real data
            section.getSummaryRows().add(new SummaryRow("TEST1-123", Duration.ofHours(3L)));
            section.getSummaryRows().add(new SummaryRow("TEST1-45", Duration.ofMinutes(15L)));
            summaryTable.getSectionList().add(section);
        }
        return printerService.printWorkSummary(summaryTable);
    }
}
