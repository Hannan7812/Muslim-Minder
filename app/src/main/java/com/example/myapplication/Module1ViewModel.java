package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import java.util.List;

import data.Duas;
import data.DuasDao;

public class Module1ViewModel extends ViewModel {
    private final LiveData<List<Duas>> duaslist;

    public Module1ViewModel(Database database){
        duaslist = database.DuasDao.getAllDuas();
    }

    public LiveData<List<Duas>> getDuaslist(){
        return duaslist;
    }
}
