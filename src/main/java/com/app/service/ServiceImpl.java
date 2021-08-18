package com.app.service;

import com.app.user.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Koichi Sugi
 * Modified 10/06/2021
 */
public class ServiceImpl implements Service {
    private final File profitLossSummary = new File("target/Profit_loss_summary.json");

    @Override
    public Map<Integer, Float> getIndividualPnL(List<Row> rows) {

        HashMap<Integer, Float> individualPnL = new HashMap<>();
        //sum up all individual PnL
        for (Row row : rows) {
            if (!individualPnL.containsKey(row.getUserId())) {
                //if a key does not exist, newly create a pair
                individualPnL.put(row.getUserId(), row.getProfit() + row.getSwaps() + row.getCommission());
            } else {
                //if a key exists, sum up
                individualPnL.put(row.getUserId(), individualPnL.get(row.getUserId()) + row.getProfit() + row.getSwaps() + row.getCommission());
            }
        }
        System.out.println("------------Individual Profit and Loss------------");
        for (Integer i : individualPnL.keySet()) {
            System.out.println("UserId: " + i + " PnL: " + individualPnL.get(i));
        }
        return individualPnL;
    }

    @Override
    public Map<Integer, Float> getGroupPnL(List<GroupTrade> groupTrades) {
        HashMap<Integer, Float> groupPnL = new HashMap<>();

        for (GroupTrade groupTrade : groupTrades) {
            if (!groupPnL.containsKey(groupTrade.getGroup())) {
                //if a key does not exist, newly create a pair
                groupPnL.put(groupTrade.getGroup(), groupTrade.getProfit() + groupTrade.getSwaps() + groupTrade.getCommission());
            } else {
                //if a key exists, sum up
                groupPnL.put(groupTrade.getGroup(), groupPnL.get(groupTrade.getGroup()) + groupTrade.getProfit() + groupTrade.getSwaps() + groupTrade.getCommission());
            }
        }
        System.out.println("------------Group Profit and Loss------------");
        for (Integer i : groupPnL.keySet()) {
            System.out.println("GroupID: " + i + " PnL: " + groupPnL.get(i));
        }
        return groupPnL;
    }

    //#2 clientTotalPnL
    @Override
    public Map<Integer, Float> getClientTotalPnL(List<GroupTrade> groupTrades, List<Row> rows) {
        //1. create a Hashmap groupID in a comment and userID
        HashMap<Integer, Integer> GroupNumber_userIdMap = new HashMap<>();
        HashMap<Integer, Float> clientTotalPnl = new HashMap<>();
        for (Row row : rows) {
            String groupId = row.getComment();
            groupId = groupId.substring(groupId.indexOf("|") + 1);
            groupId = groupId.substring(0, groupId.indexOf("|"));
            if (!GroupNumber_userIdMap.containsKey(Integer.parseInt(groupId))) {
                //if there is no such group id in GroupNumber_userIdMap, create a pair
                GroupNumber_userIdMap.put(Integer.parseInt(groupId), row.getUserId());
                //here, update groupTotalPnl with groupid as key and the total sum of individual profits
                clientTotalPnl.put(Integer.parseInt(groupId), row.getProfit() + row.getSwaps() + row.getCommission());
            } else if (clientTotalPnl.containsKey(Integer.parseInt(groupId))) {
                //if there is such group id in GroupNumber_userIdMap
                //update groupTotalPnl
                clientTotalPnl.put(Integer.parseInt(groupId), clientTotalPnl.get(Integer.parseInt(groupId)) + row.getProfit() + row.getSwaps() + row.getCommission());
            }
        }
        System.out.println("------------Client Total Profit and Loss------------");
        for (Integer i : clientTotalPnl.keySet()) {
            System.out.println("GroupID: " + i + " PnL: " + clientTotalPnl.get(i));
        }
        return clientTotalPnl;
    }

    @Override
    public void serializeJson(Map<Integer, Float> groupPnL, Map<Integer, Float> clientTotalPnl, List<GroupTrade> groupTrades, List<Row> rows) {
        Group[] group = new Group[groupPnL.size()];
        int count = 0;
        //create objects of Group for each groupID
        System.out.println();
        System.out.println("Generating JSON file...");
        for (Integer i : groupPnL.keySet()) {
            group[count] = new Group();
            group[count].setGroup(i.toString());
            count++;
        }

        //set groupPnl
        for (Integer i : groupPnL.keySet()) {
            for (Group value : group) {
                if (i == Integer.parseInt(value.getGroup())) {
                    value.setGroupPnL(groupPnL.get(i).toString());
                }
            }
        }
        //set clientTotalPnL
        //clientTotalPnL == GroupTotalPnL
        //1. Check individual's group ID
        //2. If individual's group ID matches, sum up
        for (Integer i : clientTotalPnl.keySet()) {
            for (Group value : group) {
                // if a group ID matches group ID of an object being looped, add
                if (i == Integer.parseInt(value.getGroup())) {
                    value.setClientTotalPnL(clientTotalPnl.get(i).toString());
                }
            }
        }
        //Set clientData
        //1. Check if the mid value of the comment field in the rows matches with groupId of group Object.
        //2, if there is a match, sum up the total PnL of all the tickets with the same USER_ID
        List<Row> test;
        for (Group value : group) {
            List<ClientData> clientDataList = new ArrayList<>();
            test = getObjectFromRows(rows, Integer.parseInt(value.getGroup()));

            test.stream().forEach(e -> {
                List<TradeDatum> tradeDatumList = new ArrayList<>();
                ClientData clientData = new ClientData();
                clientData.setUserId(String.valueOf(e.getUserId()));
                //if an object for ID is created for the first time
                if (clientData.getPNl() == null) {
                    clientData.setPNl(e.getCommission() + e.getSwaps() + e.getProfit());
                }
                clientData.setPNl(clientData.getPNl() + e.getCommission() + e.getSwaps() + e.getProfit());
                //Set tradeData
                //Get how many tickets and loop
                List<Row> ticketsList = getTicketsWithSameIdFromRows(rows, Integer.parseInt(clientData.getUserId()));
                for (Row item : ticketsList) {
                    TradeDatum tradeDatum = new TradeDatum();
                    tradeDatum.setTicket(String.valueOf(item.getTicket()));
                    tradeDatum.setUserId(item.getUserId());
                    tradeDatum.setCloseTime(item.getcloseTime());
                    tradeDatum.setCommission(String.valueOf(item.getCommission()));
                    tradeDatum.setSwaps(String.valueOf(item.getSwaps()));
                    tradeDatum.setProfit(String.valueOf(item.getProfit()));
                    tradeDatum.setComment(String.valueOf(item.getComment()));
                    String groupId = item.getComment();
                    groupId = groupId.substring(groupId.indexOf("|") + 1);
                    groupId = groupId.substring(0, groupId.indexOf("|"));
                    tradeDatum.setMasterLogin(groupId);
                    tradeDatum.setTotalPnL(item.getProfit() + item.getSwaps() + item.getCommission());
                    tradeDatumList.add(tradeDatum);
                }
                clientData.setTradeData(tradeDatumList);
                clientDataList.add(clientData);

            });
            value.setClientData(clientDataList);
        }
        System.out.println(Arrays.stream(group).count());
        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT
            );
            mapper.writeValue(profitLossSummary, group);
            System.out.println("--JSON file has been generated in path below--");
            System.out.println(profitLossSummary.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get a list of objects from Row by searching a mid section of "Comment" field with UserId
    public List<Row> getObjectFromRows(List<Row> rows, int searchTerm) {
        return rows.stream().filter(p -> p.getComment().contains(String.valueOf(searchTerm))).collect(Collectors.toList());
    }

    // Get a list of objects from Row by searching "UserID" field with UserId
    public List<Row> getTicketsWithSameIdFromRows(List<Row> rows, int searchTerm) {
        return rows.stream().filter(p -> p.getUserId() == (searchTerm)).collect(Collectors.toList());
    }
}
