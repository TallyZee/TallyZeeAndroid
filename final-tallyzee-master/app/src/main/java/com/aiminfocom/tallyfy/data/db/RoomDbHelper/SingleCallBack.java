package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;

import java.util.List;

public interface SingleCallBack {

    void getvSalesVoucher(SalesVoucher list);
    void getSalesOrdervoucher(SalesOrderVoucher list);
    void getPaymentvoucher(PaymentVoucher list);
    void getPurchasevoucher(PurchaseVoucher list);
    void getPurchaseOrdervoucher(PurchaseOrderVoucher list);
    void getReciptvoucher(ReceiptVoucher list);
    void getdebitNotevoucher(DebitNoteVoucher list);
    void getCreditNotevoucher(CreditNoteVoucher list);

}
