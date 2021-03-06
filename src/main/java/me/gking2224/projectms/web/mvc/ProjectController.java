package me.gking2224.projectms.web.mvc;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.gking2224.projectms.model.Project;
import me.gking2224.projectms.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    public static final String PROJECTS = "/projects";
    public static final String PROJECT       = PROJECTS+"/{id}";

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;
	
	@Autowired  @Qualifier("longDateTimeFormat") DateTimeFormatter dateTimeFormatter;

    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects(
    ) {
        List<Project> findAllProjects = projectService.findAll();
        List<Project> b = findAllProjects.stream().map(this::enrichProject).collect(toList());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<List<Project>>(b, headers, HttpStatus.OK);
    }

    @RequestMapping(value="", method=RequestMethod.POST, consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> newProject(
            @RequestBody Project project) {

        Project b = projectService.save(project);
        b = enrichProject(b);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Project>(b, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> updateProject(
            @PathVariable("id") final Long id,
            @RequestBody final Project project) {
        Long typeId = project.getId();
        if (typeId == null) project.setId(id);
        else if (typeId != id)
            throw new IllegalArgumentException("Illegal attempt to change immutable field (id)");
        Project p = projectService.save(project);
        p = enrichProject(p);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Project>(p, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProject(
            @PathVariable("id") final Long id) {
        logger.debug(PROJECTS);

        projectService.delete(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Project> getProject(
            @PathVariable("id") final Long id) {
        Project b = projectService.findById(id);
        b = enrichProject(b);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Project>(b, HttpStatus.OK);
    }

    private Project enrichProject(Project b) {
        b.setLocation(PROJECTS + "/"+ b.getId());
        return b;
    }
}
