/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.aiminfocom.tallyfy.ui.main;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.ui.base.MvpPresenter;

/**
 * Created by gulshan on 23/06/18.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    void onDrawerRateUsClick();

    void onDrawerMyFeedClick();

    void getSalesList();
    String getCompanyName();
    //void setCompanyName(String companyName);

    void getStockCall(Context context);
    void setBillsReceivable(BillsReceivable billsReceivable);
    void getBillsReceivable(String bodyParam);

    void onNavMenuCreated();

    void getSalesVoucherData();
//
    void getBillsPayableData();

    void getBillReceivableData();

    void getPurhcaseVoucherData();

//    void getContraVoucherData();
//
//    void getReceiptVoucherData();
//
    void getPaymentVoucherData();

    void getCreditVoucherData();

    void getDebitVoucherData();

    void getSalesOrderVoucherData();

    void getPurchaseOrderVoucherData();

    void getReceiptNoteVoucherData();

    void getProfitAndLossData();


    void getCompanyList();

    void getUserProfileData();

    void getStockItemData();



}
