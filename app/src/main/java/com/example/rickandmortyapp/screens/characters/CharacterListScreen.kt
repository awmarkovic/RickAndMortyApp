package com.example.rickandmortyapp.screens.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.rickandmortyapp.data.Character
import com.example.rickandmortyapp.data.api.RickAndMortyApiRepository
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.CharacterItem


@Composable
fun CharacterListScreen() {
    var character by remember {
        mutableStateOf<List<Character>>(emptyList())
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            character = RickAndMortyApiRepository.getAllCharacters()
        }
    }

    Column {
        Text(
            text = "Rick and Morty characters",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(0xFF0D47A1)
        )
        LazyColumn {
            items(character) { character ->
                CharacterItem(character)
            }
        }

    }
}
