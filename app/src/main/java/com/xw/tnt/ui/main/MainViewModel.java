package com.xw.tnt.ui.main;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xw.tnt.bean.User;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<User> users;

    public MutableLiveData<User> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        //tudo
        users.postValue(new User(new ObservableField<>("李四"), new ObservableInt(23)));


    }

    public String getText() {
        return "";
    }
}
