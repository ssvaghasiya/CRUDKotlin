package com.example.demodatabind.api

import com.example.demodatabind.model.DataFromApi
import com.example.demodatabind.model.Post
import com.example.demodatabind.model.Post1
import com.example.demodatabind.modelForJsonPlaceHolderApi.PostData
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    // https://reqres.in/api/users?page=2
    @GET("/api/users?page=2")
    fun getPost() : Call<Post>

    // https://reqres.in/api/users/4
    @GET("/api/users/{postnumber}")
    fun getPost1(
        @Path("postnumber") number: Int
    ) : Call<Post1>

    // https://reqres.in/api/users?page=5(use any page number from edittext
    @GET("/api/users")
    fun getCustomPage(
        @Query("page") pageNo: Int
    ): Call<Post>

//    @FormUrlEncoded
    @POST("/api/users")
    fun postData(
        @Body data: DataFromApi
//        @Field("data") data: DataFromApi
    ): Call<DataFromApi>

    // https://jsonplaceholder.typicode.com/comments?postId=2&_sort=id&_order=desc
    @GET("/comments")
    fun getCustompage2(
        @Query("postId") postId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Call<List<PostData>>

    // https://jsonplaceholder.typicode.com/comments?postId=2&_sort=id&_order=desc using queryMap
    @GET("/comments")
    fun getCustomPage1(
        @Query("postId") postId: Int,
        @QueryMap options: Map<String, String>
    ) : Call<List<PostData>>

    @POST("/posts")
    fun pushPost(
        @Body post: PostData
    ): Call<PostData>

    @FormUrlEncoded
    @POST("/posts")
    fun pushPost1(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<PostData>
}