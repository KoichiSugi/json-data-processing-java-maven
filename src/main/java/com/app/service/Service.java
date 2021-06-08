package com.app.service;

import com.app.user.GroupTrade;
import com.app.user.Row;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Koichi Sugi
 */
public interface Service {
    public HashMap<Integer, Float> getIndividualPnL(List<Row> rows);
    public HashMap<Integer, Float> getGroupPnL(List<GroupTrade> groupTrades);
    public HashMap<Integer, Float> getGroupTotalPnL(List<GroupTrade> groupTrades, List<Row> rows);
    public void serialzeJson(HashMap<Integer, Float> individualPnL, HashMap<Integer, Float> groupPnL, HashMap<Integer, Float> groupTotalPnL,List<GroupTrade> groupTrades,List<Row> rows );
}
