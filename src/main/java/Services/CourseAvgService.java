package Services;

import Objects.CourseAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class CourseAvgService {

    private final RestTemplate restTemplate;
    private CourseAvg courseAvg;
    private final String courseId;

    @Autowired
    public CourseAvgService(RestTemplateBuilder restTemplateBuilder, String courseId){
        this.restTemplate = restTemplateBuilder.build();
        this.courseId = courseId;
    }

    public CourseAvg getCourseForAvg(){
        HttpEntity<CourseAvg> entity = new HttpEntity<>(courseAvg);
        return restTemplate.exchange("https://planetterp.com/api/v1/course?name=" + this.courseId, HttpMethod.GET, entity, CourseAvg.class).getBody();
    }
}
