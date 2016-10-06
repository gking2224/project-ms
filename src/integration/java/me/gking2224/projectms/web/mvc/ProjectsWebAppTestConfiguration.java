package me.gking2224.projectms.web.mvc;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import me.gking2224.common.test.TestConfiguration;
import me.gking2224.common.test.WebAppTestConfigurer;
import me.gking2224.projectms.db.DatabaseConfiguration;
import me.gking2224.projectms.db.EmbeddedDatabaseConfiguration;
import me.gking2224.projectms.web.WebAppConfiguration;

@ComponentScan({"me.gking2224.projectms.model", "me.gking2224.projectms.service"})
@Import({WebAppConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, TestConfiguration.class})
public class ProjectsWebAppTestConfiguration extends WebAppTestConfigurer {
}
