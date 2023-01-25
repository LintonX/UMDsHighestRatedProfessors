package Services;

import Objects.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class DepartmentService {

    private final RestTemplate restTemplate;
    private Department[] departments;

    @Autowired
    public DepartmentService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public Department[] getAllDepartments(){
        HttpEntity<Department[]> entity = new HttpEntity<>(departments);
        return restTemplate.exchange("https://api.umd.io/v1/courses/departments", HttpMethod.GET, entity, Department[].class).getBody();
    }
}
