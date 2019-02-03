package com.example.roomdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface Dao_Numbers {

    //Запросы из БД

    //Получить все данные из таблицы
    @Query("SELECT * FROM Number")
    List<Number> getAll();

    //Получить данные по определённому id
    @Query("SELECT * FROM Number WHERE id= :id")
    Number getById(long id);

    @Query("SELECT * FROM Number")
    LiveData<List<Number>> liveGetAll();

    //Очистить всю таблицу
    @Query("DELETE FROM Number")
    void deleteAll();

    @Query("DELETE FROM Number WHERE id= :id")
    void deleteByid(int id);


    @Insert
    void insert(Number number);

    @Update
    void update(Number number);

    @Delete
    void delete(Number number);


}
