package com.app.service;

import com.app.user.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Koichi Sugi
 * Modified 10/06/2021
 */
public class ServiceImpl implements Service {
    @Override
    public HashMap<Integer, Float> getIndividualPnL(List<Row> rows) {

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
    public HashMap<Integer, Float> getGroupPnL(List<GroupTrade> groupTrades) {
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

    //#2 clientTotalPnL
    @Override
    public HashMap<Integer, Float> getClientTotalPnL(List<GroupTrade> groupTrades, List<Row> rows) {
        //1. create a Hashmap groupID in a comment and userID
        HashMap<Integer, Integer> GroupNumber_userIdMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Float> clientTotalPnl = new HashMap<Integer, Float>();
        for (int i = 0; i < rows.size(); i++) {
            String groupId = rows.get(i).getComment();
            groupId = groupId.substring(groupId.indexOf("|") + 1);
            groupId = groupId.substring(0, groupId.indexOf("|"));
            if (!GroupNumber_userIdMap.containsKey(Integer.parseInt(groupId))) {
                //if there is no such group id in GroupNumber_userIdMap, create a pair
                GroupNumber_userIdMap.put(Integer.parseInt(groupId), rows.get(i).getUserId());
                //here, update groupTotalPnl with groupid as key and the total sum of individual profits
                clientTotalPnl.put(Integer.parseInt(groupId), rows.get(i).getProfit() + rows.get(i).getSwaps() + rows.get(i).getCommission());
            } else if (clientTotalPnl.containsKey(Integer.parseInt(groupId))) {
                //if there is such group id in GroupNumber_userIdMap
                //update groupTotalPnl
                clientTotalPnl.put(Integer.parseInt(groupId), clientTotalPnl.get(Integer.parseInt(groupId)) + rows.get(i).getProfit() + rows.get(i).getSwaps() + rows.get(i).getCommission());
            }
        }
        System.out.println("------------Client Total Profit and Loss------------");
        for (Integer i : clientTotalPnl.keySet()) {
            System.out.println("GroupID: " + i + " PnL: " + clientTotalPnl.get(i));
        }
        return clientTotalPnl;
    }

    @Override
    public void serializeJson(HashMap<Integer, Float> groupPnL, HashMap<Integer, Float> clientTotalPnl, List<GroupTrade> groupTrades, List<Row> rows) {
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
            for (int j = 0; j < group.length; j++) {
                if (i == Integer.parseInt(group[j].getGroup())) {
                    group[j].setGroupPnL(groupPnL.get(i).toString());
                }
            }
        }
        //set clientTotalPnL
        //clientTotalPnL == GroupTotalPnL
        //1. Check individual's group ID
        //2. If individual's group ID matches, sum up
        for (Integer i : clientTotalPnl.keySet()) {
            for (int j = 0; j < group.length; j++) {
                // if a group ID matches group ID of an object being looped, add
                if (i == Integer.parseInt(group[j].getGroup())) {
                    group[j].setClientTotalPnL(clientTotalPnl.get(i).toString());
                }
            }
        }
        //Set clientData
        //1. Check if the mid value of the comment field in the rows matches with groupId of group Object.
        //2, if there is a match, sum up the total PnL of all the tickets with the same USER_ID
        List<Row> test = null;
        int sizy = 0;
        for (int i = 0; i < group.length; i++) {
            //Create a list of tickets data with the same groupId in the comment filed from Rows
            List<ClientData> clientDataList = new ArrayList<>();
            test = getObjectFromRows(rows, Integer.parseInt(group[i].getGroup()));
            for (int j = 0; j < test.size(); j++) {
                List<TradeDatum> tradeDatumList = new ArrayList<>();
                ClientData clientData = new ClientData();
                clientData.setUserId(String.valueOf(test.get(j).getUserId()));
                //if an obeject for ID is created for the first time
                if (clientData.getPNl() == null) {
                    clientData.setPNl(test.get(j).getCommission() + test.get(j).getSwaps() + test.get(j).getProfit());
                }
                clientData.setPNl(clientData.getPNl() + test.get(j).getCommission() + test.get(j).getSwaps() + test.get(j).getProfit());
                //Set tradeData
                //Get how many tickets and loop
                List<Row> ticketsList = getTicketsWithSameIdFromRows(rows, Integer.parseInt(clientData.getUserId()));
                for (int k = 0; k < ticketsList.size(); k++) {
                    TradeDatum tradeDatum = new TradeDatum();
                    tradeDatum.setTicket(String.valueOf(ticketsList.get(k).getTicket()));
                    tradeDatum.setUserId(ticketsList.get(k).getUserId());
                    tradeDatum.setCloseTime(ticketsList.get(k).getcloseTime());
                    tradeDatum.setCommission(String.valueOf(ticketsList.get(k).getCommission()));
                    tradeDatum.setSwaps(String.valueOf(ticketsList.get(k).getSwaps()));
                    tradeDatum.setProfit(String.valueOf(ticketsList.get(k).getProfit()));
                    tradeDatum.setComment(String.valueOf(ticketsList.get(k).getComment()));
                    String groupId = ticketsList.get(k).getComment();
                    groupId = groupId.substring(groupId.indexOf("|") + 1);
                    groupId = groupId.substring(0, groupId.indexOf("|"));
                    tradeDatum.setMasterLogin(groupId);
                    tradeDatum.setTotalPnL(ticketsList.get(k).getProfit() + ticketsList.get(k).getSwaps() + ticketsList.get(k).getCommission());
                    tradeDatumList.add(tradeDatum);
                }
                clientData.setTradeData(tradeDatumList);
                clientDataList.add(clientData);
            }
            group[i].setClientData(clientDataList);
        }

        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT
            );
            mapper.writeValue(new File("src/main/Profit_loss_summary.json"), group);
            System.out.println("");
            System.out.println("--JSON file has been generated in the resources folder--");
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Get a list of objects from Row by searching a mid section of "Comment" field with UserId
    public List<Row> getObjectFromRows(List<Row> rows, int searchterm) {
        return rows.stream().filter(p -> p.getComment().contains(String.valueOf(searchterm))).collect(Collectors.toList());
    }

    // Get a list of objects from Row by searching "UserID" field with UserId
    public List<Row> getTicketsWithSameIdFromRows(List<Row> rows, int searchterm) {
        return rows.stream().filter(p -> p.getUserId() == (searchterm)).collect(Collectors.toList());
    }
}
