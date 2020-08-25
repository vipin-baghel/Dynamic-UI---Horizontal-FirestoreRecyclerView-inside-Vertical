package com.king.value;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ItemAdapter extends FirestoreRecyclerAdapter<Items,ItemAdapter.ItemHolder>{

    Context context;

    public ItemAdapter(@NonNull FirestoreRecyclerOptions<Items> options,Context context ) {
        super(options);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull ItemHolder holder, int position,
                                    @NonNull final Items model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        Picasso.get().load(model.getImgurl()).fit().into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ItemDetail.class);
                i.putExtra("name",model.getName());
                i.putExtra("price",model.getPrice());
                i.putExtra("url",model.getImgurl());
                context.startActivity(i);
            }
        });


    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_hori,
                viewGroup,false);
        return new ItemHolder(v);
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView imageView;
        CardView cardView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            imageView=itemView.findViewById(R.id.image_view);
            price = itemView.findViewById(R.id.price);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
