package me.gking2224.projectms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.jpa.AbstractDaoImpl;
import me.gking2224.projectms.jpa.Project;
import me.gking2224.projectms.jpa.ProjectRepository;

@Component
@Transactional
public class ProjectDaoImpl extends AbstractDaoImpl<Project> implements ProjectDao {

//    @Autowired
//    DateTimeFormatter dateTimeFormatter;

    private EntityManager entityManager;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManager = emf.createEntityManager();
    }
    
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
        
        Project existing = projectRepository.findOne(project.getId());
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
        
        entityManager.detach(project);
        return project;
    }
}
