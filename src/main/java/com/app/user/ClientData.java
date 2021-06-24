package com.app.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "USER_ID",
        "PnL",
        "tradeData"
})
public class ClientData {
    @JsonProperty("USER_ID")
    private String userId;
    @JsonProperty("PnL")
    private String pNl;
    @JsonProperty("tradeData")
    private List<TradeDatum> tradeData;
}
