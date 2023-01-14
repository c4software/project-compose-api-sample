package com.example.exemplecomposelisteapi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.exemplecomposelisteapi.R

// Composant qui affiche la page d'accueil, constitué d'une image et d'un bouton
@Composable
fun HomeScreen(
    onPhotoClick: () -> Unit,
    onListClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.cat), modifier = Modifier.padding(16.dp), contentDescription = "Chat")
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = onListClick) {
            Text("Voir la liste")
        }
        Button(onClick = onPhotoClick) {
            Text("Voir les photos")
        }
    }
}