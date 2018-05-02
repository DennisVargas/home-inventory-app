package com.vargas.dennis.homeinventory;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean fabExpanded = false;
    private FloatingActionButton fab;
    private FloatingActionButton fabAddItem;
    private FloatingActionButton fabAddFolder;
    private ImageView fabOptionsBkgrnd;
    private TextView addItemTextView;
    private TextView addFolderTextView;

    private GridView gridView;
    private ListView listView;
    private ArrayList<InventoryItem> list;
    private InventoryListAdapter adapter = null;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
//        layoutFabAddItem = findViewById(R.id.fabAddItem);
        initFields();

        sqLiteHelper = new SQLiteHelper(this, "HomeInventoryDB.sqlite", null, 1);
        sqLiteHelper.QueryData("DROP TABLE IF EXISTS INVENTORYITEMS");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS INVENTORYITEMS (name VARCHAR PRIMARY KEY, price VARCHAR, image BLOB)");

        gridView = (GridView) findViewById(R.id.itemGridView);
        list = new ArrayList<>();
//        list.add(new InventoryItem(null, "dexterGordon", "12", "1230", null, "Hi this is Dennis", null, null));
//        list.add(new InventoryItem(null, "dexterGordon", "12", "1230", null, "Hi this is Dennis", null, null));
//        list.add(new InventoryItem(null, "dexterGordon", "12", "1230", null, "Hi this is Dennis", null, null));
//        list.add(new InventoryItem(null, "dexterGordon", "12", "1230", null, "Hi this is Dennis", null, null));
//        list.add(new InventoryItem(null, "dexterGordon", "12", "1230", null, "Hi this is Dennis", null, null));
        adapter = new InventoryListAdapter(this, R.layout.inventory_item, list);
        gridView.setAdapter(adapter);
        AddDatabaseEntry("Disheds", "453", null);
        AddDatabaseEntry("tOOTHdECAY", "2", null);
        AddDatabaseEntry("Trom", "2", null);
        AddDatabaseEntry("ppo", "2", null);

        Cursor cursor = sqLiteHelper.GetData("SELECT * FROM INVENTORYITEMS");
        list.clear();

        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String price = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new InventoryItem(image, name,"1", price,null, "notes", null, null ));
        }
        closeSubMenusFab();
    }
    public void AddDatabaseEntry(String name, String price, byte []image){
        try{
            sqLiteHelper.InsertData(
//                    edtName.getText().toString().trim(),
                    name,
//                    edtPrice.getText().toString().trim(),
                    price,
//                    imageViewToByte(imageView)
                    image
            );
                    Snackbar.make(findViewById(R.id.mainCoordinator), "Added Successfully!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
//            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
//            edtName.setText("");
//            edtPrice.setText("");
//            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initFields() {
        fabAddItem = findViewById(R.id.fabAddItem);
        fabAddFolder = findViewById(R.id.fabAddFolder);
        fabOptionsBkgrnd = findViewById(R.id.greyBgrndImageView);
        addItemTextView = findViewById(R.id.addItemTextView);
        addFolderTextView = findViewById(R.id.addFolderTextView);
    }

    private byte[] ImageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void ShowFloatingMenuOptions(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
        if(fabExpanded == true){
            closeSubMenusFab();
        }else{
            openSubMenusFab();
        }
        fab.setRotation(fab.getRotation()+45);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        fabAddFolder.setVisibility(View.INVISIBLE);
        fabAddItem.setVisibility(View.INVISIBLE);
        addItemTextView.setVisibility(View.INVISIBLE);
        addFolderTextView.setVisibility(View.INVISIBLE);
        fabOptionsBkgrnd.setAlpha(0f);

//        layoutFabSave.setVisibility(View.INVISIBLE);
//        layoutFabEdit.setVisibility(View.INVISIBLE);
//        layoutFabPhoto.setVisibility(View.INVISIBLE);
//        layoutFabAddItem.setVisibility(View.INVISIBLE);
//        fab.setImageResource(R.drawable.ic_settings_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        fabAddItem.setVisibility(View.VISIBLE);
        fabAddFolder.setVisibility(View.VISIBLE);
        addItemTextView.setVisibility(View.VISIBLE);
        addFolderTextView.setVisibility(View.VISIBLE);
        fabOptionsBkgrnd.setAlpha(0.8f);
//        layoutFabSave.setVisibility(View.VISIBLE);
//        layoutFabEdit.setVisibility(View.VISIBLE);
//        layoutFabPhoto.setVisibility(View.VISIBLE);
//        layoutFabAddItem.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
//        fab.setImageResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
    }

    public void AddItemFabClick(View view){
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
