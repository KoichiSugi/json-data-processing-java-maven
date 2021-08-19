package com.app.main;

import com.app.service.Service;
import com.app.service.ServiceImpl;
import com.app.user.Group;
import com.app.user.GroupTrade;
import com.app.user.Row;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author Koichi Sugi
 */
public class Main {
    private static final Service idp = new ServiceImpl();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File clientRecords = new File("src/main/resources/ClientsRecords_v2.json");
    private static final File groupTrade = new File("src/main/resources/GroupTrade_v2.json");

    static {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @SneakyThrows(IOException.class)
    public static void main(String[] args) {
        List<Row> rows = Arrays.asList(mapper.treeToValue(mapper.readTree(clientRecords).get("rows"), Row[].class));
        List<GroupTrade> groupTrades = Arrays.asList(mapper.treeToValue(mapper.readTree(groupTrade).get("rows"), GroupTrade[].class));

        // Calculate PnLs.
        idp.getIndividualPnL(rows);
        Map<Integer, Float> groupPnL = idp.getGroupPnL(groupTrades);
        Map<Integer, Float> clientTotalPnL = idp.getClientTotalPnL(groupTrades, rows);

        Group[] group = idp.createGroupObject(groupPnL, clientTotalPnL);
        // Serialize JSON.
        idp.serializeJson(rows , group);
    }
}
