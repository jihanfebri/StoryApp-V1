package com.example.story.app.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.story.app.data.DataRepository
import com.example.story.app.data.remote.respon.story.ResponseStory
import com.example.story.app.util.NetworkResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    private val _responseListStory = MutableLiveData<NetworkResult<ResponseStory>>()
    private var job: Job? = null

    fun fetchListStory(auth: String) {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getStories(auth).collectLatest {
                _responseListStory.value = it
            }
        }
    }

    val responseListStory: LiveData<NetworkResult<ResponseStory>> = _responseListStory

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
