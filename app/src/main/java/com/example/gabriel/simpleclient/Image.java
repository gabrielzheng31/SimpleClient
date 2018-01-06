package com.example.gabriel.simpleclient;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by gabriel on 18-1-6.
 */

public class Image {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private Bitmap image;
    private int type;

    public Image(Bitmap image, int type) {
        this.image = image;
        this.type = type;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getType() {
        return type;
    }
}
