package com.king.value;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Rv_item_vert extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_item_vert);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent i = getIntent();
        title = i.getStringExtra("title");
        recyclerView = findViewById(R.id.vert_item_recycler);

        Query query = db.collection(title)
                .orderBy("Rating", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Items> options = new FirestoreRecyclerOptions
                .Builder<Items>()
                .setQuery(query,Items.class)
                .setLifecycleOwner(this)
                .build();

        Rv_item_vert_Adaptor adapter = new Rv_item_vert_Adaptor(options,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
