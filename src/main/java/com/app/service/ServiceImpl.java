package com.app.service;

import com.app.user.Group;
import com.app.user.GroupTrade;
import com.app.user.Row;
import com.app.user.TradeDatum;
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
        //set tradeDatum data by checking a group number of ticket number of groupTrades data
        //add corresponding data to TradeDatum attributes if the group ID of group and groupTrade
        List<TradeDatum> clientData = new ArrayList<>();
        for (int j = 0; j < group.length; j++) {
            for (int k = 0; k < groupTrades.size(); k++) {
                if (Integer.parseInt(group[j].getGroup()) == groupTrades.get(k).getGroup()) {
                    //set ticket and other data
                    TradeDatum tradeDatum = new TradeDatum();
                    tradeDatum.setTicket(String.valueOf(groupTrades.get(k).getTicket()));
                    tradeDatum.setCommission(String.valueOf(groupTrades.get(k).getCommission()));
                    tradeDatum.setSwaps(String.valueOf(groupTrades.get(k).getSwaps()));
                    tradeDatum.setProfit(String.valueOf(groupTrades.get(k).getProfit()));
                    float totalPnl = groupTrades.get(k).getCommission() + groupTrades.get(k).getSwaps() + groupTrades.get(k).getProfit();
                    tradeDatum.setTotalPnL(totalPnl);
                    for (int z = 0; z < rows.size(); z++) {
                        String ticketNumOfGroup = rows.get(z).getComment();
                        ticketNumOfGroup = ticketNumOfGroup.substring(ticketNumOfGroup.lastIndexOf("|") + 1);
                        if (Integer.parseInt(ticketNumOfGroup) == groupTrades.get(k).getTicket()) {
                            tradeDatum.setComment(rows.get(z).getComment());
                        }
                    }
                    clientData.add(tradeDatum);
                    //System.out.println(tradeDatum);
                    group[j].setClientData(clientData);
                }
            }
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

    //Stream implementation (implement later)
    public List<GroupTrade> getObjectFromGroupTrade(List<GroupTrade> groupTrades, String searchterm) {
        return groupTrades.stream().filter(p -> p.getGroup() == (Integer.parseInt(searchterm))).collect(Collectors.toList());
    }
    //Stream implementation (implement later)
    public List<Row> getObjectFromRows(List<Row> rows, int searchterm) {
        return rows.stream().filter(p -> p.getComment().contains(String.valueOf(searchterm))).collect(Collectors.toList());
    }
}
