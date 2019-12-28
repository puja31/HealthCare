package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.button3);
        mCreateBtn = findViewById(R.id.textView3);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             String email = mEmail.getText().toString().trim();
                                             String password = mPassword.getText().toString().trim();

                                             if (TextUtils.isEmpty(email)) {
                                                 mEmail.setError("Email is Required");
                                                 return;
                                             }

                                             if (TextUtils.isEmpty(password)) {
                                                 mPassword.setError("Password is required");
                                                 return;
                                             }

                                             if (password.length() < 6) {
                                                 mPassword.setError("Password should be >= 6 Characters");
                                                 return;
                                             }
                                             progressBar.setVisibility(View.VISIBLE);
                                             fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                                     if (task.isSuccessful()) {
                                                         Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                                         startActivity(new Intent(getApplicationContext(), MainActivity.class));


                                                     } else {
                                                         Toast.makeText(Login.this, "Error Occured" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                         progressBar.setVisibility(View.GONE);

                                                     }
                                                 }
                                             });


                                         }

                                     });
                mCreateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Register.class));
                    }
                });





    }
}
