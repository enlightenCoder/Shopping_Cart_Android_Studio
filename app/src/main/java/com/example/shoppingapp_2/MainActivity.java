package com.example.shoppingapp_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<CartListModel> cartlist = new ArrayList<>();
    ArrayList<ProductModel> productList = new ArrayList<>();
   // Create the ProductAdapter object to create an association
    private ProductAdapter productAdapter;
    // Recycler view
    private RecyclerView product_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Call the method that will call the api server to get some responses
        getProductResponse();
        //Bind the recyclerview
        product_recyclerview = (RecyclerView) findViewById(R.id.product_recyclerview);
        product_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getProductResponse() {

        // Create the json parser object
        Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

        // Instantiate a retrofit object to make this android app to become an api client
        Retrofit apiClient = new Retrofit.Builder()
                             .baseUrl("http://192.168.43.116:8080/recyclerview/")
                             .addConverterFactory(GsonConverterFactory.create(gson))
                             .build();

        RequestInterface requestInterface = apiClient.create(RequestInterface.class);
        Call<List<ProductModel>> apiCall = requestInterface.getProducts();
        // Assync call
        apiCall.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                Log.i("list--->", String.valueOf(response.body()));
                productList = new ArrayList<>(response.body());
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                productList = new ArrayList<>(response.body());
                productAdapter = new ProductAdapter(MainActivity.this, productList);
                product_recyclerview.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static void shoppingCart(ArrayList<CartListModel> cartlist_)
    {
        cartlist = cartlist_;

        Log.i("response", String.valueOf(cartlist));



    }



    public void goToCart(View view)
    {
        Intent i = new Intent(this, Shopping_Cart.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cartlists", cartlist);
        i.putExtras(bundle);
        startActivity(i);
        Log.i("response", String.valueOf(cartlist));

    }





}