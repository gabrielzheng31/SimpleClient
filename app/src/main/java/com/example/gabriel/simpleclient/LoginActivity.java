package com.example.gabriel.simpleclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    private EditText userEdit;
    private EditText passwordEdit;
    private Button button_login;
    private Button button_database;
    private CheckBox rememberPass;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        userEdit = (EditText) findViewById(R.id.login_username);
        passwordEdit = (EditText) findViewById(R.id.login_password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        button_login = (Button)findViewById(R.id.button_login);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            userEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = userEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(user.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) { // 检查复选框是否被选中
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", user);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, ContactActivity.class);
                    startActivity(intent);
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

        button_database = findViewById(R.id.database);
        button_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DatabaseActivity.class);
                startActivity(intent);
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
