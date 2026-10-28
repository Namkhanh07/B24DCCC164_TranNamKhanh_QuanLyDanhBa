package com.example.quanlydanhba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView tvAvatarDetail;
    private TextView tvFullNameDetail;
    private TextView tvPhoneDetail;
    private TextView tvGroupGenderDetail;
    private TextView tvNoteDetail;
    private CheckBox cbFavoriteDetail;
    private Button btnCall;
    private Button btnBack;
    private Button btnDelete;

    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        tvAvatarDetail = findViewById(R.id.tvAvatarDetail);
        tvFullNameDetail = findViewById(R.id.tvFullNameDetail);
        tvPhoneDetail = findViewById(R.id.tvPhoneDetail);
        tvGroupGenderDetail = findViewById(R.id.tvGroupGenderDetail);
        tvNoteDetail = findViewById(R.id.tvNoteDetail);
        cbFavoriteDetail = findViewById(R.id.cbFavoriteDetail);
        btnCall = findViewById(R.id.btnCall);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        String contactId = getIntent().getStringExtra("CONTACT_ID");
        if (contactId != null) {
            currentContact = ContactManager.getInstance().getContactById(contactId);
        }

        if (currentContact == null) {
            Toast.makeText(this, "Không tìm thấy thông tin liên hệ", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        displayContactDetails();

        cbFavoriteDetail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (currentContact != null) {
                    currentContact.setFavorite(isChecked);
                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentContact != null) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + currentContact.getPhone()));
                    startActivity(dialIntent);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentContact != null) {
                    ContactManager.getInstance().removeContact(currentContact.getId());
                    Toast.makeText(ContactDetailActivity.this, "Đã xóa liên hệ thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void displayContactDetails() {
        tvAvatarDetail.setText(currentContact.getInitialLetter());
        tvFullNameDetail.setText(currentContact.getFullName());
        tvPhoneDetail.setText("SĐT: " + currentContact.getPhone());
        tvGroupGenderDetail.setText("Nhóm: " + currentContact.getGroup() + " | Giới tính: " + currentContact.getGender());
        String noteText = currentContact.getNote();
        if (noteText == null || noteText.trim().isEmpty()) {
            noteText = "Không có";
        }
        tvNoteDetail.setText("Ghi chú: " + noteText);
        cbFavoriteDetail.setChecked(currentContact.isFavorite());
    }
}