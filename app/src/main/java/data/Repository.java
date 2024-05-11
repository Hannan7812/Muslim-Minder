package data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class Repository {
    private final DuasDao duasDao;
    private LiveData<List<Dua>> allDuas;

    public Repository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        duasDao = db.duasDao();
        allDuas = duasDao.getAllDuas();
    }

    public LiveData<List<Dua>> getAll(){
        return allDuas;
    }

    public LiveData<List<Dua>> getDuasById(int id){
        return duasDao.getDuasByFeelingId(id);
    }

    public LiveData<List<Dua>> getFavoriteDuas(){
        return duasDao.getFavoriteDuas();
    }

    public void addFavoriteAndUpdateDua(int id) {
        Executors.newSingleThreadExecutor().execute(() -> {
            duasDao.addFavorite(id);
        });
    }

    public LiveData<Dua> getUpdatedDua(int id) {
        return duasDao.getDuaById(id);
    }

    public void removeFavoriteAndUpdateDua(int id){
        Executors.newSingleThreadExecutor().execute(() -> {
            duasDao.removeFavorite(id);
        });
    }
}
