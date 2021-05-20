package com.xw.tnt;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.xw.tnt.di.component.DaggerTNTAppComponent;
import com.xw.tnt.di.component.TNTAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class TNTApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    private TNTAppComponent tntAppComponent;
    String channelId = "message";
    String channelName = "消息提示";
    int importance = NotificationManager.IMPORTANCE_HIGH;

    @Override
    public void onCreate() {
        super.onCreate();
        tntAppComponent = DaggerTNTAppComponent.builder().build();
        tntAppComponent.inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//安卓8.0
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "34c747e195", true);
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public TNTAppComponent getTntAppComponent() {
        return tntAppComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return androidInjector;
//    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);//分包处理

        // 安装tinker
        Beta.installTinker();
    }
}
