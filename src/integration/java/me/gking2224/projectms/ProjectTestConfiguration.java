package me.gking2224.projectms;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import me.gking2224.common.test.CommonTestConfiguration;
import me.gking2224.projectms.db.DatabaseConfiguration;
import me.gking2224.projectms.db.EmbeddedDatabaseConfiguration;
import me.gking2224.projectms.db.jpa.JpaConfiguration;
import me.gking2224.projectms.web.WebAppConfiguration;

@Import({CommonTestConfiguration.class, DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class, JpaConfiguration.class, WebAppConfiguration.class})
@ComponentScan({"me.gking2224.projectms.model", "me.gking2224.projectms.service"})
public class ProjectTestConfiguration {

}
