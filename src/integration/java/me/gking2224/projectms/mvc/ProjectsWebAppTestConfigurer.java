package me.gking2224.projectms.mvc;


import org.springframework.context.annotation.ComponentScan;

import me.gking2224.common.utils.test.WebAppTestConfigurer;

@ComponentScan({"me.gking2224.projectms", "me.gking2224.common"})
public class ProjectsWebAppTestConfigurer extends WebAppTestConfigurer {
}
