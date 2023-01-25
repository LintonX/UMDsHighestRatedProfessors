package Objects;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "slug",
        "type",
        "courses",
        "average_rating"
})

public class Professor implements Comparable<Professor> {

    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("type")
    private String type;
    @JsonProperty("courses")
    private List<String> courses = null;
    @JsonProperty("average_rating")
    private Float averageRating;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getType() {
        return type;
    }

    public List<String> getCourses() {
        return courses;
    }

    public Float getAverageRating() {
        if(this.averageRating == null){
            return (float) 0;
        }else{
            return averageRating;
        }
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", type='" + type + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int compareTo(Professor o) {
        if(this.getAverageRating() < o.getAverageRating()){
            return 1;
        }else if(this.getAverageRating().equals(o.getAverageRating())){
            return 0;
        }else{
            return -1;
        }
    }
}
