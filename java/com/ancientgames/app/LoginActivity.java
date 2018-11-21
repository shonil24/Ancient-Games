package com.ancientgames.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    Button signup;
    Button login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText usrmail;
    private EditText usrpss;

    ProgressDialog progressDialog;
    //determines current aunthentication state of user (mauthlistener)
    //constantly check sif ser is aunthenticated or not if yes then user can access aplication data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (Button) findViewById(R.id.signup_btn);
        login = (Button) findViewById(R.id.loginbtn);

        usrmail = (EditText) findViewById(R.id.Email_id);
        usrpss = (EditText) findViewById(R.id.password);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null) {
                    //if some or any user is logged in or if user is not deautheticated i.e not null
                    Intent log = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(log);
                    finish();
                }
            }
        };


    }

   /* @Override
    public void onBackPressed() {
        // disable going back to the MainActivity after log out as after logout flow will be directed here
        moveTaskToBack(true);
        no need for this now just kill or finish the logout activity intent
    }*/

    @Override
    protected void onStart()
    //to constantly check if user is logged in.it creates an instance for checking
    {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    //if login button is clicked
        public void LoginButtonclicked(View v) {
            String usrmailid = usrmail.getText().toString();
            String usrpass = usrpss.getText().toString();

            //check if field r empty
            if(TextUtils.isEmpty(usrmailid) || TextUtils.isEmpty(usrpass)){

                Toast.makeText(getApplicationContext(),"Enter Both Values",Toast.LENGTH_LONG).show();
                //error if empty
            }
            else {
                mAuth.signInWithEmailAndPassword(usrmailid,usrpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    //login as if field is filled.If all work done execute code inside oncomplete
                    //complete listener to take care of completion
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //function runs when its completed
                        //check if task is not successful
                        if(!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,"Incorrect Email or Password",Toast.LENGTH_LONG).show();
                        }
                        else {
                            progressDialog.setMessage("Signing user in...");
                            progressDialog.show();
                            Toast.makeText(getApplicationContext(),"Logged in successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

        }
    //end login code

        public void SignupButtonClicked(View v) {
            Intent sign = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(sign);
        }

}
