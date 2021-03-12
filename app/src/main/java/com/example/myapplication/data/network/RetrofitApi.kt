package com.example.myapplication.data.network

import com.example.myapplication.data.model.DogBreedItem
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
 //   @GET("DevTides/DogsApi/master/dogs.json")
   // fun getDogBreed(): Call<List<DogBreedItem>>

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogBreed(): Observable<List<DogBreedItem>>
}