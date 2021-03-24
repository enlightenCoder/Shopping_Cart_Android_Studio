package com.example.shoppingapp_2;

import android.content.Context;
import android.util.Log;
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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    private TextView total;
    private ArrayList<ProductModel> productModels = new ArrayList<>();
    private Context context;
    private ArrayList<CartListModel> productList = new ArrayList();
    public Shopping_Cart cart;
    MainActivity a = new MainActivity();

    public ProductAdapter(){}

    public ProductAdapter(Context context, ArrayList<ProductModel> productModels)
    {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create or inflate a view layout ( cardview ) for each row in the parent context
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list_item, parent, false);

        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.product_name.setText(productModels.get(position).getName());
        holder.product_price.setText(productModels.get(position).getPrice());
        holder.greenhouse_name.setText(productModels.get(position).getGreenhouse());

        Picasso.get().load(productModels.get(position).getImageUrl()).into(holder.product_image);

        final String name = productModels.get(position).getName();
        final String price = productModels.get(position).getPrice();
        final String image = productModels.get(position).getImageUrl();
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Toast.makeText(context,name,Toast.LENGTH_LONG).show();

            }
        });

        holder.cart_opn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,price,Toast.LENGTH_LONG).show();


                productList.add( new CartListModel(image,name, price));
                //cart.DataResponse(productList);
                //cart = new Shopping_Cart(productList);

                MainActivity.shoppingCart(productList);
                Shopping_Cart.calulateTotal(price);





            }
        });


    }



    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView product_image;
        private TextView product_name, product_price, greenhouse_name;
        private Button cart_opn;
        private Button checkoutBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = (ImageView) itemView.findViewById(R.id.product_image);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            greenhouse_name = (TextView) itemView.findViewById(R.id.greenhouse_name);
            cart_opn = (Button) itemView.findViewById(R.id.cart_opn);


        }
    }
}
