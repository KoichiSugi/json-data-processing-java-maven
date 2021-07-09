package com.app.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.List;

/**
 * @Author Koichi Sugi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "group",
        "clientTotalPnL",
        "groupPnL",
        "clientData"
})
@Generated("jsonschema2pojo")
public class Group {
    @JsonProperty("group")
    private String group;
    @JsonProperty("clientTotalPnL")
    private String clientTotalPnL;
    @JsonProperty("groupPnL")
    private String groupPnL;
    @JsonProperty("clientData")
    private List<ClientData> clientData = null;

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("clientTotalPnL")
    public String getClientTotalPnL() {
        return clientTotalPnL;
    }

    @JsonProperty("clientTotalPnL")
    public void setClientTotalPnL(String clientTotalPnL) {
        this.clientTotalPnL = clientTotalPnL;
    }

    @JsonProperty("groupPnL")
    public String getGroupPnL() {
        return groupPnL;
    }

    @JsonProperty("groupPnL")
    public void setGroupPnL(String groupPnL) {
        this.groupPnL = groupPnL;
    }

    @JsonProperty("clientData")
    public List<ClientData> getClientData() {
        return clientData;
    }

    @JsonProperty("clientData")
    public void setClientData(List<ClientData> clientData) {
        this.clientData = clientData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GroupTrade.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("group");
        sb.append('=');
        sb.append(((this.group == null) ? "<null>" : this.group));
        sb.append(',');
        sb.append("clientTotalPnL");
        sb.append('=');
        sb.append(this.clientTotalPnL);
        sb.append(',');
        sb.append("groupPnL");
        sb.append('=');
        sb.append(this.groupPnL);
        sb.append(',');
        sb.append("clientData");
        sb.append('=');
        sb.append(((this.clientData == null) ? "<null>" : this.clientData));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
