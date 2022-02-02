package com.example.prayertime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GoodDeeds extends AppCompatActivity {
    private  Button btn,getBtn;
    private  EditText txt;
    private TextView textView;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deeds);
        btn = findViewById(R.id.button);
      /*  textView.setMovementMethod(new ScrollingMovementMethod());*/
        txt = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView2);
        getBtn = findViewById(R.id.get);
        DB = new DBHelper(this);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(GoodDeeds.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setText("");
                while (res.moveToNext()) {
                    textView.append("Task :" + res.getString(0) + "\n");
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Deeds = txt.getText().toString();

                if (Deeds!=null)
                {
                    Boolean checkinsertdata = DB.insertuserdata(Deeds);
                    if (checkinsertdata == true)
                    {
                        textView.append("Task :" + Deeds+ "\n");
                        Toast.makeText(GoodDeeds.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                        txt.setText("");
                    }
                }
                else {
                    Toast.makeText(GoodDeeds.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

}