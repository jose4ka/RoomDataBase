package com.example.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class Activity_Dialog_NewNumber extends AppCompatActivity {

    EditText et_Name, et_Surname, et_Number;
    Button btn_Confirm, btn_Cancel;

    //Объект базы данных
    AppDatabase dataBase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newnumber);

        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_Name.getText().toString();
                String surname=et_Surname.getText().toString();
                String number=et_Number.getText().toString();
                switch (v.getId()){
                    case R.id.btn_Confirm:
                        dataBase.numberDao().insert(new Number(name, surname, number));
                        finish();

                        break;
                    case R.id.btn_Cancel:
                        finish();
                        break;
                }
            }
        };

        btn_Confirm=findViewById(R.id.btn_Confirm);
        btn_Confirm.setOnClickListener(oclBtn);
        btn_Cancel=findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener(oclBtn);

        et_Name=findViewById(R.id.et_Name);
        et_Surname=findViewById(R.id.et_Surname);
        et_Number=findViewById(R.id.et_Number);
    }
}
