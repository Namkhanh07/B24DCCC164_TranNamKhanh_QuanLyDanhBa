package com.example.quanlydanhba;

import java.io.Serializable;

public class Contact implements Serializable {
    private String id;
    private String fullName;
    private String phone;
    private String group;
    private String gender;
    private boolean isFavorite;
    private String note;

    public Contact(String id, String fullName, String phone, String group, String gender, boolean isFavorite, String note) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.group = group;
        this.gender = gender;
        this.isFavorite = isFavorite;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInitialLetter() {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "?";
        }
        String trimmed = fullName.trim();
        String[] parts = trimmed.split("\\s+");
        String lastWord = parts[parts.length - 1];
        if (!lastWord.isEmpty()) {
            return String.valueOf(lastWord.charAt(0)).toUpperCase();
        }
        return String.valueOf(trimmed.charAt(0)).toUpperCase();
    }
}