package ru.grom.tasklist.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "timeStamp")
    public long timeStamp;

    @ColumnInfo(name = "done")
    public boolean done;

    public Note() {
    }

    protected Note(Parcel in) {
        uid = in.readInt();
        text = in.readString();
        timeStamp = in.readLong();
        done = in.readByte() != 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return uid == note.uid && timeStamp == note.timeStamp && done == note.done && Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, text, timeStamp, done);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeString(text);
        parcel.writeLong(timeStamp);
        parcel.writeByte((byte) (done ? 1 : 0));
    }
}
