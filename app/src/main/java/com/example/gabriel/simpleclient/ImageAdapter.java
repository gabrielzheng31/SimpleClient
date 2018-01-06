package com.example.gabriel.simpleclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by gabriel on 18-1-6.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Image> mImageList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        ImageView leftMessage;
        ImageView rightMessage;

        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.img_left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.img_right_layout);
            leftMessage = (ImageView) view.findViewById(R.id.left_img);
            rightMessage = (ImageView) view.findViewById(R.id.right_img);
        }
    }

    public ImageAdapter(List<Image> imageList) {
        mImageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        Image image = mImageList.get(position);
        if (image.getType() == MyMessage.TYPE_RECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMessage.setImageBitmap(image.getImage());
        } else if (image.getType() == MyMessage.TYPE_SENT) {
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMessage.setImageBitmap(image.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}
