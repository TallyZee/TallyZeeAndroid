package com.aiminfocom.tallyfy.data.db.RoomPersistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Company;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;

/**
 * Created by alahammad on 10/2/17.
 */

@Database(entities = {BillsPaybale.class, User.class, profitnloss.class, SalesOrders.class, Stock.class, Company.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BillDOA billDao();

    public abstract UserDOA userProfileDao();

    public abstract ProfitandlossDOA profitandlossDOA();

    public abstract SalesOrdersDOA salesOrdersDOA();

    public abstract StockDOA stockDOA();

    public abstract CompanyDOA companyDOA();

}
