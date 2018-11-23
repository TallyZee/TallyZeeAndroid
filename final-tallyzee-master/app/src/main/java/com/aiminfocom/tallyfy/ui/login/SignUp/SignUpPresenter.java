package com.aiminfocom.tallyfy.ui.login.SignUp;

import android.content.Context;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.data.network.FirebaseNetwork.FirebaseConnection;
import com.aiminfocom.tallyfy.data.network.FirebaseNetwork.FirebaseConnectionInterface;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.CommonUtils;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;
import com.google.android.gms.common.internal.service.Common;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GulshanPC on 13/07/2018.
 */

public class SignUpPresenter<V extends SignUpMvp> extends BasePresenter<V>
        implements SignUPMvpPresenter<V> {


    @Inject
    public SignUpPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSignUpClick(UserProfile userProfile)
    {
       if(userProfile.getUsername().isEmpty() || userProfile.getUsername()==null)
       {
           getMvpView().onError("Field cannot be empty");
       }
       if(userProfile.getEmail().isEmpty() || userProfile.getEmail()==null)
       {
           getMvpView().onError("Field Cannot Be Empty");
       }
       if(!CommonUtils.isEmailValid(userProfile.getEmail()))
       {
           getMvpView().onError("Please provide a valid email");
       }
        if(userProfile.getPassword().isEmpty() || userProfile.getEmail()==null)
        {
            getMvpView().onError("Field Cannot Be Empty");
        }
        if(userProfile.getEmail().isEmpty() || userProfile.getEmail()==null || !userProfile.getPassword().equals(userProfile.getCnpassword()))
        {
            getMvpView().onError("Password Does not matches");
        }
        if(userProfile.getCnpassword().isEmpty() || userProfile.getCnpassword()==null)
        {
            getMvpView().onError("Field Cannot Be Empty");
        }
        if(userProfile.getPhone().isEmpty() || userProfile.getPhone()==null || userProfile.getPhone().length()<11)
        {
            getMvpView().onError("Field Cannot Be Empty");
        }

    }


    public boolean onCreateAccount(UserProfile userProfile,Context context)
    {
        if(userProfile.getEmail().isEmpty() || userProfile.getEmail()==null)
        {
            getMvpView().onError("Field cannot be empty");
            return false;

        }
        if(userProfile.getUsername().isEmpty() || userProfile.getUsername()==null)
        {
            getMvpView().onError("Field Cannot Be Empty");
            return false;


        }

          if(userProfile.getPassword().isEmpty() || userProfile.getPassword()==null)
        {
            getMvpView().onError("Field Cannot Be Empty");
            return false;


        }
          if(userProfile.getCnpassword().isEmpty() || userProfile.getCnpassword()==null)
        {
            getMvpView().onError("Field Cannot Be Empty");
            return false;


        }
           if( !userProfile.getPassword().equals(userProfile.getCnpassword()))
        {
            getMvpView().onError("Password Does not matches");
            return false;


        }

           if(userProfile.getPhone().isEmpty() || userProfile.getPhone()==null || userProfile.getPhone().length()<10)
        {
            getMvpView().onError("Field Cannot Be Empty");
            return false;


        }
        if(userProfile.getPassword().length()<6)
        {
            getMvpView().onError("Password Should Minimum 6 Digit or more");
            return false;
        }
        else {
            FirebaseConnection firebaseConnection = new FirebaseConnection();
            firebaseConnection.onCreateUser(userProfile,context);
            return true;
        }
    }
}
