package geteway.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Slf4j
@Setter
@Getter
@ToString
@JsonInclude(value = JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "text", "isActive"
})
public class ReasonFeedbackEventData extends BaseEventData implements Cloneable {
    private String id;
    private String text;
    private String isActive;

    @JsonCreator
    public ReasonFeedbackEventData(
            @JsonProperty("id")String id,
            @JsonProperty("text") String text,
            @JsonProperty("isActive") String isActive) {
        this.id =id;
        this.text = text;
        this.isActive = isActive;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    public static ReasonFeedbackEventData DeseriallizeFromJson(String data) {
        ReasonFeedbackEventData reasonFeedbackEventData = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);

            reasonFeedbackEventData = mapper
                    .readerFor(ReasonFeedbackEventData.class)
                    .readValue(data);
        } catch (IOException e) {

            log.error("Error : {} ", e);
        }

        return reasonFeedbackEventData;
    }
}
