package com.vargas.dennis.homeinventory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean fabExpanded = false;
    private FloatingActionButton fab;
    private FloatingActionButton fabAddItem;
    private FloatingActionButton fabAddFolder;
    private ImageView fabOptionsBkgrnd;
    private TextView addItemTextView;
    private TextView addFolderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
//        layoutFabAddItem = findViewById(R.id.fabAddItem);
        initFields();
        closeSubMenusFab();

    }

    private void initFields() {
        fabAddItem = findViewById(R.id.fabAddItem);
        fabAddFolder = findViewById(R.id.fabAddFolder);
        fabOptionsBkgrnd = findViewById(R.id.greyBgrndImageView);
        addItemTextView = findViewById(R.id.addItemTextView);
        addFolderTextView = findViewById(R.id.addFolderTextView);

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
}
