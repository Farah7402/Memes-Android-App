package com.example.careerinstitute1;

import android.content.Context;
import android.widget.Toast;

public class Message {

    public static void message(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}