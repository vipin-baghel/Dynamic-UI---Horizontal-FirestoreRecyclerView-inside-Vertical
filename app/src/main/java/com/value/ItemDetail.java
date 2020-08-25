package com.king.value;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name,price,item_desc;
    String name_product,price_product,desc,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imageView = findViewById(R.id.image_view);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        item_desc = findViewById(R.id.item_desc);

        Intent i = getIntent();
        name_product= i.getStringExtra("name");
        price_product= i.getStringExtra("price");
        url = i.getStringExtra("url");

        Picasso.get().load(url).fit().into(imageView);
        name.setText(name_product);
        price.setText(price_product);

    }
}
