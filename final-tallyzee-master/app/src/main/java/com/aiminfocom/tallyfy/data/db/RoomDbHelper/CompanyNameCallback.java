package com.aiminfocom.tallyfy.data.db.RoomDbHelper;

import com.aiminfocom.tallyfy.data.Model.Client;
import com.aiminfocom.tallyfy.data.Model.CompanyList;

import java.util.List;

public interface CompanyNameCallback {

    void getCompanyList(List<CompanyList> list);

    void getClientAfterDelete(List<CompanyList> list);
}
