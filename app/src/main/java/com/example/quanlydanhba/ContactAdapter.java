package com.example.quanlydanhba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private final Context context;
    private final int resource;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Contact contact = getItem(position);

        TextView tvAvatar = convertView.findViewById(R.id.tvAvatar);
        TextView tvFullName = convertView.findViewById(R.id.tvFullName);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);
        TextView tvGroup = convertView.findViewById(R.id.tvGroup);
        ImageView ivFavorite = convertView.findViewById(R.id.ivFavorite);

        if (contact != null) {
            tvAvatar.setText(contact.getInitialLetter());
            tvFullName.setText(contact.getFullName());
            tvPhone.setText(contact.getPhone());
            tvGroup.setText("Nhóm: " + contact.getGroup());

            if (contact.isFavorite()) {
                ivFavorite.setVisibility(View.VISIBLE);
            } else {
                ivFavorite.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}