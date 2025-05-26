package com.example.rickandmortyapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserCharacterDao {
    @Query("SELECT * FROM user_characters")
    suspend fun getAllUserCharacters(): List<UserCharacter>

    @Insert
    suspend fun insertUserCharacter(character: UserCharacter): Long

    @Delete
    suspend fun deleteUserCharacter(character: UserCharacter)
}
