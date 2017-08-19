package com.codepath.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        readItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First item");
        items.add("second item");
        setupListViewListener();

    } final int REQUEST_CODE = 20;
    // ActivityOne.java, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String name = data.getExtras().getString("editText");
           // int code = data.getExtras().getInt("code", 0);
            // Toast the name to display temporarily on screen
            Toast.makeText(this, "Item changed", Toast.LENGTH_SHORT).show();
            items.set(index, name);
            itemsAdapter.notifyDataSetChanged();
            writeItems();

        }
    }





    public void onAddItem(View v){
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();

    }

    public void setupListViewListener(){


        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter, View item,int pos, long id) {
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;

            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         public void onItemClick(AdapterView<?> adapter, View item,int pos, long id) {
                // ActivityOne.java
// REQUEST_CODE can be any value we like, used to determine the result type later
                final int REQUEST_CODE = 20;
                // FirstActivity, launching an activity for a result


                Intent i = new Intent(MainActivity.this, EditItemsActivity.class);
               // i.putExtra("mode", 2); // pass arbitrary data to launched activity
                startActivityForResult(i, REQUEST_CODE);

             index = pos;





            }
        });


    }

    private void readItems(){
        File filesdir = getFilesDir();
        File todofile = new File(filesdir,"todo.txt");
        try{
            items = new ArrayList<String>(FileUtils.readLines(todofile));

    }catch(IOException e){
            items = new ArrayList<String>();
        }

    }


    private void writeItems(){
        File filesdir = getFilesDir();
        File todofile = new File(filesdir,"todo.txt");
        try{
            FileUtils.writeLines(todofile, items);

        }catch(IOException e){
            e.printStackTrace();
        }


    }




}
