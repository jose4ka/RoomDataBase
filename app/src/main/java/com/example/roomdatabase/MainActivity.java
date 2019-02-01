package com.example.roomdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Динамический массив для хранения номеров
    List<Number> numbers;
    //Экзеипляр адаптера
    Adapter_Numbers a_numbers;

    //Кнопки на экране
    Button btnAdd, btnClearAll;
    EditText et_Name, et_Surname, et_Number;

    //Элемент списка на экране
     RecyclerView rv_Numbers;

    //Объект базы данных
    AppDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        //Инициализируем лист и список на экране
        initializeList();
        initializeRecycleView();


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch (v.getId()){
                     case R.id.btn_Add:
                         dataNewNumber();
                         break;
                     case R.id.btn_ClearAll:
                         dataClearAll();
                         break;
                 }
            }
        };


        //Инициализируем элементы на экране
        btnAdd=findViewById(R.id.btn_Add);
        btnAdd.setOnClickListener(oclBtn);
        btnClearAll=findViewById(R.id.btn_ClearAll);
        btnClearAll.setOnClickListener(oclBtn);
        et_Name=findViewById(R.id.et_Name);
        et_Surname=findViewById(R.id.et_Surname);
        et_Number=findViewById(R.id.et_Number);


    }



    //Метод добавления нового элемента в список
    private void dataNewNumber(){
        //Получаем значения из полей ввода
            String new_name=et_Name.getText().toString();
            String new_surname=et_Surname.getText().toString();
            String new_number=et_Number.getText().toString();
            //Вставляем в базу данных новый объект с нужными нам значениями
            dataBase.numberDao().insert(new Number(new_name, new_surname, new_number));
            //Заполняем список обновленными данными
            initializeList();
            //Переинициализируем список для отображения новых данных
            initializeRecycleView();
            //Очищаем поля ввода
            clearFields();

    }


    //Метод для полной очистки базы данных и списка
    private void dataClearAll(){
        dataBase.numberDao().deleteAll();

        //Заного инициализируем список
        initializeList();
        initializeRecycleView();

        //Очищаем поля ввода
        clearFields();
    }



    //Метод инициализации списка
    private void initializeList(){
        if(dataBase.numberDao().getAll()==null){}
        else {
            numbers = dataBase.numberDao().getAll();
        }
    }

    //Метод для инициализации recycle view
    private void initializeRecycleView(){
        rv_Numbers=findViewById(R.id.rv_Numbers);
        a_numbers=new Adapter_Numbers(getApplicationContext(), numbers);
        rv_Numbers.setAdapter(a_numbers);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    //Метод для очистки полей воода после добавления элемента в таблицу/очистки таблицы
    private void clearFields(){
        et_Name.setText("");
        et_Surname.setText("");
        et_Number.setText("");
    }

}
