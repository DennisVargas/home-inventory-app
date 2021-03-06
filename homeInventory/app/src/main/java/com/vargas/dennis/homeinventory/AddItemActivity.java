package com.vargas.dennis.homeinventory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.ByteArrayOutputStream;

public class AddItemActivity extends AppCompatActivity{
    private String name, price, quantity;
    private ImageView imageView;
    private TextView nameTextView, priceTextView, quantityTextView;
    private Toolbar toolbar;
    private static final int CAPTURE_IMAGE_REQUEST = 648;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_layout);

        imageView = (ImageView) findViewById(R.id.addItemImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_REQUEST);
            }
        });
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        priceTextView = (TextView) findViewById(R.id.priceEditText);
        quantityTextView = (TextView) findViewById(R.id.quantityEditText);
        toolbar = (Toolbar) findViewById(R.id.addItemToolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAPTURE_IMAGE_REQUEST && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Matrix matrix = new Matrix();
            matrix.postRotate(90f);
            Bitmap rotBitmap = Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(rotBitmap);
        }
    }

    public byte[] ConvertImageToByteArray(ImageView imageView){
        Bitmap bitmap;
        byte[] byteArray;
        try{
            bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        }catch(Exception e){
            return null;
        }
        return byteArray;
    }
    public void SaveNewItem(){
        name = nameTextView.getText().toString().trim();
        price = priceTextView.getText().toString().trim();
        quantity = quantityTextView.getText().toString().trim();
        Log.i("SAVE","add quantity"+quantity);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name",name);
        returnIntent.putExtra("price",price);
        returnIntent.putExtra("quantity",quantity);
        returnIntent.putExtra("image", ConvertImageToByteArray(imageView));
        setResult(Activity.RESULT_OK, returnIntent);
//                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
//            edtName.setText("");
//            edtPrice.setText("");
//            imageView.setImageResource(R.mipmap.ic_launcher);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.saveMenuItem:
                SaveNewItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
