package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Group;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class GroupFragmentPresenter<V extends GroupFragmentMvp> extends BasePresenter<V>
        implements GroupFragmentMvpPresenter<V> {
    @Inject
    public GroupFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
