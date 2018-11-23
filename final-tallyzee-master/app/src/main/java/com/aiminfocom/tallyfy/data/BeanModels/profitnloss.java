package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GulshanPC on 17/07/2018.
 */
@Entity(tableName = "profitnloss")
public class profitnloss{
    @PrimaryKey(autoGenerate = true)
    @SerializedName("ID")
    @ColumnInfo(name = "ID")
    public int id;
    @SerializedName("DSPACCNAME")
    @ColumnInfo(name = "DSPACCNAME")
    public String DSPACCNAME;
    @SerializedName("PLAMT")
    @ColumnInfo(name = "PLAMT")
    public String PLAMT;
    @ColumnInfo(name = "BSNAME")
    @SerializedName("BSNAME")
    public String BSNAME;
    @SerializedName("BSAMT")
    @ColumnInfo(name = "BSAMT")
    public String BSAMT;
    @SerializedName("Sales_Accounts")
    @ColumnInfo(name = "Sales_Accounts")
    public String salesAccounts;
    @SerializedName("Cost_of_Sales")
    @ColumnInfo(name = "Cost_of_Sales")
    public String costOfSales;
    @SerializedName("Opening_Stock")
    @ColumnInfo(name = "Opening_Stock")
    public String openingStock;
    @SerializedName("Add_Purchase_Accounts")
    @ColumnInfo(name = "Add_Purchase_Accounts")
    public String addPurchaseAccounts;
    @ColumnInfo(name = "Less_Closing_Stock")
    @SerializedName("Less_Closing_Stock")
    public String lessClosingStock;
    @ColumnInfo(name = "Indirect_Incomes")
    @SerializedName("Indirect_Incomes")
    public String indirectIncomes;
    @ColumnInfo(name = "Indirect_Expenses")
    @SerializedName("Indirect_Expenses")
    public String indirectExpenses;


    public profitnloss(String salesAccounts, String costOfSales, String openingStock, String addPurchaseAccounts, String lessClosingStock, String indirectIncomes, String indirectExpenses) {
        this.salesAccounts = salesAccounts;
        this.addPurchaseAccounts = addPurchaseAccounts;
        this.costOfSales = costOfSales;
        this.lessClosingStock = lessClosingStock;
        this.openingStock = openingStock;
        this.indirectExpenses = indirectExpenses;
        this.indirectIncomes = indirectIncomes;
    }

    public String getSalesAccounts() {
        return salesAccounts;
    }

    public void setSalesAccounts(String salesAccounts) {
        this.salesAccounts = salesAccounts;
    }

    public String getCostOfSales() {
        return costOfSales;
    }

    public void setCostOfSales(String costOfSales) {
        this.costOfSales = costOfSales;
    }

    public String getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(String openingStock) {
        this.openingStock = openingStock;
    }

    public String getAddPurchaseAccounts() {
        return addPurchaseAccounts;
    }

    public void setAddPurchaseAccounts(String addPurchaseAccounts) {
        this.addPurchaseAccounts = addPurchaseAccounts;
    }

    public String getLessClosingStock() {
        return lessClosingStock;
    }

    public void setLessClosingStock(String lessClosingStock) {
        this.lessClosingStock = lessClosingStock;
    }

    public String getIndirectIncomes() {
        return indirectIncomes;
    }

    public void setIndirectIncomes(String indirectIncomes) {
        this.indirectIncomes = indirectIncomes;
    }

    public String getIndirectExpenses() {
        return indirectExpenses;
    }

    public void setIndirectExpenses(String indirectExpenses) {
        this.indirectExpenses = indirectExpenses;
    }


    public String getDSPACCNAME() {
        return DSPACCNAME;
    }

    public void setDSPACCNAME(String DSPACCNAME) {
        this.DSPACCNAME = DSPACCNAME;
    }

    public String getPLAMT() {
        return PLAMT;
    }

    public void setPLAMT(String PLAMT) {
        this.PLAMT = PLAMT;
    }

    public String getBSNAME() {
        return BSNAME;
    }

    public void setBSNAME(String BSNAME) {
        this.BSNAME = BSNAME;
    }

    public String getBSAMT() {
        return BSAMT;
    }

    public void setBSAMT(String BSAMT) {
        this.BSAMT = BSAMT;
    }

}
