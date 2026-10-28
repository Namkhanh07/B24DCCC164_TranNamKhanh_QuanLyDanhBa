package com.example.quanlydanhba;

import java.util.ArrayList;
import java.util.UUID;

public class ContactManager {
    private static ContactManager instance;
    private final ArrayList<Contact> contactList;

    private ContactManager() {
        contactList = new ArrayList<>();
        initSampleData();
    }

    public static synchronized ContactManager getInstance() {
        if (instance == null) {
            instance = new ContactManager();
        }
        return instance;
    }

    private void initSampleData() {
        contactList.add(new Contact(UUID.randomUUID().toString(), "Nguyễn Văn An", "0901234567", "Bạn bè", "Nam", false, "Bạn học phổ thông"));
        contactList.add(new Contact(UUID.randomUUID().toString(), "Trần Thị Bình", "0912345678", "Gia đình", "Nữ", true, "Chị gái"));
        contactList.add(new Contact(UUID.randomUUID().toString(), "Lê Minh Châu", "0987654321", "Công việc", "Nam", false, "Đồng nghiệp"));
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    public boolean removeContact(String id) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(id)) {
                contactList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Contact getContactById(String id) {
        for (Contact c : contactList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean isPhoneExists(String phone) {
        for (Contact c : contactList) {
            if (c.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public int getTotalCount() {
        return contactList.size();
    }

    public int getFavoriteCount() {
        int count = 0;
        for (Contact c : contactList) {
            if (c.isFavorite()) {
                count++;
            }
        }
        return count;
    }
}