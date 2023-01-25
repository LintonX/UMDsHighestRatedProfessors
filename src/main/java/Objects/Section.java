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
        "course",
        "section_id",
        "semester",
        "number",
        "seats",
        "meetings",
        "open_seats",
        "waitlist",
        "instructors"
})

public class Section {

    @JsonProperty("course")
    public String course;
    @JsonProperty("section_id")
    public String sectionId;
    @JsonProperty("semester")
    public Integer semester;
    @JsonProperty("number")
    public String number;
    @JsonProperty("seats")
    public String seats;
    @JsonProperty("meetings")
    public List<Meeting> meetings = null;
    @JsonProperty("open_seats")
    public String openSeats;
    @JsonProperty("waitlist")
    public String waitlist;
    @JsonProperty("instructors")
    public List<String> instructors = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCourse() {
        return course;
    }

    public String getSectionId() {
        return sectionId;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getNumber() {
        return number;
    }

    public String getSeats() {
        return seats;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public String getOpenSeats() {
        return openSeats;
    }

    public String getWaitlist() {
        return waitlist;
    }

    public List<String> getInstructors() {
        return instructors;
    }



    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for(int i = 0; i < meetings.size(); i++){
            str.append(meetings.get(i).toString());
        }

        return
                "\tSection Id: " + sectionId + "\n" +
                "\tSemester: " + semester + "\n" +
                "\tNumber: " + number + "\n" +
                "\tSeats: " + seats + "\n\n" +
                "\tMeetings: \n" + str + "\n" +
                "\tOpenSeats: " + openSeats + "\n" +
                "\tWaitlist: " + waitlist + "\n\n";
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
