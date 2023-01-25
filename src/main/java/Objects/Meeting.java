package Objects;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "days",
        "room",
        "building",
        "classtype",
        "start_time",
        "end_time"
})

public class Meeting {

    @JsonProperty("days")
    public String days;
    @JsonProperty("room")
    public String room;
    @JsonProperty("building")
    public String building;
    @JsonProperty("classtype")
    public String classtype;
    @JsonProperty("start_time")
    public String startTime;
    @JsonProperty("end_time")
    public String endTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {

        if(classtype.equals("")){
            classtype = "Lecture";
        }

        return
                "\tDays: " + days + "\n" +
                "\tRoom: " + room  + "\n" +
                "\tBuilding: " + building + "\n" +
                "\tClasstype: " + classtype + "\n" +
                "\tStart time: " + startTime + "\n" +
                "\tEnd time: " + endTime + "\n";
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