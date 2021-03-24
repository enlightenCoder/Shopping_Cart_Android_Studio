package com.example.shoppingapp_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Shopping_Cart extends AppCompatActivity {

    private ArrayList<CartListModel> cartList;
    private ShoppingCartAdapter cartAdapter;
    private RecyclerView recyclerView2;
    private static Button checkout;
    private static TextView total;
    public static float total_ = 0;
    public static boolean check = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping__cart);
        total = (TextView) findViewById(R.id.total);
        total.setText(String.valueOf(total_));
        //Read from intent
        //getResponse();
        Bundle bundleObject = getIntent().getExtras();
        cartList = (ArrayList<CartListModel>) bundleObject.getSerializable("cartlists");
        Log.i("check--->", String.valueOf(cartList));
        recyclerView2 = (RecyclerView) findViewById(R.id.product_recyclerview2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new ShoppingCartAdapter(Shopping_Cart.this, cartList);
        recyclerView2.setAdapter(cartAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView2);

        if(check)
        {
            RecyclerView.ViewHolder viewHolder;
        }






    }


        public void DataResponse(ArrayList<CartListModel> productList)
        {
            Log.i("message--->", String.valueOf(productList));
            Log.i("message--->", productList.get(0).getProductName());

            this.cartList =  productList;

        }


        public void getResponse()
        {
            Bundle bundleObject = getIntent().getExtras();
            cartList = (ArrayList<CartListModel>) bundleObject.getSerializable("cartlists");
            Log.i("check--->", String.valueOf(cartList.get(0).getProductName()));
            //cartAdapter = new ShoppingCartAdapter(getApplicationContext(), cartList);
            //recyclerView2.setAdapter(cartAdapter);


        }

        public static void calulateTotal(String price)
        {

            total_ = total_ + Float.parseFloat(price);
            Log.i("price", String.valueOf(total_));

            //total.setText(String.valueOf(total_));


        }











        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();

                switch(direction){

                    case ItemTouchHelper.LEFT:
                        String price = cartList.get(position).getProductPrice();
                        Log.i("pricccce : ", price);
                        total_ = total_ - Float.valueOf(price);
                        Log.i("total : ", String.valueOf(total_));
                        if(total_ >= 0)
                        {

                            //total = (TextView) findViewById(R.id.total);
                            total.setText(String.valueOf(total_));

                        }else
                         {
                             total_ = 0;
                             total.setText(String.valueOf(total_));
                             Toast.makeText(getApplicationContext(),"Cart is Empty",Toast.LENGTH_LONG).show();
                         }

                        cartList.remove(position);
                        cartAdapter.notifyItemRemoved(position);
                        break;
                    case ItemTouchHelper.RIGHT:

                        break;

                }

            }
        };

}