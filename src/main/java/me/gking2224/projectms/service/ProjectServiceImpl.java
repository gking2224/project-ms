package me.gking2224.projectms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.projectms.dao.ProjectDao;
import me.gking2224.projectms.jpa.Project;

@Component
@Transactional(readOnly=true)
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectDao dao;

    public ProjectServiceImpl() {
    }

    @Override
    @Transactional(readOnly=false)
    public Project createProject(Project project) {
        return dao.createProject(project);
    }

    @Override
    public List<Project> findAllProjects() {
        return dao.findAllProjects();
    }

    @Override
    @Transactional(readOnly=false)
    public Project updateProject(Project project) {
        return dao.updateProject(project);
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteProject(Long id) {
        dao.deleteProject(id);
    }

    @Override
    public Project findProjectById(Long id) {
        return dao.findProjectById(id);
    }

}
