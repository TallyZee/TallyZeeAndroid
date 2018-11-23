package com.aiminfocom.tallyfy.data.network.FirebaseNetwork;

import android.app.Activity;
import android.app.ActivityOptions;
import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import com.aiminfocom.tallyfy.ui.login.ResetPassword.ResetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class FirebaseAuthentication {

    final onCalledInterface onCalledInterface;
    private String email, pass, phone;
    private static final String TAG = "FirebaseAuthentication";
    private UserProfile userProfile;
    private Context ctx;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public FirebaseAuthentication(String phone, Context ctx) {
        this.phone = phone;
        this.ctx = ctx;
        onCalledInterface = (com.aiminfocom.tallyfy.data.network.FirebaseNetwork.onCalledInterface) ctx;

    }

    public FirebaseAuthentication(UserProfile userProfile, Context ctx) {
        this.userProfile = userProfile;
        this.ctx = ctx;
        onCalledInterface = (com.aiminfocom.tallyfy.data.network.FirebaseNetwork.onCalledInterface) ctx;

    }

    public FirebaseAuthentication(String email, String pass, Context ctx) {
        this.email = email;
        this.pass = pass;
        this.ctx = ctx;
        onCalledInterface = (com.aiminfocom.tallyfy.data.network.FirebaseNetwork.onCalledInterface) ctx;
    }

    public void getAuthResponse() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener((Activity) ctx, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    onCalledInterface.onSignInReturn(true);


                } else {
                    onCalledInterface.onSignInReturn(false);
                    Log.d("TAG","ERROR AT"+task.getException());


                }
            }
        });

    }


    public static UserProfile checkAuth() {

        FirebaseAuth maAuth = FirebaseAuth.getInstance();
        UserProfile userProfile = null;
        String email,phone,name;
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();
                    String phone = user.getPhoneNumber();
                    String name = user.getDisplayName();
                    userProfile.setUsername(name);
                    userProfile.setEmail(email);
                    userProfile.setPhone(phone);


                }
            }
        };
        return userProfile;
    }

    public void getAccountCreated() {
        String email = userProfile.getEmail();
        String pass = userProfile.getPassword();
        createAccountWithAuth(email, pass, ctx);
        createAccountWithDatabaseReference(userProfile, ctx);

    }


    public static UserProfile createAccountWithDatabaseReference(UserProfile userProfile, Context context) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users");
        String username = String.valueOf(userProfile.getEmail().hashCode());
        databaseReference.child(username).setValue(userProfile);


        return null;
    }


    public void createAccountWithAuth(String email, String pass, Context context) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//
                    sendEmailVerification(mAuth,context);
//                    String phone = userProfile.getPhone();
//                    sendOtp(phone,context);
                    onCalledInterface.onSignUpReturn(true);

                } else {
                    onCalledInterface.onSignUpReturn(false);
                }
            }
        });

    }


    public static void sendEmailVerification(FirebaseAuth mAuth, Context ctx) {

        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    //
                } else {
                    ///
                }
            }
        });


    }

    public static void sendOtp(String phone, Context context) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phone,
                60,
                TimeUnit.SECONDS,
                (Activity) context,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(context, "OTP Sent Successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(context, "OTP Sent Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public boolean checkEmailVerification() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.isEmailVerified()) {
            return true;
        } else {
            return false;
        }

    }


    public void onForgetPassword(String phone) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(phone).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    onCalledInterface.returnOtp(true);

                }
                else
                {
                    onCalledInterface.returnOtp(false);
                }
            }
        });
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                "+91"+phone,
//                60,
//                TimeUnit.SECONDS,
//                (Activity) ctx,
//                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    @Override
//                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                        onCalledInterface.sendOtp(phoneAuthCredential.getSmsCode());
//                        System.out.print(phoneAuthCredential.getSmsCode());
//                        Log.d(TAG, "onVerificationCompleted: "+phoneAuthCredential.getSmsCode());
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                        super.onCodeSent(s, forceResendingToken);
//                        Toast.makeText(ctx,"Code Sent",Toast.LENGTH_LONG).show();
//                       onCalledInterface.returnOtp(true);
//
//                    }
//                }
//        );
//        mAuth.sendPasswordResetEmail(phone)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                        {
//                            onCalledInterface.onPhoneVerificationSend(true);
//                        }
//                        else
//                        {
//                            onCalledInterface.onPhoneVerificationSend(false);
//                        }
//                    }
//                });



    }
}


