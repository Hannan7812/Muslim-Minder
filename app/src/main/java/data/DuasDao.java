package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DuasDao {
    @Query("SELECT * FROM Duas")
    LiveData<List<Duas>> getAllDuas();  // Return LiveData instead of List

    @Query("SELECT * FROM Duas WHERE id = :id")
    LiveData<List<Duas>> getDuasById(int id);
}
