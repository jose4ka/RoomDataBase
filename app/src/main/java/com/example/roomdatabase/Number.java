package com.example.roomdatabase;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity()
public class Number {

    //Самогенерируемый первичный ключ
    @PrimaryKey(autoGenerate = true)
    public long id;


    //Конструктор для сощдания нового элемента в БД
    public Number(String name, String surname, String number){
        this.name=name;
        this.surname=surname;
        this.number=number;
    }

    //Поля которые играют роль названия колонок в БД
    public String name;
    public String surname;
    public String number;

    //Геттеры для получения данных из нужного экземпляра объекта класса
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getNumber() {
        return this.number;
    }

    public long getId() {
        return this.id;
    }

}
