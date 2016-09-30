package me.gking2224.projectms.web.controller;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static me.gking2224.common.utils.JsonMvcTestHelper.doGet;
import static me.gking2224.common.utils.JsonMvcTestHelper.doPost;
import static me.gking2224.common.utils.JsonMvcTestHelper.doPut;
import static me.gking2224.common.utils.JsonMvcTestHelper.responseContent;
import static me.gking2224.projectms.web.mvc.ProjectController.PROJECTS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import me.gking2224.common.utils.JsonMvcTestHelper;
import me.gking2224.common.utils.JsonUtil;
import me.gking2224.projectms.model.Project;
import me.gking2224.projectms.web.mvc.ProjectsWebAppTestConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(classes=ProjectsWebAppTestConfigurer.class)
@Transactional
@Rollback
@Sql
public class ProjectControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    private JsonUtil json;
    
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        json = new JsonUtil();
    }

    @Test
    public void testGetAllBudgets() throws Exception {
        doGet(this.mockMvc, PROJECTS, JsonMvcTestHelper::expectOK)
            .andDo(JsonMvcTestHelper::responseContent)
            .andExpect(jsonPath("$.length()").value(greaterThan(0)))
            .andDo((m)->assertEquals("/projects/1", json.getFilterValue(responseContent(m), "$.[?(@._id == '1')].location")));
    }

    @Test
    public void testNewBudget() throws Exception {
        String name = "Project";
        Project b = new Project(name);
        ResultActions result = doPost(this.mockMvc, b, PROJECTS, JsonMvcTestHelper::expectOK);
        result
            .andExpect(jsonPath("$._id").isNotEmpty())
            .andExpect(jsonPath("$.name").value(hasToString(valueOf(name))))
            .andExpect(jsonPath("$.location").isNotEmpty());
    }

    @Test
    public void testGetSingleProject() throws Exception {
        String name = "Test Project";
        long id = 1L;
        String address = format("%s/%s", PROJECTS, id);
        ResultActions result = doGet(this.mockMvc, address, JsonMvcTestHelper::expectOK);
        result
            .andExpect(jsonPath("$._id").value(hasToString(valueOf(id))))
            .andExpect(jsonPath("$.name").value(equalTo(name)))
            .andExpect(jsonPath("$.location").value(equalTo(address)));
    }

    @Test
    public void testUpdateProject() throws Exception {
        String newName = "Project.x";
        long id = 1L;
        Project p = new Project(newName);
        
        doPut(this.mockMvc, p, format("%s/%s", PROJECTS, id), JsonMvcTestHelper::expectOK)
            .andExpect(jsonPath("$._id").value(hasToString(valueOf(id))))
            .andExpect(jsonPath("$.name").value(equalTo(newName)))
            .andExpect(jsonPath("$.location").isNotEmpty());
    }
}