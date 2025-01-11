package com.example.story.app.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.story.app.data.DataRepository
import com.example.story.app.view.UploadStoryViewModel
import com.example.story.app.view.LoginAndRegisterViewModel
import com.example.story.app.view.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val dataRepository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginAndRegisterViewModel::class.java) -> {
                LoginAndRegisterViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(UploadStoryViewModel::class.java) -> {
                UploadStoryViewModel(dataRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Class ViewModel not Implement")
            }
        }
    }

}