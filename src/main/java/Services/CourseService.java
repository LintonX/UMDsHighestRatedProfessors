package Services;

import Objects.Course;
import Objects.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class CourseService {

    private final RestTemplate restTemplate;
    private final String courseId;
    Course[] courses;
    Course[] allDeptCourses;
    Department[] allDepartments;

    @Autowired
    public CourseService(RestTemplateBuilder restTemplateBuilder, String courseId){
        this.restTemplate = restTemplateBuilder.build();
        this.courseId = courseId;
    }

    public Course[] getCourses(){
        HttpEntity<Course[]> entity = new HttpEntity<>(courses);
        return restTemplate.exchange("https://api.umd.io/v1/courses/" + this.courseId, HttpMethod.GET, entity, Course[].class).getBody();
    }

    public Course[] getAllDeptCourses(){
        String deptId = courseId.substring(0, 4);
        HttpEntity<Course[]> entity = new HttpEntity<>(allDeptCourses);
        return restTemplate.exchange("https://api.umd.io/v1/courses?dept_id=" + deptId, HttpMethod.GET, entity, Course[].class).getBody();
    }

    public String getCourseId() {
        return courseId;
    }

}
