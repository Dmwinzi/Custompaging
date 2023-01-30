package com.example.custompaged.Data.Remote

import androidx.compose.runtime.Composable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
   @GET("/fetchall")
   suspend fun users (@Query("page")Page : Int)  : Response<Usersrespo>


   companion object{
      operator fun  invoke() : Api{
         return Retrofit.Builder()
            .baseUrl("http://192.168.0.105:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
      }
   }




}