package com.ancientgames.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Players extends AppCompatActivity {

    EditText search_player;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    //5 array list for e.g 1 for Name and each others for Age,rank etc
    ArrayList<String> Namelist;
   // ArrayList<String> DOBlist;
    ArrayList<String> Agelist;
    //ArrayList<String> Profilelist;
    ArrayList<String> Ranklist;

    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        search_player = findViewById(R.id.search_player);
        recyclerView = findViewById(R.id.player_list);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //layout manager setting up for recycler view
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        Namelist = new ArrayList<>();
     //   DOBlist = new ArrayList<>();
        Agelist = new ArrayList<>();
       // Profilelist = new ArrayList<>();
        Ranklist = new ArrayList<>();

        //text change listerner for search text changed
        search_player.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    Namelist.clear();
                  //  DOBlist.clear();
                    Agelist.clear();
                    Ranklist.clear();
                    recyclerView.removeAllViews();
                    //clear list
                }
            }
        });
    }

    private void setAdapter(final String searchString) {


        databaseReference.child("Players").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Namelist.clear();
            //    DOBlist.clear();
                Agelist.clear();
                Ranklist.clear();
                recyclerView.removeAllViews();
                //clear list

                int counter = 0;

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String Name = snapshot.child("Name").getValue(String.class);
                  //  String DOB = snapshot.child("DOB").getValue(String.class);
                    String Age = snapshot.child("Age").getValue(String.class);
                  //  String Profile = snapshot.child("Profile").getValue(String.class);
                    String Rank = snapshot.child("Rank").getValue(String.class);

                    if(Name.toLowerCase().contains(searchString.toLowerCase())) {
                        Namelist.add(Name);
                     //   DOBlist.add(DOB);
                        Agelist.add(Age);
                    //    Profilelist.add(Profile);
                        Ranklist.add(Rank);
                        counter++;
                    } else if(Age.toLowerCase().contains(searchString.toLowerCase())) {
                        Namelist.add(Name);
                     //   DOBlist.add(DOB);
                        Agelist.add(Age);
                     //   Profilelist.add(Profile);
                        Ranklist.add(Rank);
                        counter++;
                    } else if(Rank.toLowerCase().contains(searchString.toLowerCase())) {
                        Namelist.add(Name);
                      //  DOBlist.add(DOB);
                        Agelist.add(Age);
                    //    Profilelist.add(Profile);
                        Ranklist.add(Rank);
                        counter++;
                    }
                  //  else if(DOB.toLowerCase().contains(searchString.toLowerCase())) {
                 //       Namelist.add(Name);
                        //   DOBlist.add(DOB);
                  //      Agelist.add(Age);
                        //   Profilelist.add(Profile);
                  //      Ranklist.add(Rank);
                  //     counter++;  ,DOBlist

                    if(counter == 5) {
                        //max 5 results
                        break;
                    }
                }
                searchAdapter = new SearchAdapter(Players.this, Namelist, Agelist, Ranklist);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
