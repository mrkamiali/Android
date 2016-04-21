package com.android.extencustomlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kamran ALi on 3/4/2016.
 */
public class Exp_Adapter extends BaseExpandableListAdapter {
    private Context context ;
    private List<Parent> parents ;

    public Exp_Adapter(Context context, List<Parent> parents) {
        this.context = context;
        this.parents = parents;
    }

    @Override
    public int getGroupCount() {
        return parents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parents.get(groupPosition).getChild().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parents.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {
        return parents.get(parent).getChild().get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String androidVersions_title = parents.get(parent).getName();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout, null);
        }
        TextView parent_View = (TextView) convertView.findViewById(R.id.parent_text);
        parent_View.setText(androidVersions_title);
        return convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean isLastChild, View convertView, ViewGroup parentView) {
        String child_title  = parents.get(parent).getChild().get(child).getName();
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.child_layout,null);
        }
        TextView childView = (TextView) convertView.findViewById(R.id.child_text);
        childView.setText(child_title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
