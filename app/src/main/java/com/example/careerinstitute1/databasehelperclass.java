package com.example.careerinstitute1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class databasehelperclass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="sticknotesapp";
    private static final String TABLE_NAME="stickyTable";
    private static final int DATABASE_VERSION=1;
    private static final String ID="_ID";
    private static final String NAME="STICKY_NAME";
    private static final String CREATE_TABLE="CREATE TABLE " +TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+NAME+" VARCHAR(255) );";
    private static final String DROP_TABLE= "DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public databasehelperclass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
   /*dont understand*/
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
          Message.message(context,"on Create called");
          db.execSQL(CREATE_TABLE);
        }
        catch (SQLException e)
        {
            Message.message(context,""+e);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try
        {
            Message.message(context,"on Upgrade called");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (android.database.SQLException e)
        {
            Message.message(context,""+e);

        }
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean insertDate(String tname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,tname);
               long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        return false;
        else
            return true;


    }

    public Cursor fetchData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("Select * from "+TABLE_NAME,null);
        return cur;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }



}
