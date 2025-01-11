package com.example.story.app.data

import com.example.story.app.data.remote.api.ApiService
import com.example.story.app.data.remote.respon.login.ResSignin
import com.example.story.app.data.remote.respon.register.ResSignup
import com.example.story.app.data.remote.respon.story.ResponseStory
import com.example.story.app.data.remote.respon.upload.ResStory
import com.example.story.app.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class DataRepository constructor(private val apiService: ApiService) {

    suspend fun getStories(auth: String): Flow<NetworkResult<ResponseStory>> =
        flow {
            try {
                val generateToken = generateAuthorization(auth)
                val response = apiService.getStories(generateToken)
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                val errorBody = (e as? HttpException)?.response()?.errorBody()?.string()
                emit(NetworkResult.Error(errorBody ?: "Unknown error occurred"))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun uploadStory(auth: String, description: String, file: File): Flow<NetworkResult<ResStory>> =
        flow {
            try {
                val generateToken = generateAuthorization(auth)
                val desc = description.toRequestBody("text/plain".toMediaType())
                val requestImageFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
                val imageMultipart = MultipartBody.Part.createFormData(
                    "photo",
                    file.name,
                    requestImageFile
                )
                val response = apiService.uploadStory(generateToken, imageMultipart, desc)
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                val errorBody = (e as? HttpException)?.response()?.errorBody()?.string()
                emit(NetworkResult.Error(errorBody ?: "Unknown error occurred"))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun register(name: String, email: String, password: String): Flow<NetworkResult<ResSignup>> =
        flow {
            try {
                val response = apiService.register(name, email, password)
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                val errorBody = e.toString()
                emit(NetworkResult.Error(errorBody ?: "Unknown error occurred"))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun login(email: String, password: String): Flow<NetworkResult<ResSignin>> =
        flow {
            try {
                val response = apiService.login(email, password)
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                val errorBody = e.toString()
                emit(NetworkResult.Error(errorBody ?: "Unknown error occurred"))
            }
        }.flowOn(Dispatchers.IO)

    private fun generateAuthorization(token: String): String {
        return "Bearer $token"
    }
}
