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
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Koichi Sugi
 */
class Main {
    public static final File ClientsRecords = new File("src/main/resources/ClientsRecords.json");
    public static final File GroupTrade = new File("src/main/resources/GroupTrade.json");

    public static void main(String[] args) {
        String ClientsRecordPath = ClientsRecords.getAbsolutePath();
        String GroupTradePath = GroupTrade.getAbsolutePath();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            List<Row> rows = Arrays.asList(mapper.treeToValue(mapper.readTree(new File(ClientsRecordPath)).get("rows"), Row[].class));

            ServiceImpl idp = new ServiceImpl();
            //idp.individualDataProcessing(rows);

            List<GroupTrade> groupTrades = Arrays.asList(mapper.treeToValue(mapper.readTree(new File(GroupTradePath)).get("rows"), GroupTrade[].class));
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
