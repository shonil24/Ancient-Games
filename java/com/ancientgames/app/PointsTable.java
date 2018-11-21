package com.ancientgames.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PointsTable extends AppCompatActivity {

    ListView listView;

    //obj is super of all clas ...so the object model we et down..
    //so the object that we want to get is of teams type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_table);

        listView =(ListView) findViewById(R.id.listView);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ancientgames-8775c.firebaseio.com/TeamPoints");


        FirebaseListAdapter<String> firebaseListAdapter= new FirebaseListAdapter<String>(PointsTable.this, String.class,
                android.R.layout.simple_list_item_1, databaseReference) {

            @Override
            //model is our teamname and points
            //positio  is 0,1 etc
            protected void populateView(View view, String model, int position) {
                //in place of teams it was object as object is superclass teams is subclass we w get teams type data from object
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(model);//this is displayed to text 1 of list..
                //view means view of list view//set team name to list text 1
            }
        };

        //connect listview to listadapter
        listView.setAdapter(firebaseListAdapter);

    }
}