package com.aiminfocom.tallyfy.data.network.FirebaseNetwork;

public interface onCalledInterface {


    void onReturn(boolean b);

    void onPhoneVerificationSend(boolean b);

    void onSignUpReturn(boolean b);
    void onSignInReturn(boolean b);


    void returnOtp(boolean b);


    void sendOtp(String s);
}

