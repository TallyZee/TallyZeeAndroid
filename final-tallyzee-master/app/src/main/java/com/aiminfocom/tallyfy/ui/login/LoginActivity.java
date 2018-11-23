package com.aiminfocom.tallyfy.ui.login;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.Model.BillsPayable;
import com.aiminfocom.tallyfy.data.network.FirebaseNetwork.onCalledInterface;
import com.aiminfocom.tallyfy.data.network.InternetStatus.InternetConnection;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceActivity;
import com.aiminfocom.tallyfy.ui.login.ForgetPassword.ForgetPasswordActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mindorks.placeholderview.annotations.Click;

import java.util.List;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.DatabaseCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.login.SignUp.SignUp;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by gulshanPC on 27/01/17.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView, DatabaseCallback,onCalledInterface {
    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;
    @BindView(R.id.ll_sign_up)
    TextView signUp;


    private ProgressDialog progressDialog;

    @BindView(R.id.forget)
    TextView forgetPassword;
    private FirebaseAuth mAuth;
    private static final String CHANNEL = "Personal_notifications";
    private static final int  channel_id = 001;

    private FirebaseAuth.AuthStateListener mAuthListener;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = MainActivity.getStartIntent(LoginActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        };
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LoginActivity.this);
        progressDialog = new ProgressDialog(LoginActivity.this);
    }


    @OnClick(R.id.ll_sign_up)
    public void onSignClick(View view) {
        System.out.println("clicked");
        Intent intent = SignUp.getStartIntent(LoginActivity.this);
        startActivity(intent);
    }



    @OnClick(R.id.forget)
    void onForgetPassword(View view) {
        Toast.makeText(getApplicationContext(), "Forget Password Clicked", Toast.LENGTH_SHORT).show();
        System.out.println("Forget Password CLicked");
        Intent intent = ForgetPasswordActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_server_login)
    void onSignUp(View v) {
        if(InternetConnection.isNetworkAvailable(getApplicationContext())) {
            if (mPresenter.onFirebaseLoginAuth(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString(), this)) {
                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("Authenticating You, This may take a while");
                progressDialog.show();

            }
        }else
        {
            Toast.makeText(getApplicationContext(),"Seems Like There is No Active Internet Connection",Toast.LENGTH_LONG).show();
        }


//    mPresenter.onFirebaseLoginAuth(mEmailEditText.getText().toString(),
//                    mPasswordEditText.getText().toString(), this);


    }

//    @OnClick(R.id.ib_google_login)
//    void onGoogleLoginClick(View v) {
//        mPresenter.onGoogleLoginClick();
//    }
//
//    @OnClick(R.id.ib_fb_login)
//    void onFbLoginClick(View v) {
//        mPresenter.onFacebookLoginClick();
//    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void sendDbCall() {
        LocalCacheManager.getInstance(this).getUsers(this, mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString());
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onUsersLoaded(User users) {
        mPresenter.onUpdate(users);

        System.out.println(users.getEmail() + " " + users.getMobile());

        openMainActivity();
    }

    @Override
    public void onUserDeleted() {

    }

    @Override
    public void onUserAdded() {

    }

    @Override
    public void onDataNotAvailable() {
        mPresenter.onDetach();
    }

    @Override
    public void onUserUpdated() {

    }

    @Override
    public void onListBillRecable(List<BillsPaybale> list) {

    }

    @Override
    public void onListBillRecableSave() {

    }

    @Override
    public void onProfitandLoss(List<profitnloss> list) {

    }

    @Override
    public void onSalesOrdersCallBack(List<SalesOrders> list) {

    }



    @Override
    public void onReturn(boolean b) {


    }

    @Override
    public void onPhoneVerificationSend(boolean b) {

    }

    @Override
    public void onSignUpReturn(boolean b) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSignInReturn(boolean b) {
        if (b) {
            FirebaseUser user = mAuth.getCurrentUser();
                progressDialog.dismiss();
               Intent intent = MainActivity.getStartIntent(getApplicationContext());
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                NotificationCompat.Builder notification = new NotificationCompat.Builder(this,CHANNEL)
                        .setContentTitle("Welcome Message")
                        .setContentText("Welcome to TallyZee Family")
                        .setSmallIcon(R.drawable.ic_tallyzeelogo)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
                notificationManagerCompat.notify(channel_id,notification.build());



        } else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Error !! Authentication Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void returnOtp(boolean b) {

    }

    @Override
    public void sendOtp(String s) {

    }

    @Override
        protected void onStart () {
            super.onStart();
            FirebaseUser user = mAuth.getCurrentUser();
            if(user!=null) {
                    mAuth.addAuthStateListener(mAuthListener);

            }

        }
    }

