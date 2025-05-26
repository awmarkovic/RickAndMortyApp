package com.example.rickandmortyapp.screens.addcharacters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.data.UserCharacter

@Composable
fun UserCharacterScreen(userCharacterViewModel: UserCharacterViewModel = UserCharacterViewModel()) {

    LaunchedEffect(Unit) {
        userCharacterViewModel.setUserCharacters()
    }

    var newUserCharacterName by remember { mutableStateOf("") }
    var newUserCharacterSpecies by remember { mutableStateOf("") }
    var newUserCharacterStatus by remember { mutableStateOf("") }
    var newUserCharacterGender by remember { mutableStateOf("") }
    var newUserCharacterImage by remember { mutableStateOf("") }

    Column {
        Text(
            text = "Add your character",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(0xFF0D47A1),
            modifier = Modifier.padding(bottom = 16.dp)

        )

        TextField(
            value = newUserCharacterName,
            onValueChange = { newUserCharacterName = it },
            label = { Text("Name") },
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

        TextField(
            value = newUserCharacterSpecies,
            onValueChange = { newUserCharacterSpecies = it },
            label = { Text("Species") },
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

        TextField(
            value = newUserCharacterStatus,
            onValueChange = { newUserCharacterStatus = it },
            label = { Text("Status") },
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

        TextField(
            value = newUserCharacterGender,
            onValueChange = { newUserCharacterGender = it },
            label = { Text("Gender") },
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

        TextField(
            value = newUserCharacterImage,
            onValueChange = { newUserCharacterImage = it },
            label = { Text("Image URL") },
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


        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val newUserCharacter = UserCharacter(
                    name = newUserCharacterName,
                    species = newUserCharacterSpecies,
                    status = newUserCharacterStatus,
                    gender = newUserCharacterGender,
                    image = newUserCharacterImage
                )

                userCharacterViewModel.insertUserCharacter(newUserCharacter)

                newUserCharacterName = ""
                newUserCharacterSpecies = ""
                newUserCharacterStatus = ""
                newUserCharacterGender = ""
                newUserCharacterImage = ""
            },

            modifier = Modifier
                .size(width = 100.dp, height = 36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0D47A1),
                contentColor = Color(0xFFE3F2FD)
            )
        ) {
            Text(
                text = "Save",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Add Character",
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
