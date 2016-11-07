package me.gking2224.projectms;


import org.apache.commons.cli.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import me.gking2224.common.AbstractMicroServiceApplication;
import me.gking2224.common.CommonConfiguration;
import me.gking2224.projectms.db.DatabaseConfiguration;
import me.gking2224.projectms.db.EmbeddedDatabaseConfiguration;
import me.gking2224.projectms.jms.MessagingConfiguration;
import me.gking2224.projectms.security.SecurityConfiguration;
import me.gking2224.projectms.web.WebAppConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.projectms.service", "me.gking2224.projectms.model"})
@Import({
    CommonConfiguration.class,
    DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class,
    WebAppConfiguration.class,
    SecurityConfiguration.class,
    MessagingConfiguration.class
})
public class ProjectMicroServiceApplication extends AbstractMicroServiceApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication app = new Builder(args)
                .appPrefix("projectms")
                .applicationClass(ProjectMicroServiceApplication.class)
                .build();
        app.run(args);
    }
}
