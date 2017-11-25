package com.example.gabriel.simpleclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends BaseActivity {

    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initContact();
        ContactAdapter adapter = new ContactAdapter(WelcomeActivity.this,
                R.layout.contact_item, contactList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactList.get(i);
                Toast.makeText(WelcomeActivity.this, contact.getName(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void initContact() {
        for (int i = 0; i < 10; i++) {
            Contact Alice = new Contact("Alice", R.drawable.alice_pic);
            contactList.add(Alice);
            Contact Bob = new Contact("Bob", R.drawable.bob_pic);
            contactList.add(Bob);
        }
    }
}
