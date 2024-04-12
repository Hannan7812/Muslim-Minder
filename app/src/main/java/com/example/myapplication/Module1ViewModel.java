package com.example.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import data.Dua;
import data.Repository;

public class Module1ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Dua>> allDuas;
    public Module1ViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allDuas = repository.getAll();
    }

    LiveData<List<Dua>> getAllDuas(){
        return allDuas;
    }
    LiveData<List<Dua>> getDuasById(int id){
        return repository.getDuasById(id);
    }

    LiveData<List<Dua>> getFavoriteDuas(){
        return repository.getFavoriteDuas();
    }

    public void addFavoriteAndUpdateDua(int id) {
        repository.addFavoriteAndUpdateDua(id);
    }

    public LiveData<Dua> getUpdatedDua(int id) {
        return repository.getUpdatedDua(id);
    }

    public void removeFavoriteAndUpdateDua(int id){
        repository.removeFavoriteAndUpdateDua(id);
    }
}
