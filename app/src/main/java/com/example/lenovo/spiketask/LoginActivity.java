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

public class LoginActivity extends AppCompatActivity {

    private EditText temail,tpassword;
    private String email,password;
    private TextView signup;
    private Button login;
    private ProgressBar d;
    private FirebaseAuth fba;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        d = (ProgressBar)findViewById(R.id.progressBar);
        fba = FirebaseAuth.getInstance();

        if(fba.getCurrentUser()!=null){
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        }









        login = (Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                d.setVisibility(View.VISIBLE);
                attemptLogin();
            }
        });


    }

    private void attemptLogin() {
        temail = (EditText) findViewById(R.id.email);
        tpassword=(EditText) findViewById(R.id.password);

        email = temail.getText().toString().trim();
        password = tpassword.getText().toString().trim();

        if(email.isEmpty()||password.isEmpty()){//check if email or password fields are empty
            d.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this,"Please Enter Your Email and Password",Toast.LENGTH_SHORT).show();
        }else{//user provided both credentials
            fba.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){//successful login
                        user=fba.getCurrentUser();
                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        d.setVisibility(View.GONE);
                        startActivity(myIntent);
                        finish();

                    }   else{ //unsuccessful login
                        d.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,"Wrong Email or Password",Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
    }


    public void SignUp(View v){
        Intent Signupintent = new Intent (LoginActivity.this,SignupActivity.class);
        startActivity(Signupintent);
        finish();

    }
}
