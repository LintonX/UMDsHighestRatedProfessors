package Services;

import Objects.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class SectionService {

    private final RestTemplate restTemplate;
    Section[] sections;
    String courseId;
    Map<String, ArrayList<Section>> bucket = new HashMap<>();

    @Autowired
    public SectionService(RestTemplateBuilder restTemplateBuilder, String courseId){
        this.restTemplate = restTemplateBuilder.build();
        this.courseId = courseId;
    }

    public Section[] getSections(){
        HttpEntity<Section[]> entity = new HttpEntity<>(sections);
        return restTemplate.exchange("https://api.umd.io/v1/courses/" + courseId + "/sections", HttpMethod.GET, entity, Section[].class).getBody();
    }

    public Set<String> getCourseProfNames(){

        Section[] sections = getSections();
        Set<String> namesSet = new HashSet<>();

        for(Section element: sections){
            namesSet.addAll(element.getInstructors());
        }

        return  namesSet;
    }

    public List<Section> getProfSection(String profName){

        List<Section> sectionList = new ArrayList<>();
        for(int i = 0; i < sections.length; i++){
            if(sections[i].getInstructors().get(0).equals(profName)){
                sectionList.add(sections[i]);
            }
        }

        return sectionList;
    }

    public Map<String, ArrayList<Section>> getProfSections(){

        Section[] sections = getSections();

        for(int i = 0; i < sections.length; i++){
            if(sections[i].getInstructors().size() > 0){
                String name = sections[i].getInstructors().get(0);
                if(bucket.containsKey(name)){
                    bucket.get(name).add(sections[i]);
                }else{
                    ArrayList<Section> arrayList = new ArrayList<>();
                    arrayList.add(sections[i]);
                    bucket.put(name, arrayList);
                }
            }
        }

        return bucket;
    }

}
