package com.example.gabriel.simpleclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gabriel on 17-11-25.
 */

public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.ViewHolder> {

    private List<MyMessage> mMessageList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMessage;
        TextView rightMessage;

        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMessage = (TextView) view.findViewById(R.id.left_msg);
            rightMessage = (TextView) view.findViewById(R.id.right_msg);
        }
    }

    public MyMessageAdapter(List<MyMessage> messageList) {
        mMessageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyMessage message = mMessageList.get(position);
        if (message.getType() == MyMessage.TYPE_RECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMessage.setText(message.getContent());
        } else if (message.getType() == MyMessage.TYPE_SENT) {
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMessage.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
