package com.hawkidrone.httpwww.hawkeye;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class Register extends ActionBarActivity {
    Button registerbutton;
    private Firebase myFirebaseRef;
    private AlertDialog.Builder dlgAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://radiant-heat-7859.firebaseio.com/");

        registerbutton = (Button) findViewById(R.id.register_button);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String input_email = ((TextView) findViewById(R.id.input_email)).getText().toString();
                final String input_password = ((TextView) findViewById(R.id.input_password)).getText().toString();

                myFirebaseRef.createUser(input_email, input_password, new Firebase.ResultHandler() {
                  @Override
                public void onSuccess() {
                      Intent in = new Intent(Register.this, MainActivity.class);
                      startActivity(in);
                 }
                @Override
                public void onError(FirebaseError firebaseError) {
                    showErrorDialog();
                 }
                });
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("911");
            }
        });
    }

    private void showErrorDialog() {
        dlgAlert = new AlertDialog.Builder(Register.this);
        dlgAlert.setMessage("Registration failed! Please check your Admin.");
        dlgAlert.setTitle("Error Message...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create();
        dlgAlert.show();
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
