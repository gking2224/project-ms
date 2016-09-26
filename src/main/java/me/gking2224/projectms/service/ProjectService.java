package me.gking2224.projectms.service;

import java.util.List;

import me.gking2224.projectms.jpa.Project;

public interface ProjectService {

    Project createProject(Project project);

    List<Project> findAllProjects();

    Project updateProject(Project project);

    void deleteProject(Long id);

    Project findProjectById(Long id);

}
