package me.gking2224.projectms.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import me.gking2224.projectms.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
