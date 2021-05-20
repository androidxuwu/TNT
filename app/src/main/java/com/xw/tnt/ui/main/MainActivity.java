package com.xw.tnt.ui.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.xw.tnt.R;
import com.xw.tnt.bean.User;
import com.xw.tnt.databinding.ActivityMainBinding;
import com.xw.tnt.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static java.security.AccessController.getContext;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Inject
    User user;
    //    private TextView tv_content;
//    private Button btn_start;
    private MutableLiveData<Integer> numberLiveData;
    private MainViewModel mainViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mainViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);
        mBinding.setUser(user);
        mBinding.setVm(mainViewModel);

        mainViewModel.getUsers().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User users) {
                mBinding.btnStart.setText("姓名：" + users.getName().get() + "\n"
                        + "年龄:" + users.getAge().get());
            }
        });

        numberLiveData = new MutableLiveData<>();

        mBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, "message")
                        //标题
                        .setContentTitle("收到一条聊天消息")
                        //内容
                        .setContentText("今天中午吃什么")
                        //设置发送的时间
                        .setWhen(System.currentTimeMillis())
                        //设置小图标（通知栏没有下拉的图标）
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //设置右侧大图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.ic_launcher_background))
                        //设置点击通知后自动删除通知
                        .setAutoCancel(true)
                        .setContentIntent(pi)
                        .build();
                notificationManager.notify(1, notification);
                //似乎只有设置了setContentIntent，AutoCancel才能生效


//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        int number = 0;
//                        while (number < 5) {
//                            try {
//                                Thread.sleep(2000);
//                            } catch (Exception e) {
//                            }
//                            number++;
//                            numberLiveData.postValue(number);
//                        }
//                    }
//                }).start();
            }
        });

        numberLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mBinding.tvContent.setText("姓名：" + user.getName() + "\n"
                        + "年龄:" + user.getAge() + "\n"
                        + "时间:" + integer);
                Log.d("massage:", "onchanged:" + integer);
            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        AndroidInjection.inject(this);
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
////        tv_content = findViewById(R.id.tv_content);
////        btn_start = findViewById(R.id.btn_start);
////        tv_content.setText("姓名：" + user.getName() + "\n" + "年龄:" + user.getAge());
//
//    }
}