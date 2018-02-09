package net.moshr.timetracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "work-entry")
public class WorkEntry {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_wrk_ent_prj"), nullable = false)
    @NotNull
    private Project project;

    @Getter @Setter
    @Column(name = "issue_num", length = 100, nullable = false)
    @NotNull
    private String issueNum;

    @Getter @Setter
    @Column(name = "start_time", nullable = false)
    @NotNull
    private LocalDateTime start;

    @Getter @Setter
    @Column(name = "end_time")
    private LocalDateTime end;

}
