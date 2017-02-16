package com.example.easylife;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.easylife.fragment.NewsFragment;
import com.example.easylife.fragment.PhotoFragment;
import com.example.easylife.fragment.TalkFragment;
import com.example.easylife.fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView nav_view;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    //初始化view
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置按钮图片
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //默认显示新闻页面
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new NewsFragment()).commit();

        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setCheckedItem(R.id.nav_news);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_news:
                           getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new NewsFragment()).commit();
                            actionBar.setTitle(R.string.news);
                            mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_photo:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new PhotoFragment()).commit();
                        actionBar.setTitle(R.string.photo);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_weather:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new WeatherFragment()).commit();
                        actionBar.setTitle(R.string.weather);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_talk:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new TalkFragment()).commit();
                        actionBar.setTitle(R.string.talk);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    //双击退出
    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 2000) {

                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

                firstTime = secondTime;

                return true;

            } else {

                this.finish();

            }

        }

        return super.onKeyDown(keyCode, event);

    }
}
