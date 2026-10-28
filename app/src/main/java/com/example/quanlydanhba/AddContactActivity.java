package com.example.quanlydanhba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddContactActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etPhone;
    private Spinner spGroup;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private RadioButton rbOther;
    private CheckBox cbFavorite;
    private EditText etNote;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etFullName = findViewById(R.id.etFullName);
        etPhone = findViewById(R.id.etPhone);
        spGroup = findViewById(R.id.spGroup);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbOther = findViewById(R.id.rbOther);
        cbFavorite = findViewById(R.id.cbFavorite);
        etNote = findViewById(R.id.etNote);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSave();
            }
        });
    }

    private void handleSave() {
        String fullName = etFullName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String group = spGroup.getSelectedItem().toString();
        boolean isFavorite = cbFavorite.isChecked();
        String note = etNote.getText().toString().trim();

        if (fullName.isEmpty()) {
            etFullName.setError("Họ và tên không được để trống");
            etFullName.requestFocus();
            return;
        }

        if (phone.isEmpty() || !phone.matches("^0\\d{9}$")) {
            etPhone.setError("Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0");
            etPhone.requestFocus();
            return;
        }

        if (ContactManager.getInstance().isPhoneExists(phone)) {
            etPhone.setError("Số điện thoại đã tồn tại trong danh bạ");
            etPhone.requestFocus();
            return;
        }

        String gender = "Nam";
        if (rbFemale.isChecked()) {
            gender = "Nữ";
        } else if (rbOther.isChecked()) {
            gender = "Khác";
        }

        Contact newContact = new Contact(
                UUID.randomUUID().toString(),
                fullName,
                phone,
                group,
                gender,
                isFavorite,
                note
        );

        ContactManager.getInstance().addContact(newContact);
        Toast.makeText(this, "Thêm liên hệ thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}