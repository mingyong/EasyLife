package com.example.easylife.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easylife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18530 on 2017/2/16.
 */

public class NewsFragment extends Fragment {

    private TabLayout tab_layout;
    private ViewPager viewpager;
    private List<String> mTitle;
    private List<Fragment> mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        initData();
        findView(view);
        return view;
    }
    //初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getResources().getString(R.string.newsone));
        mTitle.add(getResources().getString(R.string.newstwo));
        mTitle.add(getResources().getString(R.string.newsthree));
        mTitle.add(getResources().getString(R.string.newsfour));

        mFragment = new ArrayList<>();
        mFragment.add(new NewsOneFragment());
        mFragment.add(new NewsTwoFragment());
        mFragment.add(new NewsThreeFragment());
        mFragment.add(new NewsFourFragment());

    }

    //初始化view
    private void findView(View view) {
        tab_layout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);


        //预加载
        viewpager.setOffscreenPageLimit(mFragment.size());
        //设置适配器
        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        //绑定
        tab_layout.setupWithViewPager(viewpager);

    }
}
