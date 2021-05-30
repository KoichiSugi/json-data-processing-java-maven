package com.app.user;

import com.fasterxml.jackson.annotation.*;
import com.sun.rowset.internal.Row;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rows"
})
public class Users {
//    @JsonProperty
//    private int user_id;
//    @JsonProperty
//    private float commission;
//    @JsonProperty
//    private  float swaps;
//    @JsonProperty
//    private float profit;
//    @JsonProperty
//    private String comment; //group id
@JsonProperty("rows")
@Generated("jsonschema2pojo")
private List<Row> rows = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("rows")
    public List<Row> getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(List<Row> rows) {
        this.rows = rows;
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
