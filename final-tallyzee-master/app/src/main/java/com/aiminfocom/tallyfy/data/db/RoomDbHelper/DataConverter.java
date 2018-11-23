package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import android.arch.persistence.room.TypeConverter;

import com.aiminfocom.tallyfy.data.BeanModels.GstRatesList;
import com.aiminfocom.tallyfy.data.Model.AllInventoriesEntry;
import com.aiminfocom.tallyfy.data.Model.BankItem;
import com.aiminfocom.tallyfy.data.Model.BatchAllocation;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.CostCenter;
import com.aiminfocom.tallyfy.data.Model.CreditNoteItem;
import com.aiminfocom.tallyfy.data.Model.CreditNoteLedger;
import com.aiminfocom.tallyfy.data.Model.Inventories;
import com.aiminfocom.tallyfy.data.Model.InvoiceOrderList;
import com.aiminfocom.tallyfy.data.Model.Item;
import com.aiminfocom.tallyfy.data.Model.Ledger;
import com.aiminfocom.tallyfy.data.Model.PaymentBank;
import com.aiminfocom.tallyfy.data.Model.PaymentBill;
import com.aiminfocom.tallyfy.data.Model.PaymentLedger;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderBatchAllocation;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderInventoriesList;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderItem;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderLedger;
import com.aiminfocom.tallyfy.data.Model.SalesOrderBatchAlloction;
import com.aiminfocom.tallyfy.data.Model.SalesOrderInventorieList;
import com.aiminfocom.tallyfy.data.Model.SalesOrderItem;
import com.aiminfocom.tallyfy.data.Model.SalesOrderLedgers;
import com.aiminfocom.tallyfy.data.Model.VoucherInventories;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromList1(List<Ledger> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ledger>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<Ledger> toList1(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ledger>>() {}.getType();
        List<Ledger> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String gstRate(List<GstRatesList> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<GstRatesList>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<GstRatesList> toGstRate(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<GstRatesList>>() {}.getType();
        List<GstRatesList> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String compnayName(List<CompanyList> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CompanyList>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<CompanyList> tocompnayName(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ledger>>() {}.getType();
        List<CompanyList> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }
    @TypeConverter
    public String fronListt(List<AllInventoriesEntry> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AllInventoriesEntry>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<AllInventoriesEntry> toListtt(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AllInventoriesEntry>>() {}.getType();
        List<AllInventoriesEntry> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromListtt(List<CreditNoteLedger> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CreditNoteLedger>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<CreditNoteLedger> toListttt(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CreditNoteLedger>>() {}.getType();
        List<CreditNoteLedger> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromList2(List<PaymentBill> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentBill>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }



    @TypeConverter
    public List<PaymentBill> toList2(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentBill>>() {}.getType();
        List<PaymentBill> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromlist4(List<PaymentLedger> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentLedger>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<PaymentLedger> tolist4(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentLedger>>() {}.getType();
        List<PaymentLedger> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromList3(List<PaymentVoucher> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentVoucher>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<PaymentVoucher> toList3(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentVoucher>>() {}.getType();
        List<PaymentVoucher> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fro(List<InvoiceOrderList> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<InvoiceOrderList>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<InvoiceOrderList> to(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<InvoiceOrderList>>() {}.getType();
        List<InvoiceOrderList> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromList(List<VoucherInventories> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<VoucherInventories>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<VoucherInventories> toList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<VoucherInventories>>() {}.getType();
        List<VoucherInventories> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String from1(List<Item> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<Item> to1(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        List<Item> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String from2(List<BankItem> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<BankItem>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<BankItem> to2(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<BankItem>>() {}.getType();
        List<BankItem> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String from3(List<BatchAllocation> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<BatchAllocation>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<BatchAllocation> to3(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<BatchAllocation>>() {}.getType();
        List<BatchAllocation> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromliost(List<Inventories> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Inventories>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<Inventories> tolist(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Inventories>>() {}.getType();
        List<Inventories> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String foooo(List<PaymentBank> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentBank>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }



    @TypeConverter
    public List<PaymentBank> tooo(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PaymentBank>>() {}.getType();
        List<PaymentBank> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String foooo1(List<CostCenter> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CostCenter>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }



    @TypeConverter
    public List<CostCenter> tooo1(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CostCenter>>() {}.getType();
        List<CostCenter> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String credit(List<CreditNoteItem> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CreditNoteItem>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<CreditNoteItem> toCredit(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CreditNoteItem>>() {}.getType();
        List<CreditNoteItem> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromsales(List<SalesOrderInventorieList> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderInventorieList>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<SalesOrderInventorieList> sales(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderInventorieList>>() {}.getType();
        List<SalesOrderInventorieList> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String order1(List<SalesOrderLedgers> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderLedgers>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<SalesOrderLedgers> order(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderLedgers>>() {}.getType();
        List<SalesOrderLedgers> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String salesOrder(List<SalesOrderBatchAlloction> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderBatchAlloction>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<SalesOrderBatchAlloction> toSalesOrder(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderBatchAlloction>>() {}.getType();
        List<SalesOrderBatchAlloction> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String klk(List<SalesOrderItem> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderItem>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<SalesOrderItem> sasa(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SalesOrderItem>>() {}.getType();
        List<SalesOrderItem> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String PurchaseOrderInventoriesList(List<PurchaseOrderInventoriesList> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderInventoriesList>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<PurchaseOrderInventoriesList> toPurchaseOrderInventoriesList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderInventoriesList>>() {}.getType();
        List<PurchaseOrderInventoriesList> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }

    @TypeConverter
    public String PurchaseOrderBatchAllocation(List<PurchaseOrderBatchAllocation> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderBatchAllocation>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<PurchaseOrderBatchAllocation> toPurchaseOrderBatchAllocation(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderBatchAllocation>>() {}.getType();
        List<PurchaseOrderBatchAllocation> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String PurchaseOrderLedger(List<PurchaseOrderLedger> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderLedger>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<PurchaseOrderLedger> toPurchaseOrderLedger(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderLedger>>() {}.getType();
        List<PurchaseOrderLedger> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


    @TypeConverter
    public String PurchaseOrderItem(List<PurchaseOrderItem> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderItem>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<PurchaseOrderItem> fPurchaseOrderItem(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PurchaseOrderItem>>() {}.getType();
        List<PurchaseOrderItem> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


}