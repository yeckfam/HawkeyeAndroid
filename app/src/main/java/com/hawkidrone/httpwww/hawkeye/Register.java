package com.hawkidrone.httpwww.hawkeye;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Register extends ActionBarActivity {
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        b2 = (Button) findViewById(R.id.register_button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Register.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
