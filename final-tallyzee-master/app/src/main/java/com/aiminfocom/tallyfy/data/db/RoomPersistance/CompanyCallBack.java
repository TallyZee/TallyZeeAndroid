package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import java.util.List;

import com.aiminfocom.tallyfy.data.BeanModels.Company;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;

/**
 * Created by GulshanPC on 20/07/2018.
 */

public interface CompanyCallBack {
    void onDataNotAvailable();

    void dataBaseCompanyList(List<Company> list);
}
