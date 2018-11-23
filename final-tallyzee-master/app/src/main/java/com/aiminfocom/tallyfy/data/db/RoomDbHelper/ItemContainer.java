package com.aiminfocom.tallyfy.data.db.RoomDbHelper;



import com.aiminfocom.tallyfy.data.BeanModels.StockItem;

import java.util.List;

public interface ItemContainer {


    void getItemQuery(List<StockItem> list);

    void getGroupQuery(List<StockItem> list);

    void getCategoryQuery(List<StockItem> list);
}
