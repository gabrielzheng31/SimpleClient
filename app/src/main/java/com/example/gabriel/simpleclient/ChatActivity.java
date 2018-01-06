package com.example.gabriel.simpleclient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ThemedSpinnerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    public static final int TAKE_PHOTO = 1;

    private List<MyMessage> messageList = new ArrayList<>();
    private List<Image> imageList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private Button sendImg;
    private RecyclerView messageRecyclerView;
    private MyMessageAdapter adapter;
    private ImageAdapter imageAdapter;
    private Uri imageUri;

    private Socket socket;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initMessages();
        initConnection();
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        sendImg = findViewById(R.id.send_image);
        messageRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyMessageAdapter(messageList);
        messageRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String content = inputText.getText().toString();
                if(!"".equals(content)) {
                    MyMessage message = new MyMessage(content, MyMessage.TYPE_SENT);
                    messageList.add(message);
                    adapter.notifyItemInserted(messageList.size() - 1);
                    messageRecyclerView.scrollToPosition(messageList.size() - 1);
                    inputText.setText("");
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write(content.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
        /*imageAdapter = new ImageAdapter(imageList);
        messageRecyclerView.setAdapter(imageAdapter);
        sendImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                } else {
                    imageUri = FileProvider.getUriForFile(ChatActivity.this, "com.example.cameraalbumtest.fileprovider", outputImage);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        Image image = new Image(bitmap, Image.TYPE_SENT);
                        imageList.add(image);
                        adapter.notifyItemInserted(imageList.size() - 1);
                        messageRecyclerView.scrollToPosition(imageList.size() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void initMessages() {
        MyMessage message_1 = new MyMessage("Hello guy.", MyMessage.TYPE_RECEIVED);
        messageList.add(message_1);
        MyMessage message_2 = new MyMessage("Hello. Who is that?.", MyMessage.TYPE_SENT);
        messageList.add(message_2);
        MyMessage message_3 = new MyMessage("This is Tom. Nice talking to you.", MyMessage.TYPE_RECEIVED);
        messageList.add(message_3);
    }

    private void initConnection() {
        if (socket!=null)
            return;
        final Handler thandler = handler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.199.216", 10000);
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    while ((line = bufferedReader.readLine())!= null) {
                        Message msg = new Message();
                        msg.obj = line + "\n";
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initImages() {
        /*Image image1 = new Image(, Image.TYPE_RECEIVED);
        Image image2 = new Image(R.drawable.alice_pic, Image.TYPE_RECEIVED);*/
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }
}
