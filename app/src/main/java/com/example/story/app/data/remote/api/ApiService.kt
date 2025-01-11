package com.example.story.app.data.remote.api

import com.example.story.app.data.remote.respon.login.ResSignin
import com.example.story.app.data.remote.respon.register.ResSignup
import com.example.story.app.data.remote.respon.story.ResponseStory
import com.example.story.app.data.remote.respon.upload.ResStory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @POST("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : ResSignup

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : ResSignin

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") auth: String
    ) : ResponseStory

    @Multipart
    @POST("stories")
    suspend fun uploadStory(
        @Header("Authorization") auth: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ) : ResStory


}