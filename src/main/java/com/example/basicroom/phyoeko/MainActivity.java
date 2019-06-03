package com.example.basicroom.phyoeko;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basicroom.ContactAdapter;
import com.example.basicroom.phyoeko.contactDAO.ContactDAO;
import com.example.basicroom.phyoeko.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase database;
    ContactDAO contactDAO;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    List<Contact> contactList;

    EditText et_firstName,et_LastName,et_phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView  =findViewById(R.id.recycler_view);
        et_firstName = findViewById(R.id.et_firstName);
        et_LastName = findViewById(R.id.et_lastName);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        database = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
         contactDAO = database.getContactDAO();

       contactList = contactDAO.getContact();

        contactAdapter = new ContactAdapter(contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();



    }

    public void onClick(View view){

        //Data insert
        Contact contact = new Contact();
        contact.setFistName(et_firstName.getText().toString());
        contact.setLastName(et_LastName.getText().toString());
        contact.setPhoneNumber(et_phoneNumber.getText().toString());
        contactDAO.insert(contact);



        contactList = contactDAO.getContact();
//        Log.i("SIZE", contactList.size()+"");
        contactAdapter = new ContactAdapter(contactList);
       recyclerView.setAdapter(contactAdapter);
       contactAdapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_LONG).show();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
