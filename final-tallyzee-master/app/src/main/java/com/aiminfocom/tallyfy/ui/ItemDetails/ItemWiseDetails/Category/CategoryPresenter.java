package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryPresenter <V extends CategoryMvpView> extends BasePresenter<V>
        implements CategoryMvpPresenter<V> {
    public CategoryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
