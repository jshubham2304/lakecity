package com.example.lenovo.lake;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

FirebaseUser user;
    DatabaseReference databaseReference;
    public SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;

    List<Blog> list = new ArrayList<>();

    RecyclerView recyclerView ;

    RecyclerView.Adapter adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("root").child("Blog");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Blog blog = dataSnapshot.getValue(Blog.class);

                    list.add(blog);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// The id of the channel.
                    String id = "my_channel_01";
// The user-visible name of the channel.
                    CharSequence name = "Its name";
// The user-visible description of the channel.
                    String description = "new Post uploaded";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mChannel = new NotificationChannel(id, name, importance);
                    }
// Configure the notification channel.

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mChannel.enableLights(true);
                        mChannel.setDescription(description);

                        mChannel.setLightColor(Color.RED);
                        mChannel.enableVibration(true);
                        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                        mNotificationManager.createNotificationChannel(mChannel);
                    }
// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
                }
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        adapter = new RecyclerViewAdapter(MainActivity.this, list);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
               adapter = new RecyclerViewAdapter(MainActivity.this, list);

                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }


        });


        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null){

                        return true;
                    }else{Intent in = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(in);
                        finish();}

                case R.id.navigation_add:
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        Intent in = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(in);

                        return true;
                    }else
                    {
                        Intent in = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(in);
                        finish();
                    }
                case R.id.navigation_contact:
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        Intent intent = new Intent(MainActivity.this, ProfileSection.class);
                        startActivity(intent);
                        finish();
                        return true;
                    }else
                    {
                        Intent in = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(in);
                        finish();
                    }
            }
            return false;
        }
    };


}
