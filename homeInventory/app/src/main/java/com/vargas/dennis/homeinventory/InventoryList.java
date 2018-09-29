package com.vargas.dennis.homeinventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class InventoryList extends AppCompatActivity{
    GridView gridView;
    ListView listView;
    ArrayList<InventoryItem> list;
    InventoryListAdapter adapter = null;

}
