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
        "COMMENT",
        "masterLogin",
        "totalPnL"
})
@Generated("jsonschema2pojo")
public class TradeDatum {
    @JsonProperty("TICKET")
    private String ticket;
    @JsonProperty("USER_ID")
    private int userId;
    @JsonProperty("CLOSE_TIME")
    private String closeTime;
    @JsonProperty("COMMISSION")
    private String commission;
    @JsonProperty("SWAPS")
    private String swaps;
    @JsonProperty("PROFIT")
    private String profit;
    @JsonProperty("COMMENT")
    private String comment;
    @JsonProperty("masterLogin")
    private String masterLogin;
    @JsonProperty("totalPnL")
    private float totalPnL;

    @JsonProperty("TICKET")
    public String getTicket() {
        return ticket;
    }

    @JsonProperty("TICKET")
    public void setTicket(String ticket) {
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
    public String getCloseTime() {
        return closeTime;
    }

    @JsonProperty("CLOSE_TIME")
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("COMMISSION")
    public String getCommission() {
        return commission;
    }

    @JsonProperty("COMMISSION")
    public void setCommission(String commission) {
        this.commission = commission;
    }

    @JsonProperty("SWAPS")
    public String getSwaps() {
        return swaps;
    }

    @JsonProperty("SWAPS")
    public void setSwaps(String swaps) {
        this.swaps = swaps;
    }

    @JsonProperty("PROFIT")
    public String getProfit() {
        return profit;
    }

    @JsonProperty("PROFIT")
    public void setProfit(String profit) {
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

    public String getMasterLogin() {
        return masterLogin;
    }

    public void setMasterLogin(String masterLogin) {
        this.masterLogin = masterLogin;
    }

    @JsonProperty("totalPnL")
    public float getTotalPnL() {
        return totalPnL;
    }

    @JsonProperty("totalPnL")
    public void setTotalPnL(float totalPnL) {
        this.totalPnL = totalPnL;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TradeDatum.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ticket");
        sb.append('=');
        sb.append(((this.ticket == null) ? "<null>" : this.ticket));
        sb.append(',');
        sb.append("commission");
        sb.append('=');
        sb.append(((this.commission == null) ? "<null>" : this.commission));
        sb.append(',');
        sb.append("swaps");
        sb.append('=');
        sb.append(((this.swaps == null) ? "<null>" : this.swaps));
        sb.append(',');
        sb.append("profit");
        sb.append('=');
        sb.append(((this.profit == null) ? "<null>" : this.profit));
        sb.append(',');
        sb.append("comment");
        sb.append('=');
        sb.append(((this.comment == null) ? "<null>" : this.comment));
        sb.append(',');
        sb.append("totalPnL");
        sb.append('=');
        sb.append(this.totalPnL);
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
