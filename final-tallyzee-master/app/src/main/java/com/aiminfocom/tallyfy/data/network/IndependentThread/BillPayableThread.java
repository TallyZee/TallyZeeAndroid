package com.aiminfocom.tallyfy.data.network.IndependentThread;

import android.os.AsyncTask;

import com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve.BillPayableParser;


public class BillPayableThread extends AsyncTask<Void,Void,Void> {




    @Override
    protected Void doInBackground(Void... voids) {
        BillPayableParser billPayableParser = new BillPayableParser();
        billPayableParser.getBillData();
        return null;
    }
}
