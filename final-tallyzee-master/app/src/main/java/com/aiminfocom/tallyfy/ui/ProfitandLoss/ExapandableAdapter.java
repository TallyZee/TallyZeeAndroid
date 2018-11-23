package com.aiminfocom.tallyfy.ui.ProfitandLoss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.Model.ProfitAndLoss;

import java.util.HashMap;
import java.util.List;

public class ExapandableAdapter extends BaseExpandableListAdapter {

    private List<String> header;
    private HashMap<String,List<ProfitAndLoss>> listHashMap;
    private Context context;
public ExapandableAdapter(Context context,List<String> header,HashMap<String,List<ProfitAndLoss>> listHashMap)
{
this.context=context;
this.header=header;
this.listHashMap=listHashMap;
}

    @Override
    public int getGroupCount() {
        return header.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return header.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(header.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    String title = (String)getGroup(groupPosition);
    if(convertView==null)
    {
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.exapandable_list_view,null);

    }
        TextView title2 = convertView.findViewById(R.id.titleText);

    title2.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       ProfitAndLoss child = (ProfitAndLoss) getChild(groupPosition,childPosition);
       if(convertView==null)
       {
           LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.child_expandable_listview,null);

       }
       TextView child2 = convertView.findViewById(R.id.childText);
        TextView rupee = convertView.findViewById(R.id.rupee);
       child2.setText(child.getLedgerName());
       rupee.setText(child.getLedgerAmount());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
