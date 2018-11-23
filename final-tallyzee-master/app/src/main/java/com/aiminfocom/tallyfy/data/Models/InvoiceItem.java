package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class InvoiceItem  implements Serializable{
    String basicOrderDate;
    public InvoiceItem(String basicOrderDate, String orderType, String basicPurchaseOrderNo) {
        super();
        this.basicOrderDate = basicOrderDate;
        this.orderType = orderType;
        this.basicPurchaseOrderNo = basicPurchaseOrderNo;
    }
    String orderType;
    String basicPurchaseOrderNo;
    public String getBasicOrderDate() {
        return basicOrderDate;
    }
    public void setBasicOrderDate(String basicOrderDate) {
        this.basicOrderDate = basicOrderDate;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getBasicPurchaseOrderNo() {
        return basicPurchaseOrderNo;
    }
    public void setBasicPurchaseOrderNo(String basicPurchaseOrderNo) {
        this.basicPurchaseOrderNo = basicPurchaseOrderNo;
    }
}
