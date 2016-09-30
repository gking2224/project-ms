package me.gking2224.projectms.db.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.projectms.model.Project;
import me.gking2224.projectms.web.mvc.ProjectsWebAppTestConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(classes=ProjectsWebAppTestConfigurer.class)
@TestPropertySource("/test.properties")
@Transactional()
@EnableJpaRepositories
@Rollback
public class ProjectRepositoryIT {

    @Autowired
    protected ProjectRepository repository;
    
    @Test
    public void testSave() {
        String name = "projectName";
        
        Project p = new Project(name);
        Project saved = repository.save(p);
        assertNotNull(saved);
        assertEquals(name, saved.getName());
    }
    
    @Test
    @Sql
    public void testFindOne() {
        
        Project p = repository.findOne(1L);
        
        assertNotNull(p);
        assertEquals("Test Project", p.getName());
    }
}
