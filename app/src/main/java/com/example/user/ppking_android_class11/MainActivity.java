package com.example.user.ppking_android_class11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.mainTestview);

        myDBHelper = new MyDBHelper(this,"iii",null,1);
        db = myDBHelper.getReadableDatabase();
    }
    public void insert(View v){
        ContentValues data =  new ContentValues();
        data.put("cname","ppking");
        data.put("birthday","1988-08-02");
        data.put("tel","12354");
        db.insert("cust",null,data);
        query(null);
    }
    public void delete(View v){
        db.delete("cust","id= ? AND cname = ?",new String[]{"3","ppking"});
        query(null);
    }
    public void update(View v){
        ContentValues data =  new ContentValues();
        data.put("cname","ppking123456");
        data.put("birthday","1988-08-02");
        data.put("tel","56478979");

        db.update("cust",data,"id=?",new String[]{"5"});
        query(null);
    }

    public void query(View v){
        textView.setText("");
        // SELECT * FROM cust ORDER BY cname;
        // db.execSQL("SELECT * FROM cust");
        Cursor cursor = db.query("cust",null,"id>?",new String[]{"8"},null,null,"tel DESC");
        //moveToNext 若下一個欄位有資料則為ture
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String cname = cursor.getString(cursor.getColumnIndex("cname"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            textView.append(id +":"+ cname + ":" + birthday + ":" + tel + "\n");
        }
    }

}
