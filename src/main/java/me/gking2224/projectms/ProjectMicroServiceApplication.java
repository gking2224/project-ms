package me.gking2224.projectms;


import javax.security.auth.message.config.AuthConfigFactory;
import javax.servlet.ServletContext;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.web.support.ServletContextApplicationContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

import me.gking2224.common.CommonConfiguration;
import me.gking2224.common.jms.CommonMessagingConfiguration;
import me.gking2224.projectms.db.DatabaseConfiguration;
import me.gking2224.projectms.db.EmbeddedDatabaseConfiguration;
import me.gking2224.projectms.security.SecurityConfiguration;
import me.gking2224.projectms.web.WebAppConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.projectms.service", "me.gking2224.projectms.model"})
@Import({
    WebAppConfiguration.class,
    DatabaseConfiguration.class,
    EmbeddedDatabaseConfiguration.class,
    CommonConfiguration.class,
    SecurityConfiguration.class,
    CommonMessagingConfiguration.class
})
public class ProjectMicroServiceApplication extends SpringBootServletInitializer{

    private ServletContext servletContext;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        ConfigurableEnvironment environment = new StandardServletEnvironment();
//        ApplicationListener<?> listeners = new ApplicationListenerImplementation(this);
        ApplicationContextInitializer<?> initializers = initializer();
        return application
                .contextClass(AnnotationConfigEmbeddedWebApplicationContext.class)
                .environment(environment)
//                .listeners(listeners)
                .initializers(initializers)
                .registerShutdownHook(true)
                .web(true)
                .logStartupInfo(true)
                .sources(ProjectMicroServiceApplication.class);
    }
    private ServletContextApplicationContextInitializer initializer() {
        ServletContextApplicationContextInitializer initializer = new ServletContextApplicationContextInitializer(servletContext);
        return initializer;
    }
    public static void main(String[] args) {
        // http://stackoverflow.com/questions/38802437/upgrading-spring-boot-from-1-3-7-to-1-4-0-causing-nullpointerexception-in-authen
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        SpringApplication.run(ProjectMicroServiceApplication.class, args);
    }
}