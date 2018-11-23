package com.aiminfocom.tallyfy.utils;
import com.aiminfocom.tallyfy.data.BeanModels.GraphsValue;
import com.aiminfocom.tallyfy.data.Model.BillsReceables;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import java.util.ArrayList;
public class CalenderUtilView {
    public  void getData(ArrayList<UniversalPojo> list,String type)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int count=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,
                count9=0,count10=0,count11=0,count12=0;
        for(int i=0;i<list.size();i++) {
            if(!list.get(i).getBillOverdue().isEmpty()) {
                arrayList.add(Integer.valueOf(list.get(i).getBillOverdue()));
            }
        }
        for(int j=0;j<arrayList.size();j++)
        {
            if(arrayList.get(j)<0)
            {
                count8++;
            }
           else if(arrayList.get(j)>0 && arrayList.get(j)<30)
            {
                count++;
            }
            else if(arrayList.get(j)>31 && arrayList.get(j)<60)
            {
                count2++;
            }
            else if(arrayList.get(j)>61&& arrayList.get(j)<90)
            {
                count3++;
            }
            else if(arrayList.get(j)>91&& arrayList.get(j)<120)
            {
                count4++;
            }
            else   if(arrayList.get(j)>121&& arrayList.get(j)<150)
            {
                count5++;
            }
            else  if(arrayList.get(j)>151&& arrayList.get(j)<180)
            {
                count6++;
            }
            else  if(arrayList.get(j)>180)
            {
                count7++;
            }
        }
        double max = 0.0;
        ArrayList<Integer> countList = new ArrayList<>();
        countList.add(count);
        countList.add(count2);
        countList.add(count3);
        countList.add(count4);
        countList.add(count5);
        countList.add(count6);
        countList.add(count7);
        countList.add(count8);
        for(int y=0;y<countList.size();y++)
        {
            if(countList.get(y)>max)
            {
                max=countList.get(y);
            }
        }
        System.out.println(max +"is max");
        GraphsValue.setCount(count);
        GraphsValue.setCount2(count2);
        GraphsValue.setCount3(count3);
        GraphsValue.setCount4(count4);
        GraphsValue.setCount5(count5);
        GraphsValue.setCount6(count6);
        GraphsValue.setCount7(count7);
        GraphsValue.setCount8(count8);
        GraphsValue.setHighestCountPay(max);
        GraphsValue.setDashtype(type);
        System.out.println( "COUNT 1"+GraphsValue.getCount());
        System.out.println( "COUNT 2"+GraphsValue.getCount2());
        System.out.println( "COUNT 3"+GraphsValue.getCount3());
        System.out.println( "COUNT 4"+GraphsValue.getCount4());
        System.out.println( "COUNT 5"+GraphsValue.getCount5());
        System.out.println( "COUNT 6"+GraphsValue.getCount6());
        System.out.println( "COUNT 7"+GraphsValue.getCount7());
    }
}