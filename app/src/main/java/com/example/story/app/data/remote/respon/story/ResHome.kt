package com.example.story.app.data.remote.respon.story

import com.google.gson.annotations.SerializedName

data class ResponseStory(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("listStory")
    val listStory: List<ListStory>,
)
