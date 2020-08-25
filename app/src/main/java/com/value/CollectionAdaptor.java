package com.king.value;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CollectionAdaptor extends FirestoreRecyclerAdapter<
        Collections, CollectionAdaptor.CollectionHolder> {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    private ItemAdapter adaptor2;
    private LinearLayoutManager layoutManager;

    public CollectionAdaptor(@NonNull FirestoreRecyclerOptions<Collections> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CollectionHolder holder, int position,
                                    @NonNull final Collections model) {
        holder.textView.setText(model.getTitle());
        holder.seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Rv_item_vert.class);
                intent.putExtra("title",model.getTitle());
                context.startActivity(intent);
            }
        });


        //query for horizontal recyclerview
        Query query = db.collection(model.getTitle())
                        .orderBy("Rating", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Items> options = new FirestoreRecyclerOptions
                .Builder<Items>()
                .setQuery(query,Items.class)
                .setLifecycleOwner((LifecycleOwner) context)
                .build();

        //horizontal recyclerview adaptor
        adaptor2 = new ItemAdapter(options,context);
        holder.recyclerView2.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView2.setLayoutManager(layoutManager);
        holder.recyclerView2.setAdapter(adaptor2);
        holder.recyclerView2.setNestedScrollingEnabled(false);

    }
    @NonNull
    @Override
    public CollectionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.rv_collection,viewGroup,false);

        return new CollectionHolder(v);
    }
    class CollectionHolder extends RecyclerView.ViewHolder{
        TextView textView,seeall;
        RecyclerView recyclerView2;

        public CollectionHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_category);
            seeall = itemView.findViewById(R.id.seeall);

            // this is horizontal recyclerview
            recyclerView2=itemView.findViewById(R.id.rvHorizontal);
        }
    }
}
