package com.example.gabriel.simpleclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    private List<Message> messageList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView messageRecyclerView;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initMessages();
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        messageRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(layoutManager);
        adapter = new MessageAdapter(messageList);
        messageRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if(!"".equals(content)) {
                    Message message = new Message(content, Message.TYPE_SENT);
                    messageList.add(message);
                    adapter.notifyItemInserted(messageList.size() - 1);
                    messageRecyclerView.scrollToPosition(messageList.size() - 1);
                    inputText.setText("");
                }
            }
        });
    }

    private void initMessages() {
        Message message_1 = new Message("Hello guy.", Message.TYPE_RECEIVED);
        messageList.add(message_1);
        Message message_2 = new Message("Hello. Who is that?.", Message.TYPE_SENT);
        messageList.add(message_2);
        Message message_3 = new Message("This is Tom. Nice talking to you.", Message.TYPE_RECEIVED);
        messageList.add(message_3);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }
}
