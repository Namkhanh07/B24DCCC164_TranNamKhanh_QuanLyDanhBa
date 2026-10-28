package com.example.quanlydanhba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvStats;
    private ListView lvContacts;
    private Button btnAddContact;

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStats = findViewById(R.id.tvStats);
        lvContacts = findViewById(R.id.lvContacts);
        btnAddContact = findViewById(R.id.btnAddContact);

        adapter = new ContactAdapter(
                this,
                R.layout.item_contact,
                ContactManager.getInstance().getContactList()
        );
        lvContacts.setAdapter(adapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = ContactManager.getInstance().getContactList().get(position);
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("CONTACT_ID", contact.getId());
                startActivity(intent);
            }
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        updateStats();
    }

    private void updateStats() {
        int total = ContactManager.getInstance().getTotalCount();
        int favorite = ContactManager.getInstance().getFavoriteCount();
        tvStats.setText("Tổng số liên hệ: " + total + " | Yêu thích: " + favorite);
    }
}