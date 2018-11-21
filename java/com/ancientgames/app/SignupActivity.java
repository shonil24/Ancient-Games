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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    Button signup ;

    private FirebaseAuth mAuth;

    EditText usrmail;
    EditText usrpss;

    String usrmailid;
    String usrpass;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (Button) findViewById(R.id.signup_btn);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

    }
        public void RegisterButtonClicked(View v) {
            usrmail = (EditText) findViewById(R.id.Email_id);
            usrpss = (EditText) findViewById(R.id.password);

            usrmailid = usrmail.getText().toString();
            usrpass = usrpss.getText().toString();

            if(TextUtils.isEmpty(usrmailid) || TextUtils.isEmpty(usrpass)){

                Toast.makeText(getApplicationContext(),"Enter Both Values",Toast.LENGTH_LONG).show();
                //error if empty
            }
            else {
                progressDialog.setMessage("Registering user...");
                progressDialog.show();
                //to make user focus on this an keep thier focus away of back end loggin

                mAuth.createUserWithEmailAndPassword(usrmailid,usrpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    //complete listener to take care of completion
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    //function runs when its completed
                        if(task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this,"Registered successfuly",Toast.LENGTH_LONG).show();
                            Intent sign = new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(sign);
                            finish();
                        }
                        else {
                            Toast.makeText(SignupActivity.this,"could not register. Please try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
}
