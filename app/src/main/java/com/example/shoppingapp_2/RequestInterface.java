package com.example.shoppingapp_2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("getData.php")
    Call<List<ProductModel>> getProducts();
}
