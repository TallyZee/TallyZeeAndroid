package com.aiminfocom.tallyfy.ui.Users;

import android.content.Context;

import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.ui.base.MvpPresenter;

/**
 * Created by GulshanPC on 25/06/2018.
 */

@PerActivity
public interface UserMvpPresenter <V extends UserMvp> extends MvpPresenter<V> {

    void getUserProfileData();


}
