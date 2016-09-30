package me.gking2224.projectms.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("web")
@ComponentScan({"me.gking2224.projectms.web"})
public class WebAppConfiguration {

}
