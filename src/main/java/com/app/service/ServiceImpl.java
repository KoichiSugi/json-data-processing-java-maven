package com.app.service;

import com.app.user.Group;
import com.app.user.GroupTrade;
import com.app.user.Row;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Koichi Sugi
 */
public class ServiceImpl implements Service {

    @Override
    public HashMap<Integer, Float>  getIndividualPnL(List<Row> rows) {

        HashMap<Integer, Float> individualPnL = new HashMap<Integer, Float>();
        //sum up all individual PnL
        for (int i = 0; i < rows.size(); i++) {
            if (!individualPnL.containsKey(rows.get(i).getUserId())) {
                //if a key does not exist, newly create a pair
                individualPnL.put(rows.get(i).getUserId(), rows.get(i).getProfit() + rows.get(i).getSwaps() + rows.get(i).getCommission());
            } else {
                //if a key exists, sum up
                individualPnL.put(rows.get(i).getUserId(), individualPnL.get(rows.get(i).getUserId()) + rows.get(i).getProfit() + rows.get(i).getSwaps() + rows.get(i).getCommission());
            }
        }
        System.out.println("------------Individual Profit and Loss------------");
        for (Integer i : individualPnL.keySet()) {
            System.out.println("UserId: " + i + " PnL: " + individualPnL.get(i));
        }
        return individualPnL;
    }

    @Override
    public HashMap<Integer, Float>  getGroupPnL(List<GroupTrade> groupTrades) {
        System.out.println("Group data process");
        //  System.out.println(groupTrades);

        HashMap<Integer, Float> groupPnL = new HashMap<Integer, Float>();

        for (int i = 0; i < groupTrades.size(); i++) {
            if (!groupPnL.containsKey(groupTrades.get(i).getGroup())) {
                //if a key does not exist, newly create a pair
                groupPnL.put(groupTrades.get(i).getGroup(), groupTrades.get(i).getProfit() + groupTrades.get(i).getSwaps() + groupTrades.get(i).getCommission());
            } else {
                //if a key exists, sum up
                groupPnL.put(groupTrades.get(i).getGroup(), groupPnL.get(groupTrades.get(i).getGroup()) + groupTrades.get(i).getProfit() + groupTrades.get(i).getSwaps() + groupTrades.get(i).getCommission());
            }
        }
        System.out.println("------------Group Profit and Loss------------");
        for (Integer i : groupPnL.keySet()) {
            System.out.println("GroupID: " + i + " PnL: " + groupPnL.get(i));
        }
        return groupPnL;
    }

    @Override
    public HashMap<Integer, Float>  getGroupTotalPnL(List<GroupTrade> groupTrades, List<Row> rows) {
        //loop comment extract group number
        HashMap<Integer, Integer> GroupNumber_userIdMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Float> groupTotalPnL = new HashMap<Integer, Float>();
        for (int i = 0; i < rows.size(); i++) {
            String groupId = rows.get(i).getComment();
            groupId = groupId.substring(groupId.indexOf("|") + 1);
            groupId = groupId.substring(0, groupId.indexOf("|"));
            if (!GroupNumber_userIdMap.containsKey(Integer.parseInt(groupId))) {
                //if there is no such group id in GroupNumber_userIdMap, create a pair
                GroupNumber_userIdMap.put(Integer.parseInt(groupId), rows.get(i).getUserId());
                //here, update groupTotalPnl with groupid as key and the total sum of individual profits
                groupTotalPnL.put(Integer.parseInt(groupId), rows.get(i).getProfit() + rows.get(i).getSwaps() + rows.get(i).getCommission());
            } else if (groupTotalPnL.containsKey(Integer.parseInt(groupId))) {
                //if there is such group id in GroupNumber_userIdMap
                //update groupTotalPnl
                groupTotalPnL.put(Integer.parseInt(groupId), groupTotalPnL.get(Integer.parseInt(groupId)) + rows.get(i).getProfit() + rows.get(i).getSwaps() + rows.get(i).getCommission());
            }
        }
//        System.out.println("------------Total Group Profit and Loss------------");
//        for (Integer i : groupTotalPnL.keySet()) {
//            System.out.println("GroupID: " + i + " PnL: " + groupTotalPnL.get(i));
//        }
//        System.out.println(groupTotalPnL.size());

        return groupTotalPnL;
    }


}
