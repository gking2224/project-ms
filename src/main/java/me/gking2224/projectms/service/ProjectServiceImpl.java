package me.gking2224.projectms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.dao.CrudDao;
import me.gking2224.common.service.AbstractCrudServiceImpl;
import me.gking2224.projectms.db.dao.ProjectDao;
import me.gking2224.projectms.model.Project;

@Component
@Transactional(readOnly=true)
public class ProjectServiceImpl extends AbstractCrudServiceImpl<Project, Long>implements ProjectService {


    @Autowired
    private ProjectDao dao;

    public ProjectServiceImpl() {
    }

    @Override
    protected CrudDao<Project, Long> getDao() {
        return dao;
    }

}
