package com.example.easylife.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.easylife.R;
import com.example.easylife.entiy.PhotoData;
import com.example.easylife.utils.UtilTools;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 18530 on 2017/2/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context mContext;
    private List<PhotoData> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_photo;
        public ViewHolder(View view) {
            super(view);
            item_photo = (ImageView) view.findViewById(R.id.item_photo);
        }
    }

    public PhotoAdapter(List<PhotoData> mList){
        this.mList = mList;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        PhotoData data = mList.get(position);
        Picasso.with(mContext).load(data.getPhotoUrl()).resize(UtilTools.getWindowWidth(mContext)/2,UtilTools.getWindowheight(mContext)*2/5).into(holder.item_photo);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
