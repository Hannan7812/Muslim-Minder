package data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private final DuasDao duasDao;
    private LiveData<List<Duas>> allDuas;

    public Repository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        duasDao = db.duasDao();
        allDuas = duasDao.getAllDuas();
    }

    public LiveData<List<Duas>> getAll(){
        return allDuas;
    }

    public LiveData<List<Duas>> getDuasById(int id){
        return duasDao.getDuasById(id);
    }
}
