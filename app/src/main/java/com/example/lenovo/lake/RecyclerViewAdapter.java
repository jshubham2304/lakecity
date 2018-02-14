package com.example.lenovo.lake;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Blog> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<Blog> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Blog blog = MainImageUploadInfoList.get(position);

        holder.postTitle.setText(blog.getPostTitle());

        holder.postDesc.setText(blog.getPostDesc());

        Glide.with(context.getApplicationContext()).load(blog.getPostImage()).into(holder.postImage);

        holder.userName.setText(blog.getUserName());

        Glide.with(context.getApplicationContext()).load(blog.getProfileImage()).into(holder.profileImage);


    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView postTitle;
        public TextView postDesc;
        public ImageView postImage;
        public ImageView profileImage;
        public TextView userName;
        public ViewHolder(View itemView) {

            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.postTitle);

            postDesc = (TextView) itemView.findViewById(R.id.postDescription);

            postImage = (ImageView) itemView.findViewById(R.id.postImage);

            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);

            userName = (TextView) itemView.findViewById(R.id.userName);
        }
    }

    public void clear()
    {
        int size = this.MainImageUploadInfoList.size();
        if (size > 0)
        {
            for (int i = 0;i<size;i++)
                delete(i);
            this.notifyItemRangeRemoved(0,size);
        }
    }

    private void delete(int i) {
        MainImageUploadInfoList.remove(i);
        notifyItemRemoved(i);
    }
}