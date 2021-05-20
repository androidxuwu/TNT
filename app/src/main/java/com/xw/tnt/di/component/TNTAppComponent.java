package com.xw.tnt.di.component;

import com.xw.tnt.TNTApplication;
import com.xw.tnt.di.module.BaseActivityModule;
import com.xw.tnt.di.module.UserModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class, BaseActivityModule.class, UserModule.class})
public interface TNTAppComponent
//        extends AndroidInjector<TNTApplication>
{
    void inject(TNTApplication tntApplication);
}
