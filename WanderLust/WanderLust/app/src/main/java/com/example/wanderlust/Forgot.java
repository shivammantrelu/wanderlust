package com.example.wanderlust;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    private EditText Registered_email;
    private Button reset_pass_btn;
    private ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        Registered_email= (EditText) findViewById(R.id.reset_email_txtf);
        reset_pass_btn= (Button) findViewById(R.id.resetpass_btn);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        auth =FirebaseAuth.getInstance();
        reset_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }

            private void resetPassword() {
                String gmail=Registered_email.getText().toString().trim();

                if(gmail.isEmpty())
                {
                    Registered_email.setError("Email is Required!");
                    Registered_email.requestFocus();
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(gmail).matches()){
                    Registered_email.setError("Please enter a Valid email!");
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(gmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Forgot.this,"Reset password Link has been sent to your email",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }else {
                            Toast.makeText(Forgot.this,"Try again somthing wrong happened!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
    }
}