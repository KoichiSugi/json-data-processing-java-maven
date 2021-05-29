package com.app.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Users {
    @JsonProperty
    private int user_id;
    @JsonProperty
    private float commission;
    @JsonProperty
    private  float swaps;
    @JsonProperty
    private float profit;
    @JsonProperty
    private String comment; //group id

}
