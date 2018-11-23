package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aiminfocom.tallyfy.data.BeanModels.GstRatesList;
import com.aiminfocom.tallyfy.data.BeanModels.StockItem;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.Model.AllInventoriesEntry;
import com.aiminfocom.tallyfy.data.Model.BankItem;
import com.aiminfocom.tallyfy.data.Model.BatchAllocation;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CostCenter;
import com.aiminfocom.tallyfy.data.Model.CreditNoteItem;
import com.aiminfocom.tallyfy.data.Model.CreditNoteLedger;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.Inventories;
import com.aiminfocom.tallyfy.data.Model.InvoiceOrderList;
import com.aiminfocom.tallyfy.data.Model.Item;
import com.aiminfocom.tallyfy.data.Model.Ledger;
import com.aiminfocom.tallyfy.data.Model.PaymentBank;
import com.aiminfocom.tallyfy.data.Model.PaymentBill;
import com.aiminfocom.tallyfy.data.Model.PaymentLedger;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderBatchAllocation;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderItem;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderBatchAlloction;
import com.aiminfocom.tallyfy.data.Model.SalesOrderInventorieList;
import com.aiminfocom.tallyfy.data.Model.SalesOrderItem;
import com.aiminfocom.tallyfy.data.Model.SalesOrderLedgers;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.Model.VoucherInventories;

@Database(entities = {BillsReceables.class, BillsPayable.class, PurchaseVoucher.class,Ledger.class
,VoucherInventories.class,Item.class, BankItem.class,BatchAllocation.class, SalesVoucher.class
, Inventories.class,InvoiceOrderList.class, PaymentLedger.class, PaymentBill.class
, PaymentBank.class, CostCenter.class,PaymentVoucher.class, CreditNoteVoucher.class
, CreditNoteLedger.class,CreditNoteItem.class, AllInventoriesEntry.class,DebitNoteVoucher.class
,SalesOrderVoucher.class, SalesOrderInventorieList.class, SalesOrderLedgers.class, SalesOrderBatchAlloction.class
, SalesOrderItem.class,PurchaseOrderVoucher.class,PurchaseOrderBatchAllocation.class,PurchaseOrderItem.class,ReceiptVoucher.class
, ProfitAndLoss.class, CompanyList.class, UserProfile.class, Client.class, StockItem.class, GstRatesList.class
},version = 2,exportSchema = false)
public abstract class RoomDatabaseHelper extends RoomDatabase {

   public abstract DaoHelper daoHelper();

   public abstract BillsPayableDao billsPayableDao();

   public abstract PurchaseVoucherDao purchaseVoucherDao();

   public abstract SalesVoucherDao salesVoucherDao();

   public abstract PaymentVoucherDao paymentVoucherDao();

   public abstract CreditVoucherDao creditVoucherDao();


   public abstract DebitNoteDao debitNoteDao();

   public abstract SalesOrderDao salesOrderDao();

   public abstract PurchaseOrderDao purchaseOrderDao();

   public abstract ReceiptNoteDao receiptNoteDao();

   public abstract ProfitNLossDao profitNLossDao();

   public abstract CompanyNameDao companyNameDao();

   public abstract UserProfileDao userProfileDao();

   public abstract CompanyClientDao companyClientDao();
   public abstract ClientDoa clientDoa();
//
   public abstract StockItemDao stockItemDao();
}
