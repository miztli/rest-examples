package com.rest.examples.restexamples.run;

import com.rest.examples.restexamples.spring.config.ControllerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Created by miztli on 20/06/17.
 */
@SpringBootApplication()
public class Application {


    public static void main( String[] args )
    {
        System.out.println( "---Booting application---" );

        // configuration classes
        final Object[] sources = {
                                        ControllerConfig.class
                                 };

        new SpringApplicationBuilder(Application.class)
                .sources(sources)
                .run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Beans provided by the Application or Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
