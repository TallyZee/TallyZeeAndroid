package com.aiminfocom.tallyfy.ui.Settings;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GulshanPC on 25/06/2018.
 */

public class SettingPresenter<V extends SettingsMvp> extends BasePresenter<V>
        implements SettingMvpPresenter<V> {
    @Inject
    public SettingPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
