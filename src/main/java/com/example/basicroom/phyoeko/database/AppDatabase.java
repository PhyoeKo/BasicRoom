package com.example.basicroom.phyoeko.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.basicroom.phyoeko.model.Task;
import com.example.basicroom.phyoeko.roomDAO.TaskDAO;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO taskDao();
}