package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.Model.VouchrerDataUniv;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

public interface RoomDbCallback {


    void getBillsPayableData(List<BillsPayable> list);


    void getBillsReceivableData(List<BillsReceables> list);


    void getPurchaseVoucherData(List<PurchaseVoucher> list);

    void getSalesVoucherByPartyName(List<SalesVoucher> list);

    void getSalesVoucherData(List<SalesVoucher> list);



    void getPaymentVoucherData(List<PaymentVoucher> list);

    void getCreditVoucherData(List<CreditNoteVoucher> list);


    void getDebitVOucherData(List<DebitNoteVoucher> list);

    void getSalesOrderVoucherData(List<SalesOrderVoucher> list);

    void getPurchaseOrderVoucherData(List<PurchaseOrderVoucher> list);

    void getReceiptNoteVoucherData(List<ReceiptVoucher> list);

    void getProfitAndLossData(List<ProfitAndLoss> list);


    void getUserData(List<UserProfile> list);

    void getSalesQueryData(List<SalesVoucher> list) throws ParseException;

    void getBillsPayableQueryData(List<BillsPayable> list) throws ParseException;

    void getBillsReceivableQueryData(List<BillsReceables> list);


    void getReceiptQueryData(List<ReceiptVoucher> list);

    void getDebitNoteQueryData(List<DebitNoteVoucher> list);


    void getCreditNoteQueryData(List<CreditNoteVoucher> list);

    void getPaymentQueryData(List<PaymentVoucher> list);


    void getPurchaseQueryData(List<PurchaseVoucher> list);

    void getPurchaseOrderQueryData(List<PurchaseOrderVoucher> list);

    void getSalesOrderQueryData(List<SalesOrderVoucher> list);


    void getgetClientCompanyData(List<CompanyList> list);

    void getSalesDateBetween(List<SalesVoucher> list) throws ParseException;


}
