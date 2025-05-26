package com.example.rickandmortyapp.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rickandmortyapp.data.UserCharacter
import java.sql.SQLException

object RickAndMortyDbRepository {

    // Room Database-oppsett
    private lateinit var _appDatabase: RickAndMortyDatabase
    private val _userCharacterDao by lazy { _appDatabase.userCharacterDao() }

    // Initialiser Room-databasen
    fun initializeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            "rick_and_morty_db"
        ).build()
    }

    // Hente lagrede karakterer
    suspend fun getUserCharacters(): List<UserCharacter> {
        try {
            return _userCharacterDao.getAllUserCharacters()
        } catch (e: SQLException) {
            Log.d("Databasefeil", e.toString())
            return emptyList()
        } catch (e: Exception) {
            Log.d("Annen feil", e.toString())
            return emptyList()
        }
    }

    // Lagre ny karakter
    suspend fun insertUserCharacter(character: UserCharacter): Long {
        return try {
            _userCharacterDao.insertUserCharacter(character)
        } catch (e: SQLException) {
            Log.d("Databasefeil", e.toString())
            -1L
        }
    }

    // Slette karakter
    suspend fun deleteUserCharacter(character: UserCharacter) {
        try {
            _userCharacterDao.deleteUserCharacter(character)
        } catch (e: SQLException) {
            Log.d("Error with database", e.toString())
        }
    }
}
