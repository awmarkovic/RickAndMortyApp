package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.data.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("character/?format=json")
    suspend fun getAllCharacters(): Response<CharacterList>
}