package com.example.rickandmortyapp.screens.showcharacters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.rickandmortyapp.data.room.RickAndMortyDbRepository
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.CharacterItem
import com.example.rickandmortyapp.data.Character
import com.example.rickandmortyapp.data.UserCharacter


@Composable
fun ShowCharactersScreen() {
    var character by remember {
        mutableStateOf<List<Character>>(emptyList())
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            character = RickAndMortyDbRepository.getUserCharacters().map {
                Character(
                    id = it.id,
                    name = it.name,
                    species = it.species,
                    status = it.status,
                    image = it.image,
                    gender = it.gender
                )
            }
        }
    }

    Column {
        Text(
            text = "Your characters",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(0xFF0D47A1)
        )
        LazyColumn {
            items(character) { characterItem ->
                Column {
                    CharacterItem(characterItem)

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val userCharacter = UserCharacter(
                                    id = characterItem.id,
                                    name = characterItem.name,
                                    species = characterItem.species,
                                    status = characterItem.status,
                                    gender = characterItem.gender,
                                    image = characterItem.image
                                )
                                RickAndMortyDbRepository.deleteUserCharacter(userCharacter)

                                character = character.filter { it.id != characterItem.id }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Delete",
                            modifier = Modifier.padding(start = 2.dp)
                        )
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete Character",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}