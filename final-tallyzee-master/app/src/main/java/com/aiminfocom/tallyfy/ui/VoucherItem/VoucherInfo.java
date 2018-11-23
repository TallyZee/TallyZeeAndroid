package com.aiminfocom.tallyfy.ui.VoucherItem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BankBIll;
import com.aiminfocom.tallyfy.data.BeanModels.BillItem;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.CostCenter;
import com.aiminfocom.tallyfy.data.BeanModels.ValueSales;
import com.aiminfocom.tallyfy.data.BeanModels.Voucher;
import com.aiminfocom.tallyfy.data.Model.BankItem;
import com.aiminfocom.tallyfy.data.Model.BatchAllocation;
import com.aiminfocom.tallyfy.data.Model.CreditNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.DashType;
import com.aiminfocom.tallyfy.data.Model.DebitNoteVoucher;
import com.aiminfocom.tallyfy.data.Model.Inventories;
import com.aiminfocom.tallyfy.data.Model.Item;
import com.aiminfocom.tallyfy.data.Model.Ledger;
import com.aiminfocom.tallyfy.data.Model.PaymentBank;
import com.aiminfocom.tallyfy.data.Model.PaymentBill;
import com.aiminfocom.tallyfy.data.Model.PaymentLedger;
import com.aiminfocom.tallyfy.data.Model.PaymentVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderBatchAllocation;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderInventoriesList;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderLedger;
import com.aiminfocom.tallyfy.data.Model.PurchaseOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.PurchaseVoucher;
import com.aiminfocom.tallyfy.data.Model.ReceiptVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesOrderBatchAlloction;
import com.aiminfocom.tallyfy.data.Model.SalesOrderInventorieList;
import com.aiminfocom.tallyfy.data.Model.SalesOrderLedgers;
import com.aiminfocom.tallyfy.data.Model.SalesOrderVoucher;
import com.aiminfocom.tallyfy.data.Model.SalesVoucher;
import com.aiminfocom.tallyfy.data.Model.UniverselInventries;
import com.aiminfocom.tallyfy.data.Model.VoucherInventories;
import com.aiminfocom.tallyfy.data.Model.univBatch;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.LocalCacheManager;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.RoomDbFindPartyNameInterface;
import com.aiminfocom.tallyfy.data.db.RoomDbHelper.SingleCallBack;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.UniversalPojo;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.utils.AppConstants;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoucherInfo extends BaseActivity implements VoucherItemMvp, SingleCallBack {


    @BindView(R.id.list_Item)
    RecyclerView listItem;
    @BindView(R.id.backpressed)
    ImageView back;
    String companyNameConstatnt;
    @BindView(R.id.calender)
    ImageView calender;
    @BindView(R.id.HadderText)
    TextView hadderText;
    @BindView(R.id.prieod)
    TextView period;
    @BindView(R.id.Parent_Type)
    TextView parentType;
    @BindView(R.id.ref_number)
    TextView refNumber;
    @BindView(R.id.company_name)
    TextView companyName;


    @BindView(R.id.narration_text)
    TextView narrationText;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.ledgers)
    RecyclerView ledgerList;
    @BindView(R.id.cost_cneter_list)
    RecyclerView costCenterListRcv;

    String dt;
    String date, voucherNumber;
    BillsPaybale billsPaybale;
    @Inject
    ItemAdapter itemAdapter;
    @Inject
    LedgerList ledgers;
    @Inject
    BillDetails billDetails;
    @Inject
    BankBill bankBill;
    @Inject
    CostCenterList costCenterList;



    @Inject
    VoucherItemMvpPresenter<VoucherItemMvp> mPresenter;

    public static Intent getStartIntent(Context context, String dashType, String partyName, String date) {
        Intent intent = new Intent(context, VoucherInfo.class);
        Bundle b = new Bundle();
        b.putString(AppConstants.PARTY_NAME, partyName);
        b.putString(AppConstants.Date, date);
        b.putString(AppConstants.VOUCHER_TEXT, dashType);
        //  b.putParcelable(AppConstants.VOUCHER_LIST,billsPaybale);
        intent.putExtra(AppConstants.PRODUCT_VALUE, b);
        // intent.putParcelableArrayListExtra(AppConstants.BILLSPAYBALELIST, list);
        return intent;
    }


    @OnClick(R.id.backpressed)
    void onBackPressed(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_info);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(VoucherInfo.this);
        dt = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.VOUCHER_TEXT);
        // billsPaybale=getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getParcelable(AppConstants.VOUCHER_LIST);
        hadderText.setText(dt);
        date = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.Date);
        companyNameConstatnt = getIntent().getBundleExtra(AppConstants.PRODUCT_VALUE).getString(AppConstants.PARTY_NAME);

        String new_date = "";
        try {
            Date datee = new Date(date);
            new_date = new SimpleDateFormat("yyyyMMdd").format(datee);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        differ(companyNameConstatnt, date, dt);


    }

    void differ(String companyNameConstatnt, String date, String dash) {
        switch (DashType.valueOf(dash)) {
            case SALES:
                System.out.print(dash + " THE DATE IS" + date + "PARTY NAME" + companyNameConstatnt );
               LocalCacheManager.getInstance(getApplicationContext()).singleValueSales(this, companyNameConstatnt, date, "Sales");
                break;
            case RECEIVABLE:
                System.out.print(dash + " " + date + " " + companyNameConstatnt + " " + ValueSales.getName());
                LocalCacheManager.getInstance(getApplicationContext()).singleValueSales(this, companyNameConstatnt, date, "Sales");
                break;

            case PURCHASE:
                LocalCacheManager.getInstance(getApplicationContext()).singleValuePurchase(this, companyNameConstatnt, date, "Purchase");
                break;

            case PO:
                LocalCacheManager.getInstance(getApplicationContext()).singleValuePurchaseOrder(this, companyNameConstatnt, date, "PurchaseOrder");
                break;

            case EXPENSES:
                System.out.println(companyNameConstatnt);
                LocalCacheManager.getInstance(getApplicationContext()).singleValuePayment(this, companyNameConstatnt, date, "Payment");
                break;

            case SO:
                System.out.print(dash + " " + date + " " + companyNameConstatnt + " " + ValueSales.getName());
                LocalCacheManager.getInstance(getApplicationContext()).singleValueSalesOrder(this, companyNameConstatnt, date, "SalesOrder");
                break;

            case RECEIPT:
                LocalCacheManager.getInstance(getApplicationContext()).singleValueReciept(this, companyNameConstatnt, date, "Reciept");
                break;
        }
    }

    ArrayList<Voucher> getData() {
        ArrayList<Voucher> vouchers = new ArrayList<Voucher>();
        // public Voucher(String partyName,String dashType,String refNum,String amount,String sgst,String cgst,String productId,String quantity,String itemName)
        vouchers.add(new Voucher(billsPaybale.getBillParty(), dt, billsPaybale.getBillref(), "1357", "9", "9", "HAM/QWER", "2", "Iphone X"));
        vouchers.add(new Voucher(billsPaybale.getBillParty(), dt, billsPaybale.getBillref(), "45357", "6", "6", "HERM/REWER", "5", "IPad"));
        vouchers.add(new Voucher(billsPaybale.getBillParty(), dt, billsPaybale.getBillref(), "231357", "12", "12", "ERAM/TYWER", "6", "Amozon Kindle"));
        vouchers.add(new Voucher(billsPaybale.getBillParty(), dt, billsPaybale.getBillref(), "90357", "18", "18", "WQAM/FREER", "3", "Trello"));
        return vouchers;
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getvSalesVoucher(SalesVoucher list) {
        System.out.println("response string is" + list);
        if(list!=null) {
            System.out.println("response string is" + list);
            refNumber.setText("Ref#:" + list.getVoucherNumber());
            parentType.setText("| " + dt);
            period.setText(list.getVoucherDate());
            companyName.setText(list.getVoucherPartyName());
            ArrayList<Inventories> inventoriesList = (ArrayList<Inventories>) list.getVoucherInventories();
            ArrayList<UniverselInventries> inventrInventories = new ArrayList<>();
            for (int i = 0; i < inventoriesList.size(); i++) {
                if (inventoriesList.get(i) != null) {
                    List<BatchAllocation> batch = inventoriesList.get(i).getBatchAllocationList();
                    ArrayList<univBatch> univBatch = new ArrayList<>();
                    for (int j = 0; j < batch.size(); j++) {
                        univBatch.add(new univBatch(batch.get(j).getGowdownName(), batch.get(j).getOrderNumber(), batch.get(j).getBatchName(), batch.get(j).getAmount(), batch.get(j).getActualQty(), batch.get(j).getBillQty(), batch.get(j).getOrderDueDate()));
                    }
                    inventrInventories.add(new UniverselInventries(inventoriesList.get(i).getStockItemName(), inventoriesList.get(i).getBasicUserDesc(), inventoriesList.get(i).getRate(), inventoriesList.get(i).getAmount(), inventoriesList.get(i).getEdqty(), inventoriesList.get(i).getActualQty(), univBatch));

                }

            }
            itemAdapter.setVoucherGroupListAdapter(inventrInventories, this);
            listItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            listItem.setAdapter(itemAdapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listItem.getContext(),
//                new LinearLayoutManager(this).getOrientation());
//        listItem.addItemDecoration(dividerItemDecoration);
            narrationText.setText(list.getVoucherNarration());
            ArrayList<Inventories> inventories = (ArrayList<Inventories>) list.getVoucherInventories();
            Double amnt = 0.0;
            for (int i = 0; i < inventories.size(); i++) {
                amnt = amnt + Double.valueOf(inventories.get(i).getAmount()).intValue();
            }
            ArrayList<UniversalPojo> ledgersUniv = new ArrayList<>();
            ArrayList<Ledger> ledgerL = (ArrayList<Ledger>) list.getLedger();
            for (int l = 0; l < ledgerL.size(); l++) {
                ledgersUniv.add(new UniversalPojo(ledgerL.get(l).getLedgerName(), ledgerL.get(l).getAmount()));
            }

            ledgerL.remove(0);
            ledgers.setVoucherGroupListAdapter(ledgersUniv, this);
            ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            ledgerList.setAdapter(ledgers);
            amount.setText(String.valueOf(amnt.intValue()));
        }
    }

    @Override
    public void getSalesOrdervoucher(SalesOrderVoucher list) {
        System.out.println("response string is" + list);
        refNumber.setText("Ref#:" + list.getVoucherNumber());
        parentType.setText("| " + dt);
        period.setText(list.getVoucherDate());
        companyName.setText(list.getVoucherPartyName());

        ArrayList<SalesOrderInventorieList> inventoriesList = (ArrayList<SalesOrderInventorieList>) list.getVoucherInventories();
        ArrayList<UniverselInventries> inventrInventories = new ArrayList<>();
        for (int i = 0; i < inventoriesList.size(); i++) {
            if (inventoriesList.get(i) != null) {
                List<SalesOrderBatchAlloction> batch = inventoriesList.get(i).getBatchAllocationList();
                ArrayList<univBatch> univBatch = new ArrayList<>();
                for (int j = 0; j < batch.size(); j++) {
                    univBatch.add(new univBatch(batch.get(j).getGowdownName(), batch.get(j).getOrderNumber(), batch.get(j).getBatchName(), batch.get(j).getAmount(), batch.get(j).getActualQty(), batch.get(j).getBillQty(), batch.get(j).getOrderDueDate()));
                }
                inventrInventories.add(new UniverselInventries(inventoriesList.get(i).getStockItemName(), inventoriesList.get(i).getBasicUserDesc(), inventoriesList.get(i).getRate(), inventoriesList.get(i).getAmount(), inventoriesList.get(i).getEdqty(), inventoriesList.get(i).getActualQty(), univBatch));

            }
        }

        itemAdapter.setVoucherGroupListAdapter(inventrInventories, this);
        listItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItem.setAdapter(itemAdapter);
        narrationText.setText(list.getVoucherNarration());
        ArrayList<SalesOrderInventorieList> inventories = (ArrayList<SalesOrderInventorieList>) list.getVoucherInventories();
        Double amnt = 0.0;
        for (int i = 0; i < inventories.size(); i++) {
            amnt = amnt + Double.valueOf(inventories.get(i).getAmount()).intValue();
        }
        ArrayList<UniversalPojo> ledgersUniv = new ArrayList<>();
        ArrayList<SalesOrderLedgers> ledgerL = (ArrayList<SalesOrderLedgers>) list.getLedger();
        for (int l = 0; l < ledgerL.size(); l++) {
            ledgersUniv.add(new UniversalPojo(ledgerL.get(l).getLedgerName(), ledgerL.get(l).getLedgerAmount()));
        }

        ledgerL.remove(0);
        ledgers.setVoucherGroupListAdapter(ledgersUniv, this);
        ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ledgerList.setAdapter(ledgers);
        amount.setText(String.valueOf(amnt.intValue()));

    }

    @Override
    public void getPaymentvoucher(PaymentVoucher list) {
        System.out.println("response string is" + list);
        refNumber.setText("Ref#:" + list.getVoucherNumber());
        parentType.setText("| " + dt);
        period.setText(list.getVoucherDate());
        companyName.setText(list.getVoucherPartyName());
        ArrayList<BillItem> billItems = new ArrayList<>();
        ArrayList<BankBIll> BankBIll = new ArrayList<>();
        ArrayList<CostCenter> costCenters = new ArrayList<>();
        if (list.getLedger() != null) {

System.out.println(list.getLedger());
            ArrayList<PaymentBank> inventoriesList = (ArrayList<PaymentBank>) list.getLedger().get(1).getPaymentBankList();
            System.out.println(inventoriesList);
           BankBIll = new ArrayList<>();
            if(list.getLedger().get(0).getPaymentBankList()!=null)
            for (int i = 0; i < inventoriesList.size(); i++) {
                PaymentBank bank = inventoriesList.get(i);
                //public BankBIll(String bankAccNumber, String bankName, String bankamount, String bankTypeTransction, String bankDate)
                BankBIll.add(new BankBIll(bank.getBankAccNumber(), bank.getBankName(), bank.getBankamount(), bank.getBankTypeTransction(), bank.getBankDate()));
            }
            ArrayList<PaymentBill> billListPayment = (ArrayList<PaymentBill>) list.getLedger().get(1).getPaymentBillList();
             billItems = new ArrayList<>();
            if(list.getLedger().get(0).getPaymentBillList()!=null)
            for (int i = 0; i < billListPayment.size(); i++) {
                PaymentBill paymentBill = billListPayment.get(i);
                //public BillItem(String billName, String billAmount, String billType, String billCreditPeriod)
                billItems.add(new BillItem(paymentBill.getBillName(), paymentBill.getBillAmount(), paymentBill.getBillType(), paymentBill.getBillCreditPeriod()));
            }
            System.out.println(inventoriesList);
            ArrayList<UniverselInventries> inventrInventories = new ArrayList<>();

           costCenters = new ArrayList<>();
            ArrayList<com.aiminfocom.tallyfy.data.Model.CostCenter> costCenters1 = (ArrayList<com.aiminfocom.tallyfy.data.Model.CostCenter>) list.getLedger().get(0).getCostCenterList();
            if(list.getLedger().get(0).getCostCenterList()!=null)
            for (int i = 0; i < costCenters1.size(); i++) {
                //public CostCenter(String costactualQty, String costamount, String costbillQty, String costname)
                costCenters.add(new CostCenter(costCenters1.get(i).getCostactualQty(), costCenters1.get(i).getCostamount(), costCenters1.get(i).getCostbillQty(), costCenters1.get(i).getCostname()));
            }
        }
        costCenterListRcv.setVisibility(View.VISIBLE);
        billDetails.setVoucherGroupListAdapter(billItems, this);
        listItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItem.setAdapter(itemAdapter);
        narrationText.setText(list.getVoucherNarration());
        costCenterList.setVoucherGroupListAdapter(costCenters,this);
        costCenterListRcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        costCenterListRcv.setAdapter(costCenterList);
        bankBill.setVoucherGroupListAdapter(BankBIll, this);
        ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ledgerList.setAdapter(ledgers);

        ArrayList<PaymentLedger> inventories = (ArrayList<PaymentLedger>) list.getLedger();
        Double amnt = 0.0;
        for (int i = 0; i < inventories.size(); i++) {
            amnt = amnt + Double.valueOf(inventories.get(i).getAmount()).intValue();
        }
        ArrayList<UniversalPojo> ledgersUniv = new ArrayList<>();
//        ArrayList<PaymentLedger> ledgerL = (ArrayList<PaymentLedger>) list.getLedger();
//        for (int l = 0; l < ledgerL.size(); l++) {
//            ledgersUniv.add(new UniversalPojo(ledgerL.get(l).getLedgerName(), ledgerL.get(l).getLedgerAmount()));
//        }

//        ledgerL.remove(0);
//        ledgers.setVoucherGroupListAdapter(ledgersUniv, this);
//        ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        ledgerList.setAdapter(ledgers);
        amount.setText(String.valueOf(amnt.intValue()));
    }

    @Override
    public void getPurchasevoucher(PurchaseVoucher list) {
        System.out.println("response string is" + list);
        refNumber.setText("Ref#:" + list.getVoucherNumber());
        parentType.setText("| " + dt);
        period.setText(list.getVoucherDate());
        companyName.setText(list.getVoucherPartyName());

        List<VoucherInventories> inventoriesList = list.getVoucherInventories();
        ArrayList<UniverselInventries> inventrInventories = new ArrayList<>();
        for (int i = 0; i < inventoriesList.size(); i++) {
            if (inventoriesList.get(i) != null) {
                List<BatchAllocation> batch = inventoriesList.get(i).getBatchAllocations();
                ArrayList<univBatch> univBatch = new ArrayList<>();
                for (int j = 0; j < batch.size(); j++) {
                    univBatch.add(new univBatch(batch.get(j).getGowdownName(), batch.get(j).getOrderNumber(), batch.get(j).getBatchName(), batch.get(j).getAmount(), batch.get(j).getActualQty(), batch.get(j).getBillQty(), batch.get(j).getOrderDueDate()));
                }
                inventrInventories.add(new UniverselInventries(inventoriesList.get(i).getStockItemName(), inventoriesList.get(i).getBasicUserDesc(), inventoriesList.get(i).getRate(), inventoriesList.get(i).getAmount(), inventoriesList.get(i).getEdqty(), inventoriesList.get(i).getActualyQty(), univBatch));
            }
        }
        itemAdapter.setVoucherGroupListAdapter(inventrInventories, this);
        listItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItem.setAdapter(itemAdapter);
        narrationText.setText(list.getVoucherNarration());
        ArrayList<VoucherInventories> inventories = (ArrayList<VoucherInventories>) list.getVoucherInventories();
        Double amnt = 0.0;
        for (int i = 0; i < inventories.size(); i++) {
            amnt = amnt + Double.valueOf(inventories.get(i).getAmount()).intValue();
        }
        ArrayList<UniversalPojo> ledgersUniv = new ArrayList<>();
        ArrayList<Ledger> ledgerL = (ArrayList<Ledger>) list.getLedger();
        for (int l = 0; l < ledgerL.size(); l++) {
            ledgersUniv.add(new UniversalPojo(ledgerL.get(l).getLedgerName(), ledgerL.get(l).getAmount()));
        }
        ledgerL.remove(0);
        ledgers.setVoucherGroupListAdapter(ledgersUniv, this);
        ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ledgerList.setAdapter(ledgers);
        amount.setText(String.valueOf(amnt.intValue()));
    }

    @Override
    public void getPurchaseOrdervoucher(PurchaseOrderVoucher list) {
        System.out.println("response string is" + list);
        refNumber.setText("Ref#:" + list.getVoucherNumber());
        parentType.setText("| " + dt);
        period.setText(list.getVoucherDate());
        companyName.setText(list.getVoucherPartyName());

        ArrayList<PurchaseOrderInventoriesList> inventoriesList = (ArrayList<PurchaseOrderInventoriesList>) list.getPurchaseOrderInventoriesLists();
        ArrayList<UniverselInventries> inventrInventories = new ArrayList<>();
        for (int i = 0; i < inventoriesList.size(); i++) {
            if (inventoriesList.get(i) != null) {
                List<PurchaseOrderBatchAllocation> batch = inventoriesList.get(i).getBatchAllocationList();
                ArrayList<univBatch> univBatch = new ArrayList<>();
                for (int j = 0; j < batch.size(); j++) {
                    univBatch.add(new univBatch(batch.get(j).getGowdownName(), batch.get(j).getOrderNumber(), batch.get(j).getBatchName(), batch.get(j).getAmount(), batch.get(j).getActualQty(), batch.get(j).getBillQty(), batch.get(j).getOrderDueDate()));
                }
                inventrInventories.add(new UniverselInventries(inventoriesList.get(i).getStockItemName(), inventoriesList.get(i).getBasicUserDesc(), inventoriesList.get(i).getRate(), inventoriesList.get(i).getAmount(), inventoriesList.get(i).getEdqty(), inventoriesList.get(i).getActualQty(), univBatch));

            }
        }
        itemAdapter.setVoucherGroupListAdapter(inventrInventories, this);
        listItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItem.setAdapter(itemAdapter);
        narrationText.setText(list.getVoucherNarration());
        ArrayList<PurchaseOrderInventoriesList> inventories = (ArrayList<PurchaseOrderInventoriesList>) list.getPurchaseOrderInventoriesLists();
        Double amnt = 0.0;
        for (int i = 0; i < inventories.size(); i++) {
            amnt = amnt + Double.valueOf(inventories.get(i).getAmount()).intValue();
        }
        ArrayList<UniversalPojo> ledgersUniv = new ArrayList<>();
        ArrayList<PurchaseOrderLedger> ledgerL = (ArrayList<PurchaseOrderLedger>) list.getLedger();
        for (int l = 0; l < ledgerL.size(); l++) {
            ledgersUniv.add(new UniversalPojo(ledgerL.get(l).getLedgerName(), ledgerL.get(l).getLedgerName()));
        }

        ledgerL.remove(0);
        ledgers.setVoucherGroupListAdapter(ledgersUniv, this);
        ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ledgerList.setAdapter(ledgers);
        amount.setText(String.valueOf(amnt.intValue()));
    }

    @Override
    public void getReciptvoucher(ReceiptVoucher list) {
        System.out.println("response string is" + list);
        refNumber.setText("Ref#:" + list.getVoucherNumber());
        parentType.setText("| " + dt);
        period.setText(list.getVoucherDate());
        companyName.setText(list.getVoucherPartyName());

        ArrayList<BillItem> billItems = new ArrayList<>();
        ArrayList<BankBIll> BankBIll = new ArrayList<>();
        ArrayList<CostCenter> costCenters = new ArrayList<>();
        if (list.getLedger() != null) {

            ArrayList<BankItem> inventoriesList = (ArrayList<BankItem>) list.getLedger().get(0).getVoucherBankItem();
            BankBIll = new ArrayList<>();
            if(list.getLedger().get(0).getVoucherBankItem()!=null)
            for (int i = 0; i < inventoriesList.size(); i++) {
                BankItem bank = inventoriesList.get(i);
                //public BankBIll(String bankAccNumber, String bankName, String bankamount, String bankTypeTransction, String bankDate)
                BankBIll.add(new BankBIll(bank.getBankAccNumber(), bank.getBankName(), bank.getBankamount(), bank.getBankTypeTransction(), bank.getBankDate()));
            }
            ArrayList<Item> billListPayment = (ArrayList<Item>) list.getLedger().get(1).getItemList();
            billItems = new ArrayList<>();
            if(list.getLedger().get(1).getItemList()!=null)
            for (int i = 0; i < billListPayment.size(); i++) {
                Item paymentBill = billListPayment.get(i);
                //public BillItem(String billName, String billAmount, String billType, String billCreditPeriod)
                billItems.add(new BillItem(paymentBill.getBillName(), paymentBill.getBillAmount(), paymentBill.getBillType(), paymentBill.getBillCreditPeriod()));
            }
            ArrayList<UniverselInventries> inventrInventories = new ArrayList<>();

//            costCenters = new ArrayList<>();
//            ArrayList<com.aiminfocom.tallyfy.data.Model.CostCenter> costCenters1 = (ArrayList<com.aiminfocom.tallyfy.data.Model.CostCenter>) list.getLedger().get(0).get;
//            for (int i = 0; i < costCenters1.size(); i++) {
//                //public CostCenter(String costactualQty, String costamount, String costbillQty, String costname)
//                costCenters.add(new CostCenter(costCenters1.get(i).getCostactualQty(), costCenters1.get(i).getCostamount(), costCenters1.get(i).getCostbillQty(), costCenters1.get(i).getCostname()));
//            }
        }

        billDetails.setVoucherGroupListAdapter(billItems, this);
        listItem.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItem.setAdapter(itemAdapter);
        narrationText.setText(list.getVoucherNarration());
        costCenterList.setVoucherGroupListAdapter(costCenters,this);
        costCenterListRcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        costCenterListRcv.setAdapter(costCenterList);
        bankBill.setVoucherGroupListAdapter(BankBIll, this);
        ledgerList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ledgerList.setAdapter(ledgers);

        ArrayList<Ledger> inventories = (ArrayList<Ledger>) list.getLedger();
        Double amnt = 0.0;
        for (int i = 0; i < inventories.size(); i++) {
            amnt = amnt + Double.valueOf(inventories.get(i).getAmount()).intValue();
        }
        ArrayList<UniversalPojo> ledgersUniv = new ArrayList<>();
        amount.setText(String.valueOf(amnt.intValue()));
    }

    @Override
    public void getdebitNotevoucher(DebitNoteVoucher list) {

    }

    @Override
    public void getCreditNotevoucher(CreditNoteVoucher list) {

    }

    static class value {
        static String name;

        public static String getName() {
            return name;
        }

        public static void setName(String name) {
            value.name = name;
        }

        public static String getDate() {
            return date;
        }

        public static void setDate(String date) {
            value.date = date;
        }

        static String date;
    }
}
