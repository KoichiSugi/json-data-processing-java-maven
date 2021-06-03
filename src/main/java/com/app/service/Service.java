package com.app.service;

import com.app.user.GroupTrade;
import com.app.user.Row;

import java.util.List;

/**
 * @Author Koichi Sugi
 */
public interface Service {
    public void individualDataProcessing(List<Row> rows);
    public void groupDataProcessing(List<GroupTrade> groupTrades);

}
