package Objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "course_id",
        "semester",
        "name",
        "dept_id",
        "department",
        "credits",
        "description",
        "grading_method",
        "gen_ed",
        "core",
        "relationships",
        "sections"
})

public class Course {

    @JsonProperty("course_id")
    public String courseId;
    @JsonProperty("semester")
    public Integer semester;
    @JsonProperty("name")
    public String name;
    @JsonProperty("dept_id")
    public String deptId;
    @JsonProperty("department")
    public String department;
    @JsonProperty("credits")
    public String credits;
    @JsonProperty("description")
    public String description;
    @JsonProperty("grading_method")
    public List<String> gradingMethod = null;
    @JsonProperty("gen_ed")
    public List<List<String>> genEd = null;
    @JsonProperty("core")
    public List<String> core = null;
    @JsonProperty("relationships")
    public Relationships relationships;
    @JsonProperty("sections")
    public List<String> sections = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Course getCourse(){
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getName() {
        return name;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getDepartment() {
        return department;
    }

    public String getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGradingMethod() {
        return gradingMethod;
    }

    public List<List<String>> getGenEd() {
        return genEd;
    }

    public List<String> getCore() {
        return core;
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public List<String> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", semester=" + semester +
                ", name='" + name + '\'' +
                ", deptId='" + deptId + '\'' +
                ", department='" + department + '\'' +
                ", credits='" + credits + '\'' +
                ", description='" + description + '\'' +
                ", gradingMethod=" + gradingMethod +
                ", genEd=" + genEd +
                ", core=" + core +
                ", relationships=" + relationships +
                ", sections=" + sections +
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

}