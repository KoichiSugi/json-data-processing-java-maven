package com.app.main;

import com.app.service.ServiceImpl;
import com.app.user.GroupTrade;
import com.app.user.Row;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Koichi Sugi
 */
class Main {

    public static final String ClientsRecordsPath = "C:\\Users\\61432\\Desktop\\second_assessment\\JsonDataProcessing\\src\\main\\resources\\ClientsRecords.json";
    public static final String GroupTradePath = "C:\\Users\\61432\\Desktop\\second_assessment\\JsonDataProcessing\\src\\main\\resources\\GroupTrade.json";

    public static void main(String[] args) {
        System.out.println("main here");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            List<Row> rows = Arrays.asList(mapper.treeToValue(mapper.readTree(new File(ClientsRecordsPath)).get("rows"), Row[].class));

            ServiceImpl idp = new ServiceImpl();
            //idp.individualDataProcessing(rows);

            List<GroupTrade> groupTrades = Arrays.asList(mapper.treeToValue(mapper.readTree(new File(GroupTradePath)).get("groupTrades"), GroupTrade[].class));
            idp.groupDataProcessing(groupTrades);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
