package com.example.gabriel.simpleclient;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class ContactActivity extends BaseActivity {

    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initContact();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);
        /*recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactList.get(i);
                Toast.makeText(ContactActivity.this, contact.getName(), Toast.LENGTH_SHORT)
                        .show();
            }
        });*/
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
