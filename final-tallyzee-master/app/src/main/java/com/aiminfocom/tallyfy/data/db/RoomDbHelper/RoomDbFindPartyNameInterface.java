package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.Model.VouchrerDataUniv;

import java.util.List;

public interface RoomDbFindPartyNameInterface {
    void getvoucherListName(List<SalesVoucher> list);
    void getSalesOrdervoucherListName(List<SalesOrderVoucher> list);
    void getPaymentvoucherListName(List<PaymentVoucher> list);
    void getPurchasevoucherListName(List<PurchaseVoucher> list);
    void getPurchaseOrdervoucherListName(List<PurchaseOrderVoucher> list);
    void getReciptvoucherListName(List<ReceiptVoucher> list);
    void getdebitNotevoucherListName(List<DebitNoteVoucher> list);
    void getCreditNotevoucherListName(List<CreditNoteVoucher> list);
}
