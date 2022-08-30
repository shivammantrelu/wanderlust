package com.example.wanderlust;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextView Create_Account,ForgotPassword;
    private Button Login;
    private EditText Email,Password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Create_Account=findViewById(R.id.creat_account);
        Create_Account.setOnClickListener(this);

        Login=findViewById(R.id.Login);
        Login.setOnClickListener(this);

        Email=findViewById(R.id.login_email);
        Password=findViewById(R.id.login_pass);

        progressBar=findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();

        ForgotPassword=findViewById(R.id.forgot_password);
        ForgotPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.creat_account:
                startActivity(new Intent(com.example.wanderlust.Login.this,SignIn.class));
                break;
            case R.id.Login:
                userLogin();
                break;
            case R.id.forgot_password:
                startActivity(new Intent(com.example.wanderlust.Login.this,Forgot.class));
                break;
            case R.id.profile:
                startActivity(new Intent(com.example.wanderlust.Login.this,Profile.class));
                break;

        }


    }

    private void userLogin() {

        String email=Email.getText().toString().trim();
        String pass=Password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is Required");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please Enter a Valid Email");
            Email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            Password.setError("Password is Required");
            Password.requestFocus();
            return;
        }

        if(pass.length() < 6){
            Password.setError("Min password length is 6 Characters");
            Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(com.example.wanderlust.Login.this,Dashbord.class));
                    progressBar.setVisibility(View.GONE);
                    Email.setText("");
                    Password.setText("");

                }
                else {
                    Toast.makeText(com.example.wanderlust.Login.this,"Failed to login! please check your Email or password",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    Email.setText("");
                    Password.setText("");
                }

            }
        });

    }
}