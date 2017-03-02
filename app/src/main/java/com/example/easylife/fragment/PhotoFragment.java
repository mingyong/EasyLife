package com.example.easylife.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easylife.R;
import com.example.easylife.utils.GridSpacingItemDecoration;
import com.example.easylife.adapter.PhotoAdapter;
import com.example.easylife.entiy.PhotoData;
import com.example.easylife.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18530 on 2017/2/16.
 */

public class PhotoFragment extends Fragment {

    private RecyclerView photo_recycler;
    private List<PhotoData> mList = new ArrayList<>();
    private PhotoData data;
    private PhotoAdapter mAdapter;
    //页数
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo,null);
        findView(view);
        return view;
    }
    //初始化view
    private void findView(View view) {
        photo_recycler = (RecyclerView) view.findViewById(R.id.photo_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        photo_recycler.setLayoutManager(layoutManager);
        //设置item间距
        photo_recycler.addItemDecoration(new GridSpacingItemDecoration(2,10,false));
        //请求数据
        geData();

    }

    private void geData() {
        //执行get请求
        RxVolley.get(StaticClass.PHPTO_URL+page, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                page = page + 1;
                //Toast.makeText(getContext(),t,Toast.LENGTH_SHORT).show();
                //解析返回的Jsons数据
                paseJson(t);
            }
        });
    }

    private void paseJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0;i < jsonArray.length();i++){
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("url");
                data = new PhotoData();
                data.setPhotoUrl(url);
                mList.add(data);
            }
            mAdapter = new PhotoAdapter(mList);
            photo_recycler.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
