package me.gking2224.projectms;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import me.gking2224.common.test.TestConfiguration;
import me.gking2224.projectms.db.DatabaseConfiguration;
import me.gking2224.projectms.db.EmbeddedDatabaseConfiguration;

@ComponentScan({"me.gking2224.projectms.model", "me.gking2224.projectms.service"})
@EnableJpaRepositories("me.gking2224.projectms.db.jpa")
@Import({DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, TestConfiguration.class})
public class ProjectTestConfiguration {

}
