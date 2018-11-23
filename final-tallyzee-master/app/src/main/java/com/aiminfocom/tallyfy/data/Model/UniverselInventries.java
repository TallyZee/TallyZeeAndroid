package com.aiminfocom.tallyfy.data.Model;

import java.util.List;

public class UniverselInventries {

    private String stockItemName;

    private String basicUserDesc;

    private String rate;

    private  String amount;

    private String edqty;

    private String actualQty;

    public String getStockItemName() {
        return stockItemName;
    }

    public void setStockItemName(String stockItemName) {
        this.stockItemName = stockItemName;
    }

    public String getBasicUserDesc() {
        return basicUserDesc;
    }

    public void setBasicUserDesc(String basicUserDesc) {
        this.basicUserDesc = basicUserDesc;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEdqty() {
        return edqty;
    }

    public void setEdqty(String edqty) {
        this.edqty = edqty;
    }

    public String getActualQty() {
        return actualQty;
    }

    public void setActualQty(String actualQty) {
        this.actualQty = actualQty;
    }

    public List<univBatch> getBatchAllocationList() {
        return batchAllocationList;
    }

    public void setBatchAllocationList(List<univBatch> batchAllocationList) {
        this.batchAllocationList = batchAllocationList;
    }

    public UniverselInventries(String stockItemName, String basicUserDesc, String rate, String amount, String edqty, String actualQty, List<univBatch> batchAllocationList) {

        this.stockItemName = stockItemName;
        this.basicUserDesc = basicUserDesc;
        this.rate = rate;
        this.amount = amount;
        this.edqty = edqty;
        this.actualQty = actualQty;
        this.batchAllocationList = batchAllocationList;
    }

    private List<univBatch> batchAllocationList;
}
