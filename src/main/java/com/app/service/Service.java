package com.app.service;

import com.app.user.Group;
import com.app.user.GroupTrade;
import com.app.user.Row;

import java.util.List;
import java.util.Map;

/**
 * @Author Koichi Sugi
 */
public interface Service {
    Map<Integer, Float> getIndividualPnL(List<Row> rows);

    Map<Integer, Float> getGroupPnL(List<GroupTrade> groupTrades);

    Map<Integer, Float> getClientTotalPnL(List<GroupTrade> groupTrades, List<Row> rows);

    Group[] createGroupObject (Map<Integer, Float> groupPnL, Map<Integer, Float> clientTotalPnl);

    void serializeJson(List<Row> rows, Group[] group);
}
