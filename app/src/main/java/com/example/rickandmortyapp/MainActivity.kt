package com.example.rickandmortyapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rickandmortyapp.data.room.RickAndMortyDbRepository
import com.example.rickandmortyapp.navigation.AppNavigation
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RickAndMortyDbRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                AppNavigation()
            }
        }
    }
}


