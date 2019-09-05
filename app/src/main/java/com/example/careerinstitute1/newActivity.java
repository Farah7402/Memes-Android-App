package com.example.careerinstitute1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class newActivity extends AppCompatActivity {

    databasehelperclass obj;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        obj = new databasehelperclass(this);

        listItem = new ArrayList<>();

      userlist=(ListView)findViewById(R.id.listshow);

        viewData();

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text=userlist.getItemAtPosition(i).toString();
                Intent is = new Intent  (getApplicationContext(),Main2Activity.class);
                is.putExtra("Listviewvalue",text);
                startActivity(is);

               // Toast.makeText(newActivity.this,""+text,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_menu ,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.newsticky)
        {
          Intent i = new Intent(newActivity.this,MainActivity.class);
          startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private void viewData() {

        Cursor cur = obj.fetchData();
        if (cur.getCount() == 0) {
            showMessage("Alert ", "No data found");
        } else {
           // StringBuffer buffer = new StringBuffer();
            while (cur.moveToNext()) {
               listItem.add(cur.getString(1));
            }
         //   showMessage("Data", buffer.toString());

            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adapter);
        }


    }

    public void showMessage (String title, String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(true);
        alertBuilder.create().show();
    }
}
