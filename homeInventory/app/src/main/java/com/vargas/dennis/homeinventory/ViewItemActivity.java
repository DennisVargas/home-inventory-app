package com.vargas.dennis.homeinventory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

public class ViewItemActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameTextView, priceTextView, quantityTextView, totalValueTextView;
    private Toolbar toolbar;
    private int quantity;
    private double price;
    private double totalValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_layout);

        imageView = (ImageView) findViewById(R.id.addItemImageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        quantityTextView = (TextView) findViewById(R.id.quantityTextView);
        totalValueTextView = (TextView) findViewById(R.id.viewTotalValueTextView);

//        toolbar = (Toolbar) findViewById(R.id.viewItemToolbar);
//        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        Bitmap bitmap = ConvertByteArrayToBitmap(intent.getByteArrayExtra("image"));
        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
        else
            imageView.setImageResource(R.drawable.ic_image_white_24dp);

        nameTextView.setText("Name:  "+intent.getStringExtra("name"));

        quantityTextView.setText("Quantity:  "+intent.getStringExtra("quantity"));
        Log.i("ITEMVIEW", intent.getStringExtra("quantity"));
        priceTextView.setText("Price:  $" + intent.getStringExtra("price"));

        try{
            quantity = Integer.parseInt(intent.getStringExtra("quantity"));
        }catch(Exception e){

            quantity = 1;
        }
        try{
            price = Double.parseDouble(intent.getStringExtra("price"));
        }catch(Exception e){
            price = 0;
        }

        totalValue = quantity*price;
        totalValueTextView.setText(totalValueTextView.getText()+ String.format("%.2f", totalValue) );
    }

    public Bitmap ConvertByteArrayToBitmap(byte[] itemImage){
        Bitmap bitmap = null;
        if(itemImage != null)
            bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
        return bitmap;
    }

}
