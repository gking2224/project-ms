package me.gking2224.projectms.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.projectms.db.jpa.ProjectRepository;
import me.gking2224.projectms.model.Project;

@Component
@Transactional
public class ProjectDaoImpl extends AbstractDaoImpl<Project, Long> implements ProjectDao {

    @Autowired
    protected ProjectRepository projectRepository;
    
    
    public ProjectDaoImpl() {
    }


    @Override
    protected JpaRepository<Project, Long> getRepository() {
        return projectRepository;
    }
}
