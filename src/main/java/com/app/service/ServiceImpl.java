package com.app.service;

import com.app.user.GroupTrade;
import com.app.user.Row;


import java.util.HashMap;
import java.util.List;
/**
 * @Author Koichi Sugi
 */
public class ServiceImpl implements Service {

    @Override
    public void individualDataProcessing(List<Row> rows) {

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

//test
//        HashMap<Integer, Float> test = new HashMap<Integer, Float>();
//        for (int ii = 0; ii < rows.size(); ii++) {
//            if (!test.containsKey(629756)) {
//                //if key does not exist
//                test.put(rows.get(ii).getUserId(), rows.get(ii).getProfit() + rows.get(ii).getSwaps() + rows.get(ii).getCommission());
//            } else if (test.containsKey(629756) && rows.get(ii).getUserId() == 629756) {
//                //key exists, add
//                System.out.println(test.get(629756));
//                test.put(629756, test.get(629756) + rows.get(ii).getProfit() + rows.get(ii).getSwaps() + rows.get(ii).getCommission());
//
//            }
//        }
//        System.out.println("629756  || PnL: " + test.get(629756));

    }

    @Override
    public void groupDataProcessing(List<GroupTrade> groupTrades) {
        System.out.println("Group data process");
        System.out.println(groupTrades);
    }
}
