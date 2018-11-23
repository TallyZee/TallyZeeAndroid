package com.aiminfocom.tallyfy.data.network.IndependentThread;

import android.os.AsyncTask;
import com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve.SalesVoucherParser;


public class SalesVoucherThread extends AsyncTask<Void,Void,Void>{

    public static final String TAG = "SalesVoucherThread";

    @Override
    protected Void doInBackground(Void... voids) {
                SalesVoucherParser salesVoucherParser = new SalesVoucherParser();
        salesVoucherParser.getSalesVoucher();
        return null;
    }



}

