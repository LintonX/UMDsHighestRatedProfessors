package Services;

import Objects.Professor;
import Objects.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class ProfessorService {

    private final RestTemplate restTemplate;
    Set<String> profList;
    Professor prof;

    @Autowired
    public ProfessorService(RestTemplateBuilder restTemplateBuilder, Set<String> profList){
        this.restTemplate = restTemplateBuilder.build();
        this.profList = profList;
    }

    public Set<Professor> getProfessorsStats() throws IOException {

        Set<Professor> professorStats = new TreeSet<>();
        //List<Professor> professorStats = new ArrayList<>();
        HttpEntity<Professor> entity = new HttpEntity<>(prof);
        for(String profName: profList){
            try{
                professorStats.add(restTemplate.exchange("https://planetterp.com/api/v1/professor?name=" + profName, HttpMethod.GET, entity, Professor.class).getBody());
            }catch(HttpClientErrorException e){
                System.out.println("Error from ProfessorService");
            }
        }

        return  professorStats;
    }
}
