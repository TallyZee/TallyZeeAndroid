package com.aiminfocom.tallyfy.ui.ProfitandLoss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;
import com.aiminfocom.tallyfy.di.component.ActivityComponent;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.ProfitNLossEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfitnLossActivity extends AppCompatActivity implements ProfitnLossView{


    private static final String TAG = "ProfitAndLossActivity";
    ImageView goBack;
    private ExpandableListView expandableListView;
    private ExapandableAdapter exapandableAdapter;
    private HashMap<String,List<ProfitAndLoss>> hashMap;
    private List<String> newList;
    private HashMap<String,List<ProfitAndLoss>> profitHashMap;
    HashSet<String> parentSet;
    List<String> list = new ArrayList<>();
    List<String> typeHastSet;
    String typeArray[];
    List<String> popList = new ArrayList<>();


    public static Intent getStartIntent(Context context, List<ProfitAndLoss> list)
    {
        Intent intent = new Intent(context,ProfitnLossActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ArrayList", (ArrayList<? extends Parcelable>) list);
        return intent;
    }
    protected void onCreate(Bundle savedInstance)
    {
        ButterKnife.bind(this);
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_profit);
        expandableListView = findViewById(R.id.expandableListView);
        profitHashMap = new HashMap<>();
        ArrayList<ProfitAndLoss> arrayList = getIntent().getParcelableArrayListExtra("ArrayList");
        initList(arrayList);
        Log.d(TAG, "onCreate: "+arrayList);
        goBack = findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    public void initList(List<ProfitAndLoss> arrayList)
    {

        parentSet = new HashSet<>();
        hashMap = new HashMap<>();
        newList = new ArrayList<>();
        for(int i=0;i<arrayList.size();i++)
        {
            parentSet.add(arrayList.get(i).getLedgerNameParent());
        }
        Log.d(TAG, "oncrt"+parentSet);
        parentSet.remove("Indirect Incomes");
        typeArray = new String[8];
//        for(String type:parentSet)
//        {
//            switch (ProfitNLossEnum.valueOf(type))
//            {
//                case Sales_Accounts:
//                    typeArray[0]=type;
//                    break;
//
//                case DIRECT_INCOME:
//                    typeArray[1]=type;
//                    break;
//
//                case CLOSING_STOCK:
//                    typeArray[2]=type;
//                    break;
//
//                case OPENING_STOCK:
//                    typeArray[3]=type;
//                    break;
//
//                case PURCHASE:
//                    typeArray[4]=type;
//                    break;
//
//                case DIRECT_EXPENSE:
//                    typeArray[5]=type;
//                    break;
//
//                case Indirect_Incomes:
//                    typeArray[6]=type;
//                    break;
//
//                case Indirect_Expenses:
//                    typeArray[7]=type;
//                    break;
//            }
//        }
//for(int i=0;i<typeArray.length;i++)
//{
//    if(!typeArray[i].equalsIgnoreCase(""))
//    {
//        newList.add(typeArray[i]);
//    }
//}

        Log.d(TAG, "This is PArent Set: "+parentSet);
        for(String key:parentSet)
        {
            newList.add(key);
            ArrayList<ProfitAndLoss> valueList = new ArrayList<>();
            for(int j=0;j<arrayList.size();j++)
            {
                if(key.equalsIgnoreCase(arrayList.get(j).getLedgerNameParent()))
                {
                    valueList.add(arrayList.get(j));


                }


            }
            profitHashMap.put(key,valueList);
            for(Map.Entry<String, List<ProfitAndLoss>> loss:profitHashMap.entrySet())
            {
                String keyyy = loss.getKey();
                Log.d(TAG, "initList: "+keyyy);
                hashMap.put(key,loss.getValue());
            }

            exapandableAdapter = new ExapandableAdapter(this,newList,hashMap);
            expandableListView.setAdapter(exapandableAdapter);

        }



    }

//    public void initData()
//    {
//        hashMap = new HashMap<>();
//        newList = new ArrayList<>();
//
//        String keyyy = null;
//
////        newList.add("Sales Account");
////        newList.add("Purchase Account");
////        newList.add("Indirect Expenses");
////        newList.add("Indirect incomes");
//
//
//
//        Log.d(TAG, "initData: "+keyyy);
//        Log.d(TAG, "initData: "+list);
//        Log.d(TAG, "initData: "+newList);
//
//        ArrayList<String> firstLsit = new ArrayList<>();
////        for(int j=0;j<list.size();j++)
////        {
////            firstLsit.add(list.get(j).getLedgerName());
////        }
//
//
//
//        ArrayList<String> secondList = new ArrayList<>();
//        secondList.add("Android");
//        secondList.add("Studio");
//        secondList.add("C++");
//
//        ArrayList<String> thirdList = new ArrayList<>();
//        thirdList.add("Java");
//        thirdList.add("Programming");
//
//        ArrayList<String> fourtList = new ArrayList<>();
//        fourtList.add("Sales ");
//        fourtList.add("Sales ");
//
//
////        hashMap.put(newList.get(3),fourtList);
//
//
//
//
//    }
//









    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
