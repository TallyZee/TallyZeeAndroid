package com.aiminfocom.tallyfy.data.network.IndependentThread;

import android.os.AsyncTask;

import com.aiminfocom.tallyfy.data.network.FirbeaseDataRetrieve.CreditNoteParser;

public class CreditNoteThread extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        CreditNoteParser c =new CreditNoteParser();
        c.getCreditVouchers();
        return null;
    }
}
