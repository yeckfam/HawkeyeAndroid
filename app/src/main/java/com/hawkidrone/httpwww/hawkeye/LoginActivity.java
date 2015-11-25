package com.hawkidrone.httpwww.hawkeye;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.AuthData;


public class LoginActivity extends ActionBarActivity {

    Button loginButton;
    Button registerButton;
    private Firebase myFirebaseRef;
    private AlertDialog.Builder dlgAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://radiant-heat-7859.firebaseio.com/");

        loginButton = (Button) findViewById(R.id.to_login);
        registerButton = (Button) findViewById(R.id.to_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, Register.class);
                startActivity(in);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setMessage("Enter your email address and password")
                        .setTitle("Log in")
                        .setView(LoginActivity.this.getLayoutInflater().inflate(R.layout.dialog_login, null))
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AlertDialog dlg = (AlertDialog) dialog;
                                final String email = ((TextView) dlg.findViewById(R.id.email)).getText().toString();
                                final String password = ((TextView) dlg.findViewById(R.id.password)).getText().toString();

                                myFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                                    @Override
                                    public void onAuthenticated(AuthData authData) {
                                        Intent in = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(in);
                                    }

                                    public void onAuthenticationError(FirebaseError firebaseError) {
                                        showErrorDialog();
                                    }
                                });
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private void showErrorDialog() {
        dlgAlert = new AlertDialog.Builder(LoginActivity.this);
        dlgAlert.setMessage("Wrong password or username");
        dlgAlert.setTitle("Error Message...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create();
        dlgAlert.show();
    }
}

