package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;

import java.text.ParseException;
import java.util.List;

public interface DateRetrival {

    void getSalesDateBetween(List<SalesVoucher> list) throws ParseException;

    void getReceiptDateBetween(List<ReceiptVoucher> list);


    void getPaymentDateBetween(List<PaymentVoucher> list);

    void getDebitDateBetween(List<DebitNoteVoucher> list);

}
