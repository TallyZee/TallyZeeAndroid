package com.aiminfocom.tallyfy.ui.Users;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AlertDialog extends Dialog implements View.OnClickListener{

    private static final String CHANNEL = "Personal_notifications";
    private static final int channel_id = 001;
    private Context context;
    private TextView yesButton,noButton;
    public AlertDialog(Context context)
    {
        super(context);
        this.context=context;
    }


    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.yesButton:
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                LocalCacheManager.getInstance(getContext()).deleteBillsPayable();
                LocalCacheManager.getInstance(getContext()).deleteClientDao();
                LocalCacheManager.getInstance(getContext()).deleteCompanyClientDao();
                LocalCacheManager.getInstance(getContext()).deleteComapnyNameDao();
                LocalCacheManager.getInstance(getContext()).deleteCreditVoucherdao();
                LocalCacheManager.getInstance(getContext()).deleteDebitVoucherDao();
                LocalCacheManager.getInstance(getContext()).deletePaymentVoucher();
                LocalCacheManager.getInstance(getContext()).deleteProfitNLossDao();
                LocalCacheManager.getInstance(getContext()).deletepurchaseOrderDao();
                LocalCacheManager.getInstance(getContext()).deletePurchaseVoucherDao();
                LocalCacheManager.getInstance(getContext()).deleteReceiptDao();
                LocalCacheManager.getInstance(getContext()).deleteSalesOrderDao();
                LocalCacheManager.getInstance(getContext()).deleteSalesVoucherDao();
                LocalCacheManager.getInstance(getContext()).deleteUserProfileDao();


               NotificationCompat.Builder notification = new NotificationCompat.Builder(context,CHANNEL)
                        .setContentTitle("Logout Notification From TallyZee")
                        .setContentText("You Have Been Logged Off" )
                        .setSmallIcon(R.drawable.ic_tallyzeelogo)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                       .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(channel_id,notification.build());

                break;

            case R.id.noButton:
                dismiss();
                break;
        }
    }
}
