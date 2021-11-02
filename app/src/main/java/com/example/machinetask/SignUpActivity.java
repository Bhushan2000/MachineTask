package com.example.machinetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edit_email, edit_password, edit_name, edit_mobile;

    private Button signUpButton;

    private Spinner spinner;

    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        initViewModel();
        spinnerSetUp();

    }

    private void initViewModel() {


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

    }

    public void onSignUpClick(View view) {

        checkDataEntered();


        String name = edit_name.getText().toString().trim();
        String mobile = edit_mobile.getText().toString().trim();
        String wmail = edit_email.getText().toString().trim();
        String password = edit_password.getText().toString().trim();
        String type = spinner.getSelectedItem().toString().trim();

        userViewModel.insert( name,
                mobile,
                wmail,
                password,
                type);

        startActivity(new Intent( this,LoginActivity.class));

        finish();




    }

    private void checkDataEntered() {

        if (isEmpty(edit_name)) {

            edit_email.setError("You must enter first name to sign up!");

        }

        if (isEmpty(edit_mobile)) {
            edit_mobile.setError("mobile is required!");
        }


        if (isEmpty(edit_password)) {
            edit_password.setError("password is required!");
        }

        if (isEmail(edit_email) == false) {
            edit_email.setError("Enter valid email!");
        }
    }


    boolean isEmail(EditText text) {

        CharSequence email = text.getText().toString();

        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {

        CharSequence str = text.getText().toString();

        return TextUtils.isEmpty(str);
    }

    private void spinnerSetUp() {
        // Spinner Drop down elements
        List<String> type = new ArrayList<String>();
        type.add("--- Select User Type ---");
        type.add("Customer");
        type.add("Vendor");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void initViews() {

        edit_name = findViewById(R.id.edit_Name);

        edit_mobile = findViewById(R.id.edit_Mobile);

        edit_email = findViewById(R.id.edit_Email);

        edit_password = findViewById(R.id.edit_Password);

        signUpButton = findViewById(R.id.button_signup);

        spinner = findViewById(R.id.spinner);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show();
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        finish();

    }
}
