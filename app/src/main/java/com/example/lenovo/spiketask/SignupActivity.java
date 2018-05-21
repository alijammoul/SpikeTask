package com.example.lenovo.spiketask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private EditText temail,tpassword,tpassword2;
    private String email,password,password2;
    private Button signup;

    private ProgressBar d;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        d=(ProgressBar)findViewById(R.id.progressBar);
        fba = FirebaseAuth.getInstance();

        signup = (Button) findViewById(R.id.signbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               attemptSignUp();
            }
        });
    }

    private void attemptSignUp() {

        temail = (EditText) findViewById(R.id.email);
        tpassword=(EditText)findViewById(R.id.password);
        tpassword2=(EditText)findViewById(R.id.password2);

        email=temail.getText().toString().trim();
        password=tpassword.getText().toString().trim();
        password2=tpassword2.getText().toString().trim();





        if(email.isEmpty()||password.isEmpty()||password2.isEmpty()){//Missing credentials

            Toast.makeText(SignupActivity.this,"Please Enter all Credentials",Toast.LENGTH_SHORT).show();
        }else{//Credentials not empty
            if(password2.equals(password)) {//matching passwords
                d.setVisibility(View.VISIBLE);
                fba.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            d.setVisibility(View.GONE);
                            Toast.makeText(SignupActivity.this,"Account Created Successfully",Toast.LENGTH_SHORT).show();
                            fba.signOut();

                            Intent myIntent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(myIntent);
                            finish();
                        }else{
                            d.setVisibility(View.GONE);
                            Toast.makeText(SignupActivity.this,"Failed to Register Account",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{//Passwords dont match
                d.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this,"Passwords dont match",Toast.LENGTH_SHORT).show();
            }
        }


    }


    public void Login(View v){
        Intent loginintent = new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(loginintent);
        finish();
    }
}
