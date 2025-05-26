package com.example.rickandmortyapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapp.screens.characters.CharacterListScreen
import com.example.rickandmortyapp.screens.addcharacters.UserCharacterScreen
import com.example.rickandmortyapp.screens.filtercharacters.FilterCharacterScreen
import com.example.rickandmortyapp.screens.showcharacters.ShowCharactersScreen
import kotlinx.serialization.Serializable

@Serializable
private object NavCharacterList

@Serializable
private object NavAddCharacter

@Serializable
private object NavShowCharacters

@Serializable
private object NavFilterCharacters

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var chosenScreen by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFBBDEFB),
                tonalElevation = 4.dp
            ) {
                NavigationBarItem(
                    selected = chosenScreen == 0,
                    onClick = {
                        chosenScreen = 0
                        navController.navigate(NavCharacterList)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = null,
                            tint = if (chosenScreen == 0) Color(0xFF0D47A1) else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Characters",
                            color = if (chosenScreen == 0) Color(0xFF0D47A1) else Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = if (chosenScreen == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
                NavigationBarItem(
                    selected = chosenScreen == 1,
                    onClick = {
                        chosenScreen = 1
                        navController.navigate(NavAddCharacter)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = null,
                            tint = if (chosenScreen == 1) Color(0xFF0D47A1) else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Add your character",
                            color = if (chosenScreen == 1) Color(0xFF0D47A1) else Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = if (chosenScreen == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
                NavigationBarItem(
                    selected = chosenScreen == 2,
                    onClick = {
                        chosenScreen = 2
                        navController.navigate(NavShowCharacters)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = if (chosenScreen == 2) Color(0xFF0D47A1) else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Your characters",
                            color = if (chosenScreen == 2) Color(0xFF0D47A1) else Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = if (chosenScreen == 2) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
                NavigationBarItem(
                    selected = chosenScreen == 3,
                    onClick = {
                        chosenScreen = 3
                        navController.navigate(NavFilterCharacters)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = if (chosenScreen == 3) Color(0xFF0D47A1) else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = "Filter characters",
                            color = if (chosenScreen == 3) Color(0xFF0D47A1) else Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = if (chosenScreen == 3) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavCharacterList
            ) {
                composable<NavCharacterList> {
                    CharacterListScreen()
                }
                composable<NavAddCharacter> {
                    UserCharacterScreen()
                }
                composable<NavShowCharacters> {
                    ShowCharactersScreen()
                }
                composable<NavFilterCharacters> {
                    FilterCharacterScreen()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NavigationPreview() {
    AppNavigation()
}
