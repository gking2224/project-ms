package me.gking2224.projectms.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.projectms.db.jpa.ProjectRepository;
import me.gking2224.projectms.model.Project;

@Component
@Transactional
public class ProjectDaoImpl extends AbstractDaoImpl<Project> implements ProjectDao {

    @Autowired
    protected ProjectRepository projectRepository;
    
    
    public ProjectDaoImpl() {
    }

    @Override
    public Project createProject(Project project) {
        Project saved = projectRepository.save(project);
        return saved;
    }

    @Override
    public List<Project> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    @Override
    public Project updateProject(Project project) {
        
//        Project existing = projectRepository.findOne(project.getId());
        Project saved = projectRepository.save(project);
        return saved;
    }
    
    @Override
    public void deleteProject(Long id) {
        projectRepository.delete(id);
    }

    @Override
    public Project findProjectById(Long id) {
        
        Project project = projectRepository.findOne(id);
        
        getEntityManager().detach(project);
        return project;
    }
}
