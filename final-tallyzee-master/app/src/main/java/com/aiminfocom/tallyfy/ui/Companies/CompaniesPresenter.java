package com.aiminfocom.tallyfy.ui.Companies;

import android.content.Context;

import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GulshanPC on 25/06/2018.
 */

public class CompaniesPresenter <V extends CompaniesMvp> extends BasePresenter<V>
        implements CompaniesMvpPresenter<V> {
    DataManager dataManager;

    @Inject
    public CompaniesPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        this.dataManager=dataManager;
    }

    @Override
    public List<String> getCompanyList() {
        return new ArrayList<String>();
//        return dataManager.getCompanyList();
    }

    @Override
    public List<BillsReceivable> getBillReceable(Context context) {
        //return dataManager.getBillsReceivable(context);
        return new ArrayList<BillsReceivable>();
    }

    @Override
    public String getCompanyName() {
        return getDataManager().getCompanyName();
    }

    @Override
    public void setCompanyName(String name) {
        getDataManager().setCompanyName(name);
    }
}
