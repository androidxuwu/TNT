package com.xw.tnt.ui;

import com.xw.tnt.R;
import com.xw.tnt.bean.User;
import com.xw.tnt.databinding.ActivityTNTBinding;
import com.xw.tnt.ui.base.BaseActivity;

import javax.inject.Inject;

public class TNTActivity extends BaseActivity<ActivityTNTBinding> {

    @Inject
    User user;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_t_n_t);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_t_n_t;
    }

    @Override
    protected void initView() {

    }
}