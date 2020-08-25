package com.king.value;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Fragment_home extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public CollectionAdaptor adaptor;
    private RecyclerView recyclerView;
    private Context context;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getContext();

        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);

        editText = rootView.findViewById(R.id.search);

        recyclerView = rootView.findViewById(R.id.recycler_view_collection);//this is vertical recyclerview

        setUpRecyclerView();

        return rootView;
    }

    private void setUpRecyclerView(){
        //query for vertical recyclerview
        Query query = db.collection("All Collections");

        FirestoreRecyclerOptions<Collections> options = new FirestoreRecyclerOptions
                .Builder<Collections>()
                .setQuery(query,Collections.class)
                .build();

        // vertical recyclerview adaptor
        adaptor = new CollectionAdaptor(options,context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptor);
    }

    @Override
    public void onStart() {
        super.onStart();
        adaptor.startListening();


    }

    @Override
    public void onStop() {
        super.onStop();
        adaptor.stopListening();

    }

}
