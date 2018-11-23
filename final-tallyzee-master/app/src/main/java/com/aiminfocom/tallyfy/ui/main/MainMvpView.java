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

import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;

import com.aiminfocom.tallyfy.data.BeanModels.StockItem;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.data.Model.CompanyList;
import com.aiminfocom.tallyfy.data.Model.ContraVoucher;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.ui.base.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gulshan on 23/06/18.
 */

public interface MainMvpView extends MvpView {

    void openLoginActivity();

    void showAboutFragment();



    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void updateUserProfilePic(String currentUserProfilePicUrl);

    void updateAppVersion();

    void showRateUsDialog();

    void openMyFeedActivity();

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();

    void onGetStock(List<Stock> list);

    void showBillResponse(ArrayList<BillsReceivable> response);

    void showBillReceivableResponse(BillsReceivable list);

    void getResponse(List<SalesVoucher> res);

    void getSalesVoucherResponse(ArrayList<SalesVoucher> arrayList,int count);

    void getBillPayableResponse(ArrayList<BillsPayable> arrayList,int count);

    void getBillReceivableResponse(ArrayList<BillsReceables> arrayList,int count);

//    void getContraVoucherResponse(ArrayList<ContraVoucher> arrayList);
//
//    void getReceiptVoucherResponse(ArrayList<ReceiptVoucher> arrayList);
//
//    void getPaymentVoucherResponse(ArrayList<PaymentVoucher> arrayList);

    void getPurchaseVoucherResponse(ArrayList<PurchaseVoucher> arrayList,int count);

    void getPaymentVoucherResponse(ArrayList<PaymentVoucher> arrayList,int count);

    void getCreditVoucherResponse(ArrayList<CreditNoteVoucher> arrayList,int count);

    void getDebitVoucherResponse(ArrayList<DebitNoteVoucher> arrayList,int count);

    void getSalesOrderVoucherResponse(ArrayList<SalesOrderVoucher> arrayList,int count);


    void getPurchaseOrderVoucherResponse(ArrayList<PurchaseOrderVoucher> arrayList,int count);

    void getReceiptVoucherResponse(ArrayList<ReceiptVoucher> arrayList,int count);

    void getProfitAndLossResponse(ArrayList<ProfitAndLoss> arrayList);

    void getComapnyListResponse(ArrayList<CompanyList> arrayList);

    void getUserProfileResponse(ArrayList<UserProfile> arrayList);

//
    void getStockItemResponse(ArrayList<StockItem> arrayList);
}
