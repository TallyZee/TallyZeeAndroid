package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class InvoiceOrderList implements Serializable {
    String basicOrderRate;
    String orderType;

    public String getBasicOrderRate() {
        return basicOrderRate;
    }

    public void setBasicOrderRate(String basicOrderRate) {
        this.basicOrderRate = basicOrderRate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBasicPurchaseOrderNumber() {
        return basicPurchaseOrderNumber;
    }

    public void setBasicPurchaseOrderNumber(String basicPurchaseOrderNumber) {
        this.basicPurchaseOrderNumber = basicPurchaseOrderNumber;
    }

    public InvoiceOrderList(String basicOrderRate, String orderType, String basicPurchaseOrderNumber) {

        this.basicOrderRate = basicOrderRate;
        this.orderType = orderType;
        this.basicPurchaseOrderNumber = basicPurchaseOrderNumber;
    }

    String basicPurchaseOrderNumber;
}
