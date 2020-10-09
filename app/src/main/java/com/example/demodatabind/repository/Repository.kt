package com.example.demodatabind.repository

import android.R
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demodatabind.api.RetrofitInstance
import com.example.demodatabind.api.RetrofitInstance1
import com.example.demodatabind.model.Data
import com.example.demodatabind.model.DataFromApi
import com.example.demodatabind.model.Post
import com.example.demodatabind.model.Post1
import com.example.demodatabind.modelForJsonPlaceHolderApi.PostData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {

    fun getPost() : MutableLiveData<Post> {
        val getResponse = MutableLiveData<Post>()

        RetrofitInstance.api.getPost()
            .enqueue(object : Callback<Post> {
                override fun onFailure(call: Call<Post>, t: Throwable) {

                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        getResponse.value = response.body()

                    }
                }
            })
        return getResponse
    }

    fun getPost1(number: Int): MutableLiveData<Post1>{
        val getResponse = MutableLiveData<Post1>()

        RetrofitInstance.api.getPost1(number)
            .enqueue(object : Callback<Post1> {
                override fun onFailure(call: Call<Post1>, t: Throwable) {
                }

                override fun onResponse(call: Call<Post1>, response: Response<Post1>) {
                    if (response.isSuccessful) {
                        getResponse.value = response.body()
                    }
                }
            })
        return getResponse
    }

    fun getCustompage(pageNo: Int): MutableLiveData<Post>{
        val getResponse = MutableLiveData<Post>()

        RetrofitInstance.api.getCustomPage(pageNo)
            .enqueue(object : Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if(response.isSuccessful){
                        getResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }

            })
        return getResponse
    }

    fun getCustomPage2(postId: Int,sort: String,order: String): MutableLiveData<List<PostData>>{
        val getResponse = MutableLiveData<List<PostData>>()

        RetrofitInstance1.api.getCustompage2(postId,sort,order)
            .enqueue(object: Callback<List<PostData>>{
                override fun onResponse(
                    call: Call<List<PostData>>,
                    response: Response<List<PostData>>
                ) {
                    if(response.isSuccessful){
                        getResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<PostData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return getResponse
    }

    fun getCustomPage1(postId: Int,options: Map<String, String>): MutableLiveData<List<PostData>>{
        val getResponse = MutableLiveData<List<PostData>>()

        RetrofitInstance1.api.getCustomPage1(postId,options)
            .enqueue(object: Callback<List<PostData>>{
                override fun onResponse(
                    call: Call<List<PostData>>,
                    response: Response<List<PostData>>
                ) {
                    if(response.isSuccessful){
                        getResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<PostData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        return getResponse
    }

    fun pushPost(post: PostData): MutableLiveData<PostData>{
        val getResponse = MutableLiveData<PostData>()

        RetrofitInstance1.api.pushPost(post)
            .enqueue(object: Callback<PostData>{
                override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                    if(response.isSuccessful){
                        getResponse.value = response.body()
                        Log.d("PostdataBody",response.body().toString())
                        Log.d("PostdataRCode",response.code().toString())
                        Log.d("PostdataMsg",response.message().toString())
                    }
                }

                override fun onFailure(call: Call<PostData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        return getResponse
    }

    fun pushPost1(userId: Int,id: Int,title: String,body: String): MutableLiveData<PostData> {
        val getResponse = MutableLiveData<PostData>()

        RetrofitInstance1.api.pushPost1(userId, id, title, body)
            .enqueue(object : Callback<PostData>{
                override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                    if(response.isSuccessful){
                        getResponse.value = response.body()
                        Log.d("ResponseCode",response.code().toString())
                        Log.d("ResponseMsg",response.message().toString())
                    }
                }

                override fun onFailure(call: Call<PostData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return getResponse
    }

    fun postData(postdata: DataFromApi): MutableLiveData<DataFromApi>{
        val getResponse = MutableLiveData<DataFromApi>()

        RetrofitInstance.api.postData(postdata)
            .enqueue(object : Callback<DataFromApi>{
                override fun onResponse(call: Call<DataFromApi>, response: Response<DataFromApi>) {
                    if(response.isSuccessful){
                        getResponse.value = response.body()
                        Log.d("REQResponse",response.body().toString())
                        Log.d("REQCode",response.code().toString())
                    }
                }

                override fun onFailure(call: Call<DataFromApi>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return getResponse
    }

}