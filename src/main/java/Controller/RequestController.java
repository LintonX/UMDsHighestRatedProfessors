/*
package Controller;

import Objects.Course;
import Services.CourseService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/retrieve")
public class RequestController {

    @GetMapping("/string")
    public String displayCourses(){
        RestTemplateBuilder rtb = new RestTemplateBuilder();
        CourseService cs = new CourseService(rtb, "CMSC216");

        Course[] courses = cs.getCourses();

        return courses[0].toString();
    }
}
*/