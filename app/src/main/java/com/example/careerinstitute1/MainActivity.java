package com.example.careerinstitute1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

     databasehelperclass obj;
     EditText textarea;
     Button show;
     RadioButton green,yellow,pink;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         obj = new databasehelperclass(this);

         textarea = (EditText) findViewById(R.id.textarea);
         green=(RadioButton)findViewById(R.id.radio_green);
         yellow=(RadioButton)findViewById(R.id.radio_yellow);
         pink=(RadioButton)findViewById(R.id.radio_pink);

         yellow.setOnClickListener(this);
         green.setOnClickListener(this);
         pink.setOnClickListener(this);


     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.menu_items ,menu);
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {

         if(item.getItemId() == R.id.savem)
         {
             boolean x = obj.insertDate(textarea.getText() + "");
             if (x)
             {  Toast.makeText(this, "Sticky note save", Toast.LENGTH_LONG).show();
                 Intent i = new Intent(this,newActivity.class);
                 startActivity(i);}
             else
                 Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }

         if(item.getItemId()==R.id.view)
         {
             Intent intent=new Intent(this,newActivity.class);
             startActivity(intent);
         }

         return super.onOptionsItemSelected(item);
     }


     public void showMessage(String title, String message) {
         AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
         alertBuilder.setTitle(title);
         alertBuilder.setMessage(message);
         alertBuilder.setCancelable(true);
         alertBuilder.create().show();
     }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.radio_green)
        {
            textarea.setBackgroundColor(Color.GREEN);
        }

        if(view.getId()==R.id.radio_yellow)
        {
            textarea.setBackgroundColor(Color.YELLOW);
        }
        if(view.getId()==R.id.radio_pink)
        {
            textarea.setBackgroundColor(Color.GRAY);
        }
    }
}