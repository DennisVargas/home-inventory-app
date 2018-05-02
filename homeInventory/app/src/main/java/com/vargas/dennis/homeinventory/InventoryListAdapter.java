package com.vargas.dennis.homeinventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryListAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<InventoryItem> itemsList;


    public InventoryListAdapter(Context context, int layout, ArrayList<InventoryItem> itemsList) {
        this.context = context;
        this.layout = layout;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class TagViews{
        ImageView imageView;
        TextView nameTextView, priceTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        TagViews tagViews = new TagViews();
        if (rowView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(layout, null);
            tagnameTextView = (TextView) rowView.findViewById(R.id.nameTextView);
            priceTextView = (TextView) rowView.findViewById(R.id.priceTextView);
            rowView.setTag();
        }
    }
}
