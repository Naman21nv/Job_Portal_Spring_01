package org.example.job_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ENTRY POINT OF THE APPLICATION
 * 
 * THEORY & FLOW:
 * This is the very first file that runs when you start your Spring Boot application.
 * It contains the standard Java `main` method.
 * 
 * WHY USE @SpringBootApplication?
 * 1. It is actually a combination of THREE annotations:
 *    - @Configuration: Tells Spring this class can be used to define settings/beans.
 *    - @EnableAutoConfiguration: This is the "magic" of Spring Boot. It looks at your 
 *      classpath (e.g. "Oh, you have Tomcat and Spring Web installed? I'll automatically 
 *      set up a web server for you on port 8080!").
 *    - @ComponentScan: Tells Spring to scan the current package (`org.example.job_portal`) 
 *      and all its sub-packages for any classes annotated with @Component, @Service, 
 *      @Repository, or @Controller. This is how Spring finds your JobController, 
 *      JobService, and JobRepo and creates Beans for them!
 * 
 * FLOW: 
 * Run `main()` -> SpringBoot starts up -> Auto-configures server -> Scans your 
 * packages for @Components -> Creates beans and stores them in its Application Context 
 * -> Server is ready to receive HTTP requests!
 */
@SpringBootApplication
public class JobPortalApplication {

    public static void main(String[] args) {
        
        // Starts the entire Spring Boot framework and launches the embedded server (like Tomcat).
        SpringApplication.run(JobPortalApplication.class, args);

    }

}
