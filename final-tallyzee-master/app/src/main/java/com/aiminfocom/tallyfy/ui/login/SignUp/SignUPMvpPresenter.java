package com.aiminfocom.tallyfy.ui.login.SignUp;

import android.content.Context;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.ui.base.MvpPresenter;

/**
 * Created by GulshanPC on 13/07/2018.
 */
@PerActivity
public interface SignUPMvpPresenter <V extends SignUpMvp> extends MvpPresenter<V> {


    void onSignUpClick(UserProfile userProfile);

    boolean onCreateAccount(UserProfile userProfile, Context context);
}
