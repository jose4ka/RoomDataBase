package com.example.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter_Numbers.CallBackButtons{

    //Динамический массив для хранения номеров
    //Локальный список для подключения его к Recycle View
     List<Number> numbers;

    //Экземпляр адаптера
     Adapter_Numbers a_numbers;

    //Кнопки на экране
     Button btnAdd, btnClearAll;

    //Элемент списка на экране
     RecyclerView rv_Numbers;

    //Созданный нами объект базы данных
     AppDatabase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализируем базу данных
        dataBase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        //Инициализируем лист и список на экране
        //Эти методы описаны ниже
        initializeList();
        initializeRecycleView();


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch (v.getId()){
                     case R.id.btn_Add:
                         //Запускаем всплывающее окно для добавления номера
                         Intent add=new Intent(MainActivity.this, Activity_Dialog_NewNumber.class);
                         startActivity(add);
                         break;
                     case R.id.btn_ClearAll:
                         //чищаем таблицу
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

    }

    //Метод для полной очистки таблицы и списка
    private void dataClearAll(){
        //Обращаемся к базе данных, берем из неё объект Dao, из Dao ыпоняем
        dataBase.numberDao().deleteAll();
        //Заного инициализируем список
        initializeList();
        initializeRecycleView();
    }



    //Метод инициализации списка
    private  void initializeList(){
        //Проверяем базу на пустоту
        if(!(dataBase.numberDao().getAll()==null)){
            //Присваиваем локальному списку данные базы
            numbers = dataBase.numberDao().getAll();
        }
    }


    //Метод для инициализации recycle view
    private  void initializeRecycleView(){
        //Находим список на экране
        rv_Numbers=findViewById(R.id.rv_Numbers);
        //Инициализируем адаптер
        a_numbers=new Adapter_Numbers(getBaseContext(), numbers, this);
        //Присваиваем списку адаптер
        rv_Numbers.setAdapter(a_numbers);
        rv_Numbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    //Метод удаления нужного объекта из таблицы, вызывается интерфейсом адаптера
    @Override
    public void deleteNumber(Number number) {
        dataBase.numberDao().delete(number);
        initializeList();
        initializeRecycleView();
    }

    @Override
    public void updateNumber(Number number) {

    }


    /*
    В методах onStart и onResume мы "обновляем"
    Они активируются после закрытия диалогового окна с добавлением номера
    Тем самым, после закрытия диалогового окна, мы видим уже обновлённый спискок
     */
    @Override
    protected void onStart() {
        super.onStart();
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeList();
        initializeRecycleView();
    }
}
