package com.app.service;

import com.app.user.GroupTrade;
import com.app.user.Row;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Koichi Sugi
 */
public interface Service {
    HashMap<Integer, Float> getIndividualPnL(List<Row> rows);

    HashMap<Integer, Float> getGroupPnL(List<GroupTrade> groupTrades);

    HashMap<Integer, Float> getClientTotalPnL(List<GroupTrade> groupTrades, List<Row> rows);

    void serializeJson(HashMap<Integer, Float> groupPnL, HashMap<Integer, Float> clientTotalPnl, List<GroupTrade> groupTrades, List<Row> rows);

}
