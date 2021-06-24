package com.app.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

/**
 * @Author Koichi Sugi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "TICKET",
        "USER_ID",
        "CLOSE_TIME",
        "COMMISSION",
        "SWAPS",
        "PROFIT",
        "COMMENT"
})
@Generated("jsonschema2pojo")
public class Row {
    @JsonProperty("TICKET")
    private int ticket;
    @JsonProperty("USER_ID")
    private int userId;
    @JsonProperty("CLOSE_TIME")
    private String closeTime;
    @JsonProperty("COMMISSION")
    private float commission;
    @JsonProperty("SWAPS")
    private float swaps;
    @JsonProperty("PROFIT")
    private float profit;
    @JsonProperty("COMMENT")
    private String comment;

    @JsonProperty("TICKET")
    public int getTicket() {
        return ticket;
    }

    @JsonProperty("TICKET")
    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    @JsonProperty("USER_ID")
    public int getUserId() {
        return userId;
    }

    @JsonProperty("USER_ID")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonProperty("CLOSE_TIME")
    public String getcloseTime() {
        return closeTime;
    }
    @JsonProperty("CLOSE_TIME")
    public void setcloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("COMMISSION")
    public float getCommission() {
        return commission;
    }

    @JsonProperty("COMMISSION")
    public void setCommission(float commission) {
        this.commission = commission;
    }

    @JsonProperty("SWAPS")
    public float getSwaps() {
        return swaps;
    }

    @JsonProperty("SWAPS")
    public void setSwaps(float swaps) {
        this.swaps = swaps;
    }

    @JsonProperty("PROFIT")
    public float getProfit() {
        return profit;
    }

    @JsonProperty("PROFIT")
    public void setProfit(float profit) {
        this.profit = profit;
    }

    @JsonProperty("COMMENT")
    public String getComment() {
        return comment;
    }


    @JsonProperty("COMMENT")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Row.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(this.userId);
        sb.append(',');
        sb.append("commission");
        sb.append('=');
        sb.append(this.commission);
        sb.append(',');
        sb.append("swaps");
        sb.append('=');
        sb.append(this.swaps);
        sb.append(',');
        sb.append("profit");
        sb.append('=');
        sb.append(this.profit);
        sb.append(',');
        sb.append("comment");
        sb.append('=');
        sb.append(((this.comment == null) ? "<null>" : this.comment));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

