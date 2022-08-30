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
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private Button SignIn;
    private TextView Already_user;
    private EditText reg_email,reg_pass,reg_name,reg_no;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        SignIn=findViewById(R.id.sign_in_btn);
        SignIn.setOnClickListener(this);

        Already_user=findViewById(R.id.Aready_user);
        Already_user.setOnClickListener(this);

        reg_email=findViewById(R.id.reg_email);
        reg_pass=findViewById(R.id.reg_pass);
        reg_name=findViewById(R.id.reg_name);
        reg_no=findViewById(R.id.reg_no);

        progressBar=findViewById(R.id.progressbar);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_btn:
                signinUser();
                break;

            case R.id.Aready_user:
                startActivity(new Intent(com.example.wanderlust.SignIn.this,Login.class));
                break;
        }

    }

    private void signinUser() {

        String email=reg_email.getText().toString().trim();
        String pass=reg_pass.getText().toString().trim();
        String name=reg_name.getText().toString().trim();
        String phone_no=reg_no.getText().toString().trim();

        if(name.isEmpty())
        {
            reg_name.setError("Name is Required!");
            reg_name.requestFocus();
            return;
        }

        if(phone_no.isEmpty())
        {
            reg_no.setError("Mobile No is Required");
            reg_no.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            reg_email.setError("Email id is Required");
            reg_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            reg_email.setError("Plese Provide Valid email");
            reg_email.requestFocus();
            return;
        }

        if(pass.length()<6){
            reg_pass.setError("Min password length should be 6 characters!");
            reg_pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            User user=new User(name,phone_no,email);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(com.example.wanderlust.SignIn.this,"User have been Sign in Sucessfully",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        Toast.makeText(com.example.wanderlust.SignIn.this,"Failed to sign in the user",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });

                        }else
                        {
                            Toast.makeText(com.example.wanderlust.SignIn.this,"Failed to sign in the user",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

    }
}