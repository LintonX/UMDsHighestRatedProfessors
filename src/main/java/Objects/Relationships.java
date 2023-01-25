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
        "coreqs",
        "prereqs",
        "formerly",
        "restrictions",
        "additional_info",
        "also_offered_as",
        "credit_granted_for"
})

public class Relationships {

    @JsonProperty("coreqs")
    public Object coreqs;
    @JsonProperty("prereqs")
    public String prereqs;
    @JsonProperty("formerly")
    public Object formerly;
    @JsonProperty("restrictions")
    public Object restrictions;
    @JsonProperty("additional_info")
    public String additionalInfo;
    @JsonProperty("also_offered_as")
    public Object alsoOfferedAs;
    @JsonProperty("credit_granted_for")
    public String creditGrantedFor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}