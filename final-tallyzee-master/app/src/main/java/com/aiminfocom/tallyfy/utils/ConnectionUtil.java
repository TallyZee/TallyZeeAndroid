package com.aiminfocom.tallyfy.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.Company;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrder;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.Stock;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.data.network.ApiEndPoint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by GulshanPC on 29/06/2018.
 */

public class ConnectionUtil {
    static Context appContext;
    public static String TAG="ConnectionUtil";
    private static String ID;
    private static String responseString,responseStringBillRec,responseStringBillPay,responseStringListOfCompanies;
    static ArrayList<BillsReceivable> billsReceivables;
    static ArrayList<BillsPaybale> billsPaybales;
    public static ConnectionUtil getInistance()
    {

        return new ConnectionUtil();
    }
    public static void connectTally(String ID) {

        AsyncTaskRunner runner = new AsyncTaskRunner();

        runner.execute("simple");


    }
    public ConnectionUtil()
    {

    }
    public ConnectionUtil(Context context)
    {
        appContext=context;
    }
    public static void getData(Context context)
    {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String urlParameters="<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>"+ID+"</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
        final String requestBody = urlParameters;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiEndPoint.TALLY_ENDPOINT,response -> {
            Log.e("VOLLEY ok:", response);
            System.out.println("from volly:"+ConnectionUtil.xmltoJson(response));
        }, error -> Log.e("VOLLEY error:", error.toString())) {
            @Override
            public String getBodyContentType() {
                return "text/xml; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                    System.out.println(" volly Status Code:"+response);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }





//public List<Company> getCompanyList(Context context)
//{
//    getData(context);
//    ID="Bills Receivable";
//    return list;
//}


static class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;


        @Override
        protected String doInBackground(String... params) {

            String urlParameters = "<ENVELOPE><HEADER><VERSION>1</VERSION><TALLYREQUEST>Export</TALLYREQUEST><TYPE>Data</TYPE><ID>outreportmenu</ID></HEADER><BODY><DESC><STATICVARIABLES><EXPLODEFLAG>Yes</EXPLODEFLAG><SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT></STATICVARIABLES></DESC></BODY></ENVELOPE>";
            StringBuffer response = null;
            try {
                String url = "http://192.168.1.14:9000";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type",
                        "application/xml;charset=utf-8");

                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());

                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
                String responseStatus = con.getResponseMessage();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(responseString);
                responseString=xmltoJson(response.toString());
                System.out.println(responseString);

            } catch (IOException e) {

                System.out.println("error" + e.getMessage());
            }
            System.out.println("responseString:"+response.toString());
            if(response==null)
                return null ;
            return response.toString();
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
           // callBack.processFinish(result);

            responseString=xmltoJson(result);
            System.out.println("the xml respose"+xmltoJson(result));
        }


        @Override
        protected void onPreExecute() {


        }


        @Override
        protected void onProgressUpdate(String... text) {


        }
    }

    public static String xmltoJson(String xmlString)
    {
         // some XML String previously created
        XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();
        JSONObject jsonObject = xmlToJson.toJson();

        // convert to a Json String
        String jsonString = xmlToJson.toString();
        return jsonString;
    }
    public static List<Company> convertCompanyObject(String xmlString)
    {
        List<Company> list=new ArrayList<Company>();
//        // some XML String previously created
//        System.out.println(TAG+" " +xmlString);
//        XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();
//        System.out.println(TAG+" " +xmlToJson);
//        JSONObject jsonObject = xmlToJson.toJson();
        try{
            JSONObject obj = new JSONObject(xmlString);
            JSONObject object1=obj.getJSONObject("ENVELOPE");
            JSONObject object2=object1.getJSONObject("COMPANYNAME.LIST");
            JSONArray array=object2.getJSONArray("COMPANYNAME");


            for(int i=0;i<array.length();i++) {
                JSONObject content=array.getJSONObject(i);
                String content1=content.getString("content");
            list.add(new Company(i,content1));
            }
System.out.println("thecmp list inner"+list);           // if(obj.getString())

        }catch (Exception e)
        {
            Log.e(TAG,"Json Convert:"+e.getMessage());
        }
        return list;
    }
    static List<BillsReceivable> convertBillsReceivableObject(String xmlString)
    {
        List<BillsReceivable> list=new ArrayList<BillsReceivable>();

        try{
            JSONObject obj = new JSONObject(xmlString);
            JSONObject object1=obj.getJSONObject("ENVELOPE");
            JSONArray billDue=object1.getJSONArray("BILLDUE");
            JSONArray billoverdue=object1.getJSONArray("BILLOVERDUE");
            JSONArray billCl=object1.getJSONArray("BILLCL");
            JSONArray billFixed=object1.getJSONArray("BILLFIXED");
            for(int i=0;i<billFixed.length();i++) {
                JSONObject billfixedContent=billFixed.getJSONObject(i);
                JSONObject content= (JSONObject) billCl.get(i);
                // public BillsReceivable(String billref,String billDate,String billParty,String billDue,String billCl,String billOverDue)
                list.add(new BillsReceivable(billfixedContent.getString("BILLREF"),billfixedContent.getString("BILLDATE"),billfixedContent.getString("BILLPARTY"),billDue.getString(i),content.getString("content"),billoverdue.getString(i)));
               // list.add(new BillsReceivable(billRef.getString(i),billDue.getString(i),billCl.getString(i),));

            }
            // if(obj.getString())

        }catch (Exception e)
        {
System.out.println(e.getMessage());
        }
        System.out.println("the list is:"+list);
        return list;
    }
    static List<BillsPaybale> convertBillsPaybaleObject(String xmlString)
    {
        List<BillsPaybale> list=new ArrayList<BillsPaybale>();

        try{
            JSONObject obj = new JSONObject(xmlString);
            JSONObject object1=obj.getJSONObject("OUTSTANDINGRECEIVABLE");
//            JSONArray billDue=object1.getJSONArray("BILLDUE");
//            JSONArray billoverdue=object1.getJSONArray("BILLOVERDUE");
//            JSONArray billCl=object1.getJSONArray("BILLCL");
            JSONArray billFixed=object1.getJSONArray("BILLFIXED");
            for(int i=0;i<billFixed.length();i++) {
                JSONObject billfixedContent=billFixed.getJSONObject(i);
               // JSONObject content= (JSONObject) billCl.get(i);,billfixedContent.getString("OUTDUEON"),billfixedContent.getString("OVERDUEDAYS")
                // public BillsReceivable(String billref,String billDate,String billParty,String billDue,String billCl,String billOverDue)
                list.add(new BillsPaybale(billfixedContent.getString("BILLREF"),billfixedContent.getString("BILLDATE"),billfixedContent.getString("BILLPARTY"),billfixedContent.getString("BILLCL")));
                // list.add(new BillsReceivable(billRef.getString(i),billDue.getString(i),billCl.getString(i),));

            }
            // if(obj.getString())

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("the list is:"+list);
        return list;
    }
    static profitnloss convertProfitAndLossObject(String xmlString)
    {
        List<BillsPaybale> list=new ArrayList<BillsPaybale>();
        // some XML String previously created
        HashMap<String,String> profitandloss=new HashMap<String,String>();
        int length=0;
        try{
            JSONObject obj = new JSONObject(xmlString);
            JSONObject object1=obj.getJSONObject("ENVELOPE");
            JSONArray name=object1.getJSONArray("DSPACCNAME");
            JSONArray amount=object1.getJSONArray("PLAMT");
            if(name.length()>amount.length())
            {
                length=name.length();
            }
            else
            {
                length=amount.length();
            }
            for(int i=0;i<length;i++) {
                JSONObject item1=name.getJSONObject(i);
                String itemName=item1.getString("DSPDISPNAME");
                JSONObject item2=amount.getJSONObject(i);
                String itemAmount1=item2.getString("PLSUBAMT");
                String itemAmount2=item2.getString("BSMAINAMT");
                if(!itemAmount1.equals(null))
                {
                    profitandloss.put(itemName,itemAmount1);
                }else
                {
                    profitandloss.put(itemName,itemAmount2);
                }


            }
            System.out.println("profit and loss data:"+profitandloss);
            // if(obj.getString())

        }catch (Exception e)
        {

        }
        return null;
    }
    public static List<Stock> convertStockObject(String xmlString)
    {
        List<Stock> list=new ArrayList<Stock>();
        // some XML String previously created
       // HashMap<String,String> profitandloss=new HashMap<String,String>();
        int length=0;
        try{
            JSONObject obj = new JSONObject(xmlString);
            JSONObject object1=obj.getJSONObject("ENVELOPE");
            JSONArray name=object1.getJSONArray("DSPDISPNAME");
            JSONArray amount=object1.getJSONArray("DSPSTKINFO");
            if(name.length()>amount.length())
            {
                length=name.length();
            }
            else
            {
                length=amount.length();
            }
            for(int i=0;i<length;i++) {
                JSONObject nameObject=name.getJSONObject(i);
                String itemName=nameObject.getString("DSPDISPNAME");

                JSONObject infoObject=amount.getJSONObject(i);
                String itemRate=infoObject.getString("DSPCLRATE");
                String itemAmount=infoObject.getString("DSPCLAMTA");
                String itemQtnty=infoObject.getString("DSPCLQTY");
            }
           // System.out.println("profit and loss data:"+profitandloss);
            // if(obj.getString())

        }catch (Exception e)
        {

        }
        return list;
    }
    public static List<SalesOrders> convertSalesOrders(String xmlString)
    {
        List<SalesOrders> list=new ArrayList<SalesOrders>();
        // some XML String previously created
        // HashMap<String,String> profitandloss=new HashMap<String,String>();
        int length=0;
        try{
            JSONObject obj = new JSONObject(xmlString);
            JSONObject object1=obj.getJSONObject("ENVELOPE");
            JSONArray dordate=object1.getJSONArray("DORDATE");
            JSONArray dorname=object1.getJSONArray("DORNAME");
            JSONArray doritem=object1.getJSONArray("DORITEM");
            JSONArray dorpndgqty=object1.getJSONArray("DORPNDGQTY");
            JSONArray dorrate=object1.getJSONArray("DORRATE");
            JSONArray orderdicount=object1.getJSONArray("ORDERDICOUNT");
            JSONArray dordueon=object1.getJSONArray("DORDUEON");
            JSONArray dorvalue=object1.getJSONArray("DORVALUE");
            JSONArray orderduedays=object1.getJSONArray("ORDERDUEDAYS");
for(int i=0;i<object1.length();i++)
{

 //(String dorDate,String dorName,String dorDueOn,String dorItem,String dorPndgqty,String dorRate,String dorValue)
    list.add(new SalesOrders(dordate.get(i).toString(),dorname.get(i).toString(),dordueon.get(i).toString(),doritem.get(i).toString(),dorpndgqty.get(i).toString(),dorrate.get(i).toString(),dorvalue.get(i).toString()));
}
            // System.out.println("profit and loss data:"+profitandloss);
            // if(obj.getString())

        }catch (Exception e)
        {

        }
        return list;
    }
    public List<SalesOrders> getListSalesOrder(String xml)
    {
        System.out.println("the resopnse string is:"+xml);
        List<SalesOrders> list=new ArrayList<SalesOrders>();
list=convertSalesOrders(xml);
        return  list;
    }
//    public List<String> getCompanyList()
//    {
//        System.out.println("the resopnse string is:"+responseStringListOfCompanies);
//       return convertCompanyObject(responseStringListOfCompanies);
//
//    }
    public List<BillsReceivable> getBillsReceivable(Context context,String xml)
    {
            System.out.println("the response String from bill rec:"+xml);
            List<BillsReceivable> list=convertBillsReceivableObject(xml);

         //   DataGenerator.with(AppDataBase.getAppDatabase(context)).generateBillsReceivable((ArrayList<BillsReceivable>) list);
            return list;

    }
    public profitnloss getProfitAndLoss(Context context,String xml)
    {
        System.out.println("the response String from bill rec:"+xml);
        profitnloss list=convertProfitAndLossObject(xml);

        //   DataGenerator.with(AppDataBase.getAppDatabase(context)).generateBillsReceivable((ArrayList<BillsReceivable>) list);
        return list;

    }
    public List<Stock> getStockList(Context context, String xml)
    {
        System.out.println("the response String from bill rec:"+xml);
        List<Stock> list=convertStockObject(xml);

        //   DataGenerator.with(AppDataBase.getAppDatabase(context)).generateBillsReceivable((ArrayList<BillsReceivable>) list);
        return list;

    }
    public List<BillsPaybale> getBillsPaybale(String xml)
    {

       // DataGenerator.with(AppDataBase.getAppDatabase(context)).generateBillsReceivable();
        return convertBillsPaybaleObject(xml);

    }
    private OkHttpClient client = new OkHttpClient();
    public String getConnectionToServer(String url,Context context)
    {
        final String[] responseData = {""};
        Observable.fromCallable(() -> {
            RequestBody body = RequestBody.create(ApiEndPoint.XML,url);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(ApiEndPoint.TALLY_ENDPOINT)
                    .addHeader("Content-Type",
                            "application/xml;charset=utf-8")
                    .post(body)
                    .build();
            try {
                okhttp3.Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                Log.e("Network request", "Failure", e);
            }

            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
            responseData[0] = (String) response;
                   // LocalCacheManager.getInstance(context).addBill(context);
                    System.out.println("result is:"+response+" "+response.toString());
                });
        return responseData[0];
    }

}
