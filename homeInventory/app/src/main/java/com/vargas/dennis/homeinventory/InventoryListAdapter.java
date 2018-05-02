package com.vargas.dennis.homeinventory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
            if (inflater != null) {
                rowView = inflater.inflate(layout, null);
                tagViews.nameTextView = (TextView) rowView.findViewById(R.id.nameTextView);
                tagViews.priceTextView = (TextView) rowView.findViewById(R.id.priceTextView);
                tagViews.imageView = (ImageView) rowView.findViewById(R.id.itemImageView);
                rowView.setTag(tagViews);
            }
            else{
                Log.i("INVLISTADP", "inflater is null!!! failed inflater!!!");
            }

        }
        else{
            tagViews = (TagViews) rowView.getTag();
        }

        InventoryItem inventoryItem = itemsList.get(position);

        tagViews.nameTextView.setText(inventoryItem.getName());
        tagViews.priceTextView.setText(inventoryItem.getPrice());

        byte [] itemImage = inventoryItem.getImage();
        if(itemImage != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
            tagViews.imageView.setImageBitmap(bitmap);
        }
        return rowView;
    }
}
