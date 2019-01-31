package com.example.roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface Dao_Numbers {

    @Query("SELECT * FROM numbers")
    List<Number> getAll();

    @Query("SELECT * FROM numbers WHERE id= :id")
    Number getById(long id);

    @Insert
    void insert(Number number);

    @Update
    void update(Number number);

    @Delete
    void delete(Number number);
}
