package me.gking2224.projectms.db;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile("embedded")
@ComponentScan({"me.gking2224.projectms.db"})
@Import(me.gking2224.common.db.embedded.EmbeddedDatabaseConfiguration.class)
public class EmbeddedDatabaseConfiguration {
    
}
