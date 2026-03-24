<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<%--
    VIEW LAYER (JSP - JavaServer Pages)

    THEORY & FLOW:
    This file is the "View" in the MVC (Model-View-Controller) architecture.
    It is responsible ONLY for displaying data to the user.
    Flow: Controller sends a "Model" -> This JSP file receives the Model ->
          This file dynamically generates standard HTML using the data inside the Model
          -> The final HTML is sent to the user's web browser.

    WHY USE JSP / JSTL?
    Standard HTML is static (it doesn't change). By using JSP and JSTL (JSP Standard Tag Library),
    we can write loops and if-statements directly inside our HTML!
    This allows us to dynamically display 1 job or 100 jobs using the exact same code block.

    IMPORTANT JSP SETTINGS:
    `isELIgnored="false"` -> Tells the server to evaluate Expression Language (EL),
                             which looks like this: `${something}`. This allows us to
                             extract variables sent from our Java Controller.
--%>

<%-- This imports JSTL Core tags (like <c:forEach>) so we can use them below --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Job Post List</title>

    <%-- Bootstrap CSS for quick, responsive styling --%>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>


<%-- Navigation Bar --%>
<nav class="navbar navbar-expand-lg navbar-light bg-warning">
    <div class="container">
        <a class="navbar-brand fs-1 fw-medium" href="#">Telusko Job Portal Web App</a>
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="viewalljobs">All Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="https://telusko.com/">Contact</a></li>
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <h2 class="mb-4 text-center font-weight-bold">Job Post List</h2>

    <div class="row row-cols-2">
        <%--
            THE JSTL FOR-EACH LOOP (<c:forEach>)

            This is where the magic happens!
            In the Controller, we did: `model.addAttribute("jobPosts", service.getJobs());`

            - `items="${jobPosts}"`: This grabs the List of jobs from the Controller's Model.
            - `var="jobPost"`: For each iteration of the loop, the current job in the list
                               is temporarily stored in this variable named `jobPost`.

            If there are 5 jobs in the list, this entire block of HTML inside the <c:forEach>
            will be copied and pasted 5 times automatically by the server!
        --%>
        <c:forEach var="jobPost" items="${jobPosts}">

            <div class="col mb-4">
                <div class="card border-dark bg-dark text-white">
                    <div class="card-body">

                        <%--
                            EXPRESSION LANGUAGE (EL)
                            `${jobPost.postProfile}` acts as a shortcut.
                            Behind the scenes, the server is essentially doing:
                            `jobPost.getPostProfile()` and pasting the result as HTML text.
                        --%>
                        <h5 class="card-title">${jobPost.postProfile}</h5>

                        <p class="card-text">
                            <strong>Description:</strong>
                                ${jobPost.postDesc}
                        </p>

                        <p class="card-text">
                            <strong>Experience Required:</strong>
                                ${jobPost.reqExperience} years
                        </p>

                        <p class="card-text">
                            <strong>Tech Stack:</strong>
                        <ul>
                            <%--
                                NESTED LOOP:
                                Our JobPost object has a List of Strings called `postTechStack`
                                (e.g., ["Java", "Spring Boot", "SQL"]).
                                We loop through that inner list here to print each technology
                                as a bullet point (<li>).
                            --%>
                            <c:forEach var="tech" items="${jobPost.postTechStack}">
                                <li>${tech}</li>
                            </c:forEach>
                        </ul>
                        </p>
                    </div>
                    <div class="card-footer">
                        <!-- Optional footer content -->
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>