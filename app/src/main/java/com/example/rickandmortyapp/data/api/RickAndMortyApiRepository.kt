package com.example.rickandmortyapp.data.api
import android.util.Log
import com.example.rickandmortyapp.data.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyApiRepository {
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _rickAndMortyService = _retrofit.create(RickAndMortyService::class.java)

    suspend fun getAllCharacters(): List<Character> {
        try {
            val response = _rickAndMortyService.getAllCharacters()
            return if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.d("API Error", e.toString())
            return emptyList()
        }
    }
}