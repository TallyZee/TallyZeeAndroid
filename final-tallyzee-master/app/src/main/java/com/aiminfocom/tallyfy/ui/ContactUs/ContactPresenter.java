package com.aiminfocom.tallyfy.ui.ContactUs;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.Settings.SettingMvpPresenter;
import com.aiminfocom.tallyfy.ui.Settings.SettingsMvp;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class ContactPresenter <V extends ContactMvp & SettingsMvp> extends BasePresenter<V>
        implements SettingMvpPresenter<V>
{
    public ContactPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}