package com.example.server_api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.create
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

object NetworkConfig {
    fun getInterceptor() : OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.254.248/server_api/index.php/ServerApi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(StaffService::class.java)
}

interface StaffService{
    @FormUrlEncoded
    @POST("addStaff")
    fun addStaff(@Field("name")name: String,
                 @Field("hp")hp:String,
                 @Field("alamat")alamat:String) : Call<ResultStatus>

    @GET("getDataStaff")
    fun getData(): Call<ResultStaff>

    @FormUrlEncoded
    @POST("updateStaff")
    fun updateStaff(@Field("id")id:String,
                    @Field("name")name:String,
                    @Field("hp")hp:String,
                    @Field("alamat")alamat:String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("deleteStaff")
    fun deleteStaff(@Field("id")id:String?) : Call<ResultStatus>
}