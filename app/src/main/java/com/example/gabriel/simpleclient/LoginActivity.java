package com.example.gabriel.simpleclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_welcome = new Intent(LoginActivity.this, ContactActivity.class);
                EditText user = (EditText) findViewById(R.id.login_username);
                String username = user.getText().toString();
                intent_welcome.putExtra("extra_name", username);
                startActivity(intent_welcome);
            }
        });

        TextView register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_register = new Intent(LoginActivity.this, ContactAdapter.class);
                startActivity(intent_register);
            }
        });
    }

}
