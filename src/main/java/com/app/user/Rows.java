package com.app.user;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rows"
})
@Generated("jsonschema2pojo")
public class Rows {

//    @JsonProperty("rows")
//  //  @JsonDeserialize(contentUsing = SkipWrapperObjectDeserializer.class)
////    @JsonWrapperObject("wrapper")
// //   public Users[] users;
//
//    private List<Users> users;
//    @JsonProperty("rows")
//    public List<Users> getUsers() {
//        return users;
//    }
//    @JsonProperty("rows")
//    public void setUser(List<Users> user) {
//        this.users = users;
//    }
    @JsonProperty("rows")
    private ArrayList<Row> rows = null;

    @JsonProperty("rows")
    public ArrayList<Row> getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Rows.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("rows");
        sb.append('=');
        sb.append(((this.rows == null)?"<null>":this.rows));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
