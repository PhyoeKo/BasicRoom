package com.example.basicroom.phyoeko;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.basicroom.phyoeko.contactDAO.ContactDAO;
import com.example.basicroom.phyoeko.model.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
    //first branch
}