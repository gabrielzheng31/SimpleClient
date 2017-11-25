package com.example.gabriel.simpleclient;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by gabriel on 17-11-24.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mContactList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View contactView;
        ImageView contactImage;
        TextView contactName;

        public ViewHolder(View view) {
            super(view);
            contactView = view;
            contactImage = (ImageView) view.findViewById(R.id.contact_image);
            contactName = (TextView) view.findViewById(R.id.contact_name);
        }
    }

    public ContactAdapter(List<Contact> contactList) {
        mContactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Contact contact = mContactList.get(position);
                Snackbar.make(view, "You clicked view " + contact.getName(),
                        Snackbar.LENGTH_SHORT).show();
                ChatActivity.actionStart(view.getContext());
            }
        });
        holder.contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Contact contact = mContactList.get(position);
                Snackbar.make(view, "You clicked image " + contact.getName(),
                        Snackbar.LENGTH_SHORT).show();
            }
        });
        holder.contactName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Contact contact = mContactList.get(position);
                Snackbar.make(view, "You clicked name " + contact.getName(),
                        Snackbar.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = mContactList.get(position);
        holder.contactImage.setImageResource(contact.getImageId());
        holder.contactName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

}
