package com.example.basicroom.phyoeko.contactDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.basicroom.phyoeko.model.Contact;

import java.util.List;
@Dao
public interface ContactDAO {

    @Insert
     void insert(Contact... contacts);

    @Update
     void update(Contact... contacts);

    @Delete
     void delete(Contact... contacts);

    @Query("SELECT * FROM contact")
     List<Contact> getContact();

    @Query("SELECT * FROM contact WHERE phoneNumber = :number")
     Contact getContactWithId(String number);
}
