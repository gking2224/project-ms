package me.gking2224.projectms.db.dao;

import java.util.List;

import me.gking2224.projectms.model.Project;

public interface ProjectDao {

    Project createProject(Project project);

    List<Project> findAllProjects();

    Project updateProject(Project project);

    void deleteProject(Long id);

    Project findProjectById(Long id);

}
