package com.example.rickandmortyapp.screens.filtercharacters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.CharacterItem
import com.example.rickandmortyapp.data.Character
import com.example.rickandmortyapp.data.api.RickAndMortyApiRepository
import com.example.rickandmortyapp.data.room.RickAndMortyDbRepository
import kotlinx.coroutines.launch

@Composable
fun FilterCharacterScreen() {

    var filter by remember {
        mutableStateOf("")
    }

    var allCharacters by remember {
        mutableStateOf<List<Character>>(emptyList())
    }

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            allCharacters = RickAndMortyDbRepository.getUserCharacters().map {
                Character (
                    id = it.id,
                    name = it.name,
                    species = it.species,
                    status = it.status,
                    image = it.image,
                    gender = it.gender
                )
            } + RickAndMortyApiRepository.getAllCharacters()
        }
    }

    Column {
        Text(
            text = "Filter characters",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(0xFF0D47A1),
            modifier = Modifier.padding(bottom = 2.dp)
        )

        Text(text = "Here you can filter characters after their gender, species, status or name! For example: 'Alive', 'Human', 'Rick', 'Male'",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            color = Color(0xFF0D47A1),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = filter ,
            onValueChange = {filter = it},
            label = { Text("Enter here") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color(0xFFE3F2FD)),
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE3F2FD),
                unfocusedContainerColor = Color(0xFFE3F2FD),
            )
        )

        LazyColumn {
            items(
                allCharacters.filter { character ->
                    character.gender.equals(filter, ignoreCase = true) ||
                            character.name.contains(filter, ignoreCase = true) ||
                            character.species.contains(filter, ignoreCase = true) ||
                            character.status.equals(filter, ignoreCase = true) ||
                            filter.isBlank()
                }
            ) { character ->
                CharacterItem(character)
            }
        }

    }



}