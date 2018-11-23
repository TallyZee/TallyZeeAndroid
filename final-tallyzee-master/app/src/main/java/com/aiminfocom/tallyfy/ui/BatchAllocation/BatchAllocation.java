package com.aiminfocom.tallyfy.ui.BatchAllocation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherInfo;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.utils.AppConstants;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BatchAllocation extends BaseActivity implements BatchAllocationMvp{
    @Inject
    BatchAllocationMvpPresenter<BatchAllocationMvp>  mPresenter;

    String actualQty;
    String amount;
    String batchName;
    String billQty;
    String gowdownName;
    String orderDueDate;
    String orderNumbe;
    String itemName;


    public static Intent getStartIntent(Context context,String itemName, String actualQty, String amount, String batchName,String billQty,String gowdownName,String orderDueDate,String orderNumber) {
        Intent intent = new Intent(context, VoucherInfo.class);
        Bundle b = new Bundle();
        b.putString(AppConstants.VALUE,itemName);
        b.putString(AppConstants.actualQty,actualQty);
        b.putString(AppConstants.batchName,batchName);
        b.putString(AppConstants.amount, amount);
        b.putString(AppConstants.billQty,billQty);
        b.putString(AppConstants.gowdownName,gowdownName);
        b.putString(AppConstants.orderDueDate, orderDueDate);
        b.putString(AppConstants.orderNumber, orderNumber);
        //  b.putParcelable(AppConstants.VOUCHER_LIST,billsPaybale);
        intent.putExtra(AppConstants.PRODUCT_VALUE, b);
        // intent.putParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST, list);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_allocation);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(BatchAllocation.this);
        actualQty=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.actualQty);
        gowdownName=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.gowdownName);
        itemName=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.VALUE);
        billQty=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.billQty);
        batchName=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.batchName);
        amount=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.amount);
        orderDueDate=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.orderDueDate);
        orderNumbe=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.orderNumber);


    }

    @Override
    protected void setUp() {

    }
}
