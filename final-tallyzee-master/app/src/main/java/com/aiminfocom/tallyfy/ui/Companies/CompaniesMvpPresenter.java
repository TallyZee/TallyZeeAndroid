package com.aiminfocom.tallyfy.ui.Companies;

import android.content.Context;

import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by GulshanPC on 25/06/2018.
 */
@PerActivity
public interface CompaniesMvpPresenter<V extends CompaniesMvp> extends MvpPresenter<V> {
    List<String> getCompanyList();

    List<BillsReceivable> getBillReceable(Context context);

    String getCompanyName();

    void setCompanyName(String name);
}
