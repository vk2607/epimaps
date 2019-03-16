package com.piedpiper.epimaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HospitalLogin extends AppCompatActivity {

    TextView registerHospital;
    private FirebaseAuth lAuth;
    Button loginButton;
    EditText passwordEditText, emailEditText;
    String email, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);
        lAuth = FirebaseAuth.getInstance();

        registerHospital = (TextView) findViewById(R.id.register_text);
        loginButton = (Button) findViewById(R.id.signin_button);
        passwordEditText = (EditText) findViewById(R.id.password_signin_editext);
        emailEditText = (EditText) findViewById(R.id.email_signin_edittext);

        registerHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalLogin.this, HospitalRegister.class));
            }
        });
        signIn();
    }

    private void signIn() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(HospitalLogin.this);
                pd.setMessage("Loading");
                pd.show();
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    lAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                if(lAuth.getCurrentUser().isEmailVerified()){
                                    Intent launchNextActivity;
                                    launchNextActivity = new Intent(HospitalLogin.this, HospitalHomeScreen.class);
                                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(launchNextActivity);
                                    finish();
                                }
                                else {
                                    Toast.makeText(HospitalLogin.this, "Please verify your email first", Toast.LENGTH_SHORT).show();
                                    pd.hide();
                                }
                            }
                            else {

                                Toast.makeText(HospitalLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                pd.hide();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(HospitalLogin.this, "Fll all the fileds", Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

        });
    }


}
