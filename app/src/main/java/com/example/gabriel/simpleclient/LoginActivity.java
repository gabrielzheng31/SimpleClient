package com.example.gabriel.simpleclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    private EditText userEdit;
    private EditText passwordEdit;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEdit = (EditText) findViewById(R.id.login_username);
        passwordEdit = (EditText) findViewById(R.id.login_password);
        button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = userEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(user.equals("admin") && password.equals("123456")) {
                    Intent intent_contact = new Intent(LoginActivity.this, ContactActivity.class);
                    startActivity(intent_contact);
                    finish();
                } else {
                    View view = findViewById(R.id.button_login);
                    Snackbar.make(view, "account or password is incorrect",
                            Snackbar.LENGTH_SHORT).show();
                }

                /*EditText user = (EditText) findViewById(R.id.login_username);
                String username = user.getText().toString();
                intent_welcome.putExtra("extra_name", username);*/

            }
        });

        TextView register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_register);
            }
        });
    }

}
