package com.example.shoppingapp_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private ArrayList<CartListModel> cartList;
    Context context;

    public ShoppingCartAdapter(Context context, ArrayList<CartListModel> cartList)
    {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list,parent,false);

        return new ShoppingCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.product_name.setText(cartList.get(position).getProductName());
        holder.product_price.setText(cartList.get(position).getProductPrice());

        Picasso.get().load(cartList.get(position).getImageUrl()).into(holder.image);

        final String name = cartList.get(position).getProductName();
        final String price = cartList.get(position).getProductPrice();
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Toast.makeText(context,name,Toast.LENGTH_LONG).show();




            }
        });




    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView product_name, product_price;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.cart_img);
            product_name = (TextView) itemView.findViewById(R.id.cart_name);
            product_price = (TextView) itemView.findViewById(R.id.cart_price);


        }
    }
}
