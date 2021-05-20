package com.xw.tnt.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public abstract class BaseActivity<VBD extends ViewDataBinding> extends AppCompatActivity implements HasFragmentInjector {

    protected VBD mBinding;

    @Inject
    DispatchingAndroidInjector<Fragment> androidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
//        View rootView = getLayoutInflater().inflate(this.getLayoutId(),null,false);
//        mBinding = DataBindingUtil.bind(rootView);
//        super.setContentView(mBinding.getRoot());
        mBinding = DataBindingUtil.setContentView(this,getLayoutId());

        initView();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return androidInjector;
    }
}
