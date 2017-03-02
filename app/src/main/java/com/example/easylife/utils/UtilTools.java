package com.example.easylife.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by 18530 on 2017/2/17.
 */

public class UtilTools {
    //获取屏幕宽度
    public static int getWindowWidth(Context mContext){
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        return manager.getDefaultDisplay().getWidth();
    }

    //获取屏幕宽度
    public static int getWindowheight(Context mContext){
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        return manager.getDefaultDisplay().getHeight();
    }

}
