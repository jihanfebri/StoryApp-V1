package com.example.story.app.data.remote.respon.login

import com.google.gson.annotations.SerializedName

data class ResSignin(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("loginResult")
    val result: Result

)