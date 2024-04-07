package com.example.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import java.util.List;

import data.AppDatabase;
import data.Duas;
import data.DuasDao;
import data.Repository;

public class Module1ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Duas>> allDuas;
    public Module1ViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allDuas = repository.getAll();
    }

    LiveData<List<Duas>> getAllDuas(){
        return allDuas;
    }
    LiveData<List<Duas>> getDuasById(int id){
        return repository.getDuasById(id);
    }
}
