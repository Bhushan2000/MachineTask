package com.example.machinetask;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<User> getAll();

    @Query("SELECT * FROM user where email LIKE  :email AND password LIKE :password")
    User findByName(String email ,String password);

    @Insert
    void insert(User users);




}