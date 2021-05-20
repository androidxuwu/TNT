package com.xw.tnt.di.module;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.xw.tnt.bean.User;
import com.xw.tnt.di.DataScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

//    @DataScope
    @Provides
    public User provideUser() {
        return new User(new ObservableField<>("xw"), new ObservableInt(25));
    }
}
