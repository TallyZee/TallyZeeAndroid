package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import java.util.ArrayList;
import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.Stock;

/**
 * Created by GulshanPC on 19/07/2018.
 */

public interface StockCallBack {
    void dataBaseStockList(List<Stock> list);
}
