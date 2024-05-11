package data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity
public class Dua {
    @Nullable
    public String reference;

    @Nullable
        public Integer feeling_id;

    @Nullable
    public Integer catalog_id;

    @Nullable
    public Integer favorite;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NotNull
    public String arabic;

    @NotNull
    public String english;

    @Override
    public String toString(){
        return arabic + " " + english + " " + reference + " " + feeling_id + " " + catalog_id + " " + favorite;
    }
    public String getId() {
        return String.valueOf(id);
    }
}
