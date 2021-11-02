package com.example.machinetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_email, edit_password;

    private Button loginButton;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initViews();

        initViewModel();


    }

    private void initViewModel() {

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


    }

    private void initViews() {

        edit_email = findViewById(R.id.edit_Email);

        edit_password = findViewById(R.id.edit_Password);

        loginButton = findViewById(R.id.button_login);
    }

    boolean isEmail(EditText text) {

        CharSequence email = text.getText().toString();

        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {

        CharSequence str = text.getText().toString();

        return TextUtils.isEmpty(str);
    }

    public void onLoginClick(View view) {

        checkUsername();


    }

    void checkUsername() {
        boolean isValid = true;
        if (isEmpty(edit_email)) {
            edit_email.setError("You must enter username to login!");
            isValid = false;
        } else {
            if (!isEmail(edit_email)) {
                edit_email.setError("Enter valid email!");
                isValid = false;
            }
        }

        if (isEmpty(edit_password)) {
            edit_password.setError("You must enter password to login!");
            isValid = false;
        } else {
            if (edit_password.getText().toString().length() < 4) {
                edit_password.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }


        if (isValid) {


            String email = edit_email.getText().toString().trim();

            String password = edit_password.getText().toString().trim();

              ;

            if (userViewModel.findUser(email, password)) {

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

                this.finish();
            } else {
                Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    public void onSignUpClick(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();

    }
}