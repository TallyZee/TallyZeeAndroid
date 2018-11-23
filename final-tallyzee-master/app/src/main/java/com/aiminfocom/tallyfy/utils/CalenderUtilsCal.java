package com.aiminfocom.tallyfy.utils;

import android.content.Context;

import com.aiminfocom.tallyfy.data.BeanModels.GraphsValue;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceActivity;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;


import java.util.ArrayList;

public class CalenderUtilsCal {


    public static void getCount(ArrayList<UniversalPojo> list, Context context,String type) {
        ArrayList<Integer> countList = new ArrayList<>();
        double amount1 = 0.0,amount2=0.0,amount3=0.0,amount4=0.0,amount5=0.0,amount6=0.0,amount7=0.0,amount8=0.0,amount9=0.0
                ,amount10=0.0,amount11=0.0,amount12=0.0;
        double var = 0.0;
        String value = "";
        int count = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0,
                count9 = 0, count10 = 0, count11 = 0, count12 = 0;

        if (list.size()>0)
            for (int i = 0; i < list.size(); i++) {

                try {
                    if (list.get(i).getAmount() != null) {
                        var = Double.valueOf(list.get(i).getAmount().replace("-", ""));
                    }
                }catch (Exception e)
                {
                    int question = list.get(i).getAmount().lastIndexOf('?');
                    int length = (list.get(i).getAmount().length());
                    var = var + Double.valueOf(list.get(i).getAmount().substring(question + 1, length));
                }
                if(list.get(i).getPartyName()!=null)
                    value = list.get(i).getPartyName().substring(4, 6);
                switch (value) {
                    case "01":
                        count++;
                        amount1 = amount1 + var;

                        break;

                    case "02":
                        count2++;
                        amount2 = amount2 + var;
                        break;

                    case "03":
                        count3++;
                        amount3 = amount3 + var;
                        break;

                    case "04":
                        count4++;
                        amount4 = amount4 + var;
                        break;
                    case "05":
                        count5++;
                        amount5 = amount5 + var;
                        break;
                    case "06":
                        count6++;
                        amount6 = amount6 + var;
                        break;
                    case "07":
                        count7++;
                        amount7 = amount7 + var;
                        break;
                    case "08":
                        count8++;
                        amount8 = amount8 + var;
                        break;
                    case "09":
                        count9++;
                        amount9 = amount9 + var;
                        break;
                    case "10":
                        count10++;
                        amount10 = amount10 + var;
                        break;
                    case "11":
                        count11++;
                        amount11 = amount11 + var;
                        break;
                    case "12":
                        count12++;
                        amount12 = amount12 + var;
                        break;

                }
            }

        ArrayList<Double> maxList = new ArrayList<>();
        maxList.add(amount1); maxList.add(amount2); maxList.add(amount3); maxList.add(amount4); maxList.add(amount5); maxList.add(amount6); maxList.add(amount7); maxList.add(amount1); maxList.add(amount1);
        maxList.add(amount8); maxList.add(amount9); maxList.add(amount10); maxList.add(amount11); maxList.add(amount12);
        double max=0.0;
        for(int z=0;z<maxList.size();z++)
        {
            if(maxList.get(z)>max)
            {
                max=maxList.get(z);
            }

        }
        int max2 = (int) Math.round(max);
        System.out.println(max2+"This is Max");
        System.out.println(amount1+"is Amount for Jan");
        System.out.println(amount2+"is Amount for Feb");
        System.out.println(amount3+"is Amount for Mar");
        System.out.println(amount4+"is Amount for Apr");
        System.out.println(amount5+"is Amount for May");
        System.out.println(amount6+"is Amount for Jun");
        System.out.println(amount7+"is Amount for july");
        System.out.println(amount8+"is Amount for Aug");
        System.out.println(amount9+"is Amount for Sep");
        System.out.println(amount10+"is Amount for Oct");
        System.out.println(amount11+"is Amount for Nov");
        System.out.println(amount12+"is Amount for Dec");
        GraphsValue.setDashtype(type);
        GraphsValue.setCount((int) amount1);
        GraphsValue.setCount2((int) amount2);
        GraphsValue.setCount3( (int)  amount3);
        GraphsValue.setCount4( (int) amount4);
        GraphsValue.setCount5((int) amount5);
        GraphsValue.setCount6((int) amount6);
        GraphsValue.setCount7((int) amount7);
        GraphsValue.setCount8((int) amount8);
        GraphsValue.setCount9((int) amount9);
        GraphsValue.setCount10((int) amount10);
        GraphsValue.setCount11((int) amount11);
        GraphsValue.setCount12((int) amount12);
        GraphsValue.setHighestCount(max2);

    }
}