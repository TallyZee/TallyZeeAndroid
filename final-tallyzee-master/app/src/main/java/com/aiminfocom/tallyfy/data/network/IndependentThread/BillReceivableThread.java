package com.aiminfocom.tallyfy.data.network.IndependentThread;


import android.os.Handler;
import android.os.Looper;

import com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve.BillReceivableParser;

public class BillReceivableThread implements Runnable {


    public void run()
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                BillReceivableParser billReceivableParser = new BillReceivableParser();
                billReceivableParser.onDataRetreive();
            }
        });
    }
}
