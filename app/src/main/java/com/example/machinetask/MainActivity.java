package com.example.machinetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initView();

        userViewModel.getUserLive().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                textView.setText(user.getName() + " Welcome !!");
            }
        });

    }

    private void initView() {
        textView = findViewById(R.id.tvUserName);

    }

    private void initViewModel() {

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

    }

}