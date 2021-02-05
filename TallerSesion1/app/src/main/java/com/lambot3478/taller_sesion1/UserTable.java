package com.lambot3478.taller_sesion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserTable extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_table);

        listViewUsers = findViewById(R.id.listViewUsers);

        databaseHelper = new DatabaseHelper(this);

        populateListView();

    }

    private void populateListView()
    {

        Cursor data = databaseHelper.getData();

        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext())
            listData.add(data.getString(1));

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listViewUsers.setAdapter(adapter);

        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String name = parent.getItemAtPosition(position).toString();

                Cursor data = databaseHelper.getUserData(name);

                int userID = -1;
                while(data.moveToNext())
                    userID = data.getInt(0);

                if(userID > -1)
                {

                    Intent profileIntent = new Intent(UserTable.this, ProfileActivity.class);
                    profileIntent.putExtra("id", userID);
                    startActivity(profileIntent);

                }

            }
        });

    }

}