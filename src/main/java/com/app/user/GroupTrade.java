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
        "GROUP",
        "COMMISSION",
        "SWAPS",
        "PROFIT"
})
@Generated("jsonschema2pojo")
public class GroupTrade {
    @JsonProperty("TICKET")
    private int ticket;
    @JsonProperty("GROUP")
    private int group;
    @JsonProperty("COMMISSION")
    private float commission;
    @JsonProperty("SWAPS")
    private float swaps;
    @JsonProperty("PROFIT")
    private float profit;

    @JsonProperty("TICKET")
    public int getTicket() {
        return ticket;
    }

    @JsonProperty("TICKET")
    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    @JsonProperty("GROUP")
    public int getGroup() {
        return group;
    }

    @JsonProperty("GROUP")
    public void setGroup(int group) {
        this.group = group;
    }

    @JsonProperty("COMMISSION")
    public float getCommission() {
        return commission;
    }

    @JsonProperty("COMMISSION")
    public void setCommission(int commission) {
        this.commission = commission;
    }

    @JsonProperty("SWAPS")
    public float getSwaps() {
        return swaps;
    }

    @JsonProperty("SWAPS")
    public void setSwaps(int swaps) {
        this.swaps = swaps;
    }

    @JsonProperty("PROFIT")
    public float getProfit() {
        return profit;
    }

    @JsonProperty("PROFIT")
    public void setProfit(int profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Row.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticket");
        sb.append('=');
        sb.append(this.ticket);
        sb.append(',');
        sb.append("group");
        sb.append('=');
        sb.append(this.group);
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
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
