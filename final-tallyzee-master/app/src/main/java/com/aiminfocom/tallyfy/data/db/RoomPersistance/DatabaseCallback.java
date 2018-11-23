package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;

/**
 * Created by alahammad on 10/4/17.
 */

public interface DatabaseCallback {

    void onUsersLoaded(User users);

    void onUserDeleted();

    void onUserAdded();

    void onDataNotAvailable();

    void onUserUpdated();

    void onListBillRecable(List<BillsPaybale> list);

    void onListBillRecableSave();

    void onProfitandLoss(List<profitnloss> list);

    void onSalesOrdersCallBack(List<SalesOrders> list);


}
