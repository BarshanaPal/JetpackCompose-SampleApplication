package com.example.myapplication.data.viewmodel

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.DogBreedItem
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.view.DogCard
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class DogListViewModel :ViewModel() {
    var dogsList: MutableState<List<DogBreedItem>> = mutableStateOf(ArrayList())

    @Composable
    fun Dogs(){
        //using Retrofit Library
      /*  RetrofitClient.getRetrofitApi().getDogBreed()
            .enqueue(object : Callback<List<DogBreedItem>>{
                override fun onResponse(
                    call: Call<List<DogBreedItem>>,
                    response: Response<List<DogBreedItem>>
                ) {
                    dogsList.value= response.body()!!
                }

                override fun onFailure(call: Call<List<DogBreedItem>>, t: Throwable) {
                    Log.d("Tag",t.localizedMessage)
                }

            })*/
        //Using Rxjava
        RetrofitClient.getRetrofitApi().getDogBreed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<DogBreedItem>>{
                    override fun onSubscribe(d: Disposable) {
                        var disposable=d
                    }

                    override fun onNext(t: List<DogBreedItem>) {
                        dogsList.value=t
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Tag",e.localizedMessage)
                    }

                    override fun onComplete() {
                        Log.e("Tag","Complete")
                    }

                })
        LazyColumn{
            itemsIndexed(
                    items=dogsList.value
            ){index, dogBreedItem ->
                DogCard(dogBreedItem = dogBreedItem, onClick = {})
            }
        }
    }
}
