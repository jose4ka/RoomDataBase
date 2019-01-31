package com.example.roomdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Динамический массив для хранения номеров
    static ArrayList<Number> numbers=new ArrayList<Number>();
    //Экзеипляр адаптера
    Adapter_Numbers a_numbers;

    Button btnAdd, btnClearAll;
    EditText et_Name, et_Surname, et_Number;

    //Элемент списка на экране
    static RecyclerView rv_Numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch (v.getId()){
                     case R.id.btn_Add:
                         addNumber();
                         break;
                     case R.id.btn_ClearAll:
                         clearNumbers();
                         break;
                 }
            }
        };
        btnAdd=findViewById(R.id.btn_Add);
        btnAdd.setOnClickListener(oclBtn);
        btnClearAll=findViewById(R.id.btn_ClearAll);
        btnClearAll.setOnClickListener(oclBtn);
        et_Name=findViewById(R.id.et_Name);
        et_Surname=findViewById(R.id.et_Surname);
        et_Number=findViewById(R.id.et_Number);

        rv_Numbers=findViewById(R.id.rv_Numbers);
        a_numbers=new Adapter_Numbers(getApplicationContext(), numbers);
        rv_Numbers.setAdapter(a_numbers);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    //Метод для добавления списка
    public static void addNumber(){
        numbers.add(new Number());
        rv_Numbers.getAdapter().notifyDataSetChanged();
    }

    public static void clearNumbers(){
        numbers.clear();
        rv_Numbers.getAdapter().notifyDataSetChanged();
    }
}
