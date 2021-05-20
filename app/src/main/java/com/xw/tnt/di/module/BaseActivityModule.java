package com.xw.tnt.di.module;

import com.xw.tnt.di.component.BaseActivitySubComponent;
import com.xw.tnt.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseActivitySubComponent.class)
public abstract class BaseActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

//    @ContributesAndroidInjector
//    abstract TNTActivity contributeTNTActivity();
}
