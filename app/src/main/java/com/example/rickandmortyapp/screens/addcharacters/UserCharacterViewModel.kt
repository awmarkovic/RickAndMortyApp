package com.example.rickandmortyapp.screens.addcharacters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.room.RickAndMortyDbRepository
import com.example.rickandmortyapp.data.UserCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserCharacterViewModel : ViewModel() {
    private val _userCharacters = MutableStateFlow<List<UserCharacter>>(emptyList())
    val userCharacters = _userCharacters.asStateFlow()

    fun setUserCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _userCharacters.value = RickAndMortyDbRepository.getUserCharacters()
        }
    }

    fun insertUserCharacter(character: UserCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            val newCharacterId = RickAndMortyDbRepository.insertUserCharacter(character)
            if (newCharacterId != -1L) {
                val newCharacter = character.copy(id = newCharacterId.toInt())
                _userCharacters.value += newCharacter
            } else {
                _userCharacters.value = _userCharacters.value
                println("Failed to insert character: $character")
            }
        }
    }
}