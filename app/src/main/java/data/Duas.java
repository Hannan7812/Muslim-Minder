package data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Duas {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NotNull
    public String text;

    @Override
    public String toString(){
        return text;
    }
    public String getId() {
        return String.valueOf(id);
    }
}
