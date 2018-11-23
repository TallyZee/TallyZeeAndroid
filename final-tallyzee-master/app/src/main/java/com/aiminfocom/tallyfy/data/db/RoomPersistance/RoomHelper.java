package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;

/**
 * Created by GulshanPC on 06/07/2018.
 */

public interface RoomHelper {
    List<BillsReceivable> getAll();
    BillsReceivable getBillsReceivable();
    BillsReceivable deleteBillsReceivable();
    BillsReceivable updateBillsReceivable();
}
