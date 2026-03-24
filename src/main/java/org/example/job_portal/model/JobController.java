package org.example.job_portal.model;

import org.example.job_portal.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CONTROLLER LAYER (Presentation / Web Endpoints)
 * 
 * THEORY & FLOW:
 * This class handles all incoming HTTP Requests from users' browsers. 
 * It acts like a Traffic Cop. 
 * Flow: User visits a URL -> Controller receives request -> Controller asks Service for data 
 *       -> Controller packages data into a 'Model' -> Controller sends Model to a 'View' (JSP page).
 * 
 * WHY USE A CONTROLLER LAYER?
 * In MVC (Model-View-Controller) architecture, the Controller separates the URL routing 
 * and user interface (JSP pages) from the core business logic (Service/Repository).
 * 
 * ANNOTATION BREAKDOWN:
 * @Controller -> A specialized @Component. It tells Spring Boot:
 *                "Scan this class for methods mapped to URLs (like @GetMapping)."
 *                Spring creates a bean for this controller at startup.
 */
@Controller
public class JobController {

    /**
     * @Autowired -> Dependency Injection (DI)
     * Spring finds the `JobService` bean we created in our Application Context
     * and automatically injects it here. 
     * We don't have to write `JobService service = new JobService();`.
     */
    @Autowired
    private JobService service;

    /**
     * Handles requests for the home page.
     * 
     * @RequestMapping -> Maps a URL to this method. By default, it accepts any HTTP method (GET, POST).
     *                    Here, we map TWO paths: "/" and "/home" to return the same page.
     * 
     * @return "home" -> Spring Boot will look for a JSP file named "home.jsp" in our views folder.
     */
    @RequestMapping({"/","home"})
    public String home(){
        return "home";
    }

    /**
     * Handles requests to show the "Add Job" form page.
     * 
     * @GetMapping -> A shortcut for @RequestMapping(method = RequestMethod.GET).
     *                Used when a user simply types a URL in their browser or clicks a link.
     */
    @GetMapping("/addjob")
    public String addJob(){
        return "addjob";
    }

    /**
     * Handles requests to view all jobs.
     * 
     * FLOW: User visits /viewalljobs -> Controller asks Service for jobs -> 
     *       Controller puts jobs into a box named "Model" -> Sends box to viewalljobs.jsp.
     * 
     * @param model An object Spring gives us automatically. Think of it as a suitcase 
     *              that carries data from our Java code to our JSP page.
     */
    @GetMapping("/viewalljobs")
    public String viewAllJobs(Model model){
        
        // 1. Fetch data from Service Layer
        // 2. Put it in the Model suitcase with the label "jobPosts"
        // 3. The viewalljobs.jsp page will unpack this suitcase using `${jobPosts}`
        model.addAttribute("jobPosts", service.getJobs());
        
        // 4. Tell Spring to load viewalljobs.jsp
        return "viewalljobs";
    }

    /**
     * Handles the form submission from the "Add Job" page.
     * 
     * @PostMapping -> A shortcut for @RequestMapping(method = RequestMethod.POST).
     *                 Used when a user clicks 'Submit' on an HTML form. This hides data 
     *                 from the URL (unlike GET) for security and size reasons.
     * 
     * FLOW: User fills out HTML form on /addjob -> Clicks Submit -> Browser sends POST 
     *       request to "/handleForm" -> Spring automatically takes all input fields from 
     *       the HTML form and magically converts them into a populated `JobPost` object!
     * 
     * @param jobpost The object Spring automatically built from the HTML form data.
     *                (Spring matched form input 'names' to JobPost fields like 'postDesc').
     * @param model   The suitcase to pass data to our success page.
     */
    @PostMapping("handleForm" )
    public String handleForm(JobPost jobpost, Model model){
        
        // 1. Pack the newly submitted job into the Model so our success page can display it.
        model.addAttribute("jobPost", jobpost);
        
        // 2. Pass the new job down to our Service Layer to be processed and saved.
        service.addJob(jobpost);
        
        // 3. Tell Spring to load sucess.jsp (a page that says "Job Added Successfully!")
        return "sucess";
    }

}
