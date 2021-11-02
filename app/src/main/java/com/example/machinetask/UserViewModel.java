package com.example.machinetask;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();


    //instance of DAO and database
    private AppDatabase appDatabase;

    private UserDao userDao;

    private LiveData<User> userLiveData;


    public UserViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getAppDatabase(application);

        userDao = appDatabase.userDao();


        userLiveData = userDao.getAll();


    }

    LiveData<User> getUserLive() {
        return userLiveData;
    }


    public void insert(

            String name,
            String mobile,
            String wmail,
            String password,
            String type
    ) {

        new InsertTask(userDao,
                name,
                mobile,
                wmail,
                password,
                type).execute();

    }

    private class InsertTask extends AsyncTask<Void, Void, HashMap> {

        UserDao userDao;
        String name;
        String mobile;
        String email;
        String password;
        String type;

        public InsertTask(UserDao userDao, String name, String mobile, String email, String password, String type) {
            this.userDao = userDao;
            this.name = name;
            this.mobile = mobile;
            this.email = email;
            this.password = password;
            this.type = type;
        }

        @Override
        protected HashMap doInBackground(Void... voids) {


            User user = new User();

            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setMobile(mobile);
            user.setUsertype(type);

            userDao.insert(user);
            return null;
        }
    }

    public boolean findUser(String email, String password) {


        new FindUser(userDao, email, password).execute();


        return true;


    }

    private class FindUser extends AsyncTask<Void, Void, HashMap<String, String>> {

        UserDao userDao;
        String email;
        String password;


        public FindUser(UserDao userDao, String email, String password) {
            this.userDao = userDao;
            this.email = email;
            this.password = password;
        }

        @Override
        protected HashMap<String, String> doInBackground(Void... voids) {


            userDao.findByName(email, password);


            return null;
        }
    }
}