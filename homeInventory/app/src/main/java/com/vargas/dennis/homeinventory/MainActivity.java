package com.vargas.dennis.homeinventory;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
    private boolean anotherNewItem = false;
    public static SQLiteHelper sqLiteHelper;
    private static final int ADD_ITEM_REQUEST_CODE = 934;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
//        layoutFabAddItem = findViewById(R.id.fabAddItem);
        initFields();
        initGridItemClick();
        gridView.setAdapter(adapter);

        UpdateItemGridView();
        closeSubMenusFab();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.itemGridView) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//            menu.setHeaderTitle(list.get(info.position).getName());

            String[] menuItems = getResources().getStringArray(R.array.deleteMenu);
//            String []menuItems = {"Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuItem.getMenuInfo();
        int menuItemIndex = menuItem.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.deleteMenu);
        String menuItemName = menuItems[menuItemIndex];
        InventoryItem inventoryItem = list.get(info.position);
        int id = inventoryItem.getId();
        sqLiteHelper.DeleteData(id);
        UpdateItemGridView();
        return true;
    }

    private void initGridItemClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(findViewById(R.id.mainCoordinator),"Item Clicked!!!!",
                        Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ViewItemActivity.class);
                InventoryItem item = (InventoryItem)parent.getItemAtPosition(position);
                intent.putExtra("name", item.getName());
                intent.putExtra("quantity", item.getQuantity());
                Log.i("MAINACTIVITY", item.getQuantity());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("image", item.getImage());
                startActivity(intent);
//                ToggleFloatingMenuOptions(view);
//                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });
    }

    public void AddDatabaseEntry(String name, String price, String quantity, byte []image){
        try{
            sqLiteHelper.InsertData(name, price, quantity, image);// imageViewToByte(imageView)
                    Snackbar.make(findViewById(R.id.mainCoordinator), "Item Added Successfully!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
            UpdateItemGridView();
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

//        database initialization and loading
        sqLiteHelper = new SQLiteHelper(this, "HomeInventoryDB.sqlite", null, 1);
//        sqLiteHelper.QueryData("DROP TABLE IF EXISTS INVENTORYITEMS");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS INVENTORYITEMS (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, quantity VARCHAR, image BLOB)");

        gridView = (GridView) findViewById(R.id.itemGridView);
        list = new ArrayList<>();
        adapter = new InventoryListAdapter(this, R.layout.inventory_item, list);
    }

    private byte[] ImageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void ToggleFloatingMenuOptions(View view) {
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

        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        fabAddItem.setVisibility(View.VISIBLE);
        fabAddFolder.setVisibility(View.VISIBLE);
        addItemTextView.setVisibility(View.VISIBLE);
        addFolderTextView.setVisibility(View.VISIBLE);
        fabOptionsBkgrnd.setAlpha(0.8f);

        fabExpanded = true;
    }

    public void AddItemFabClick(View view){
        Snackbar.make(view, "Add Item Clicked", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
//        startActivity(intent);
        ToggleFloatingMenuOptions(view);
        startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
//        AddDatabaseEntry("Disheds", "453", null);
//        AddDatabaseEntry("tOOTHdECAY", "2", null);
//        AddDatabaseEntry("Trom", "2", null);
//        AddDatabaseEntry("ppo", "2", null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ITEM_REQUEST_CODE && data != null){
            Bundle extras = data.getExtras();
            String name = (String)extras.get("name");
            String price = (String) extras.get("price");
            String quantity = (String) extras.get("quantity");
            Log.i("MAIN", "result activity: "+ quantity);
            byte[] imageBytes = (byte[]) extras.get("image");
            AddDatabaseEntry(name, price, quantity, imageBytes);
            UpdateItemGridView();
        }
    }

    public void UpdateItemGridView(){
        Cursor cursor = sqLiteHelper.GetData("SELECT * FROM INVENTORYITEMS");
        list.clear();

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String quantity = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            System.out.println(image);
            list.add(new InventoryItem(image, name, quantity, price,null, "notes", null, null, id ));
        }
        registerForContextMenu(gridView);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
