package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DuasDao {
    @Query("SELECT * FROM Dua")
    LiveData<List<Dua>> getAllDuas();  // Return LiveData instead of List

    @Query("SELECT * FROM Dua WHERE feeling_id = :id")
    LiveData<List<Dua>> getDuasByFeelingId(int id);

    @Query("SELECT * FROM Dua WHERE favorite = 1")
    LiveData<List<Dua>> getFavoriteDuas();

    @Query("UPDATE Dua SET favorite = 1 WHERE id = :id")
    void addFavorite(int id);

    @Query("UPDATE Dua SET favorite = 0 WHERE id = :id")
    void removeFavorite(int id);

    @Query("SELECT * FROM Dua WHERE id = :id")
    LiveData<Dua> getDuaById(int id);
}
