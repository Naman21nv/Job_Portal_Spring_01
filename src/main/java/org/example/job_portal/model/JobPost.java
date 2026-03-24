package org.example.job_portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * MODEL LAYER (Data Transfer Object / Entity)
 * 
 * THEORY:
 * This class represents the core data structure of our application: A Job Post. 
 * In MVC (Model-View-Controller) architecture, this is the "Model". It holds 
 * the raw data that gets passed around between the Controller, Service, and Repository layers.
 * 
 * WHY USE LOMBOK?
 * In standard Java, you would need to write getters, setters, constructors, 
 * toString(), and equals() methods for all these private fields, resulting in 
 * 50+ lines of boilerplate code. Lombok generates all of this automatically at compile-time.
 * 
 * ANNOTATION BREAKDOWN:
 * @Data -> A Lombok shortcut annotation that automatically generates Getters, 
 *          Setters, toString(), equals(), and hashCode() methods for all fields.
 * 
 * @NoArgsConstructor -> Generates an empty default constructor (e.g. `new JobPost()`).
 *                       Required by Spring and Jackson (JSON parsing) to instantiate 
 *                       empty objects before filling them with data from a form submission.
 * 
 * @AllArgsConstructor -> Generates a constructor that takes every single field as an argument.
 *                        Useful when we want to create a fully populated JobPost object in one line
 *                        (e.g., in our mock data inside JobRepo).
 * 
 * @Component -> Tells Spring "Hey, manage the lifecycle of this class for me". 
 *               Spring will create a bean (object instance) of this class and store it 
 *               in its Application Context. This allows us to use @Autowired to inject 
 *               it elsewhere if needed.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JobPost {
    
    // Unique identifier for each job. (e.g. 1, 2, 3...)
    private int postId;
    
    // The title of the job (e.g., "Software Engineer")
    private String postProfile;
    
    // A detailed description of what the job entails
    private String postDesc;
    
    // Minimum years of experience required
    private int reqExperience;
    
    // A list of technologies required for the job (e.g. ["Java", "Spring Boot", "SQL"])
    private List<String> postTechStack;

}
