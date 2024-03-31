package data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import data.Duas;
import data.DuasDao;

@Database(entities = {Duas.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase{
    public abstract DuasDao duasDao();
    public static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Duas").createFromAsset("Duas.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
