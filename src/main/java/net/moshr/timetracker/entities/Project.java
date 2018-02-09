package net.moshr.timetracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial", name = "id", nullable = false)
	private Long id;
	
	@Column(name = "project_name", nullable = false)
	@Getter @Setter
	private String projectName;

}
