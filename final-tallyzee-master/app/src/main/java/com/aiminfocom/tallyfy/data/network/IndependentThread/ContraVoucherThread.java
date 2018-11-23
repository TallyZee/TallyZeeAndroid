package com.aiminfocom.tallyfy.data.network.IndependentThread;

import android.os.AsyncTask;

import com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve.ContraVouchersParser;

public class ContraVoucherThread extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        ContraVouchersParser contraVouchersParser = new ContraVouchersParser();
        contraVouchersParser.getContraVouchers();
        return null;
    }
}
