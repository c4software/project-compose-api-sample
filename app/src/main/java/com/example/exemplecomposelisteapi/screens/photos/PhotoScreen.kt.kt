package com.example.exemplecomposelisteapi.screens.photos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.exemplecomposelisteapi.data.LOADING_STATES
import com.example.exemplecomposelisteapi.components.ListItem
import com.example.exemplecomposelisteapi.components.Error
import com.example.exemplecomposelisteapi.components.Loader
import com.example.exemplecomposelisteapi.data.Todo
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exemplecomposelisteapi.components.PhotoItem
import com.example.exemplecomposelisteapi.data.Photo
import com.example.exemplecomposelisteapi.screens.list.ListViewModel

// Composant qui affiche la page de liste.
// Chaque élément est cliquable et affiche le détail d'un élément
@Composable
fun PhotoScreen(viewModel: PhotoViewModel = viewModel()) {
    // Variable qui changeront d'état lors du chargement des données
    val loadingState = viewModel.loadingState.collectAsState()
    val photos = viewModel.photoList.collectAsState()

    // Récupération des éléments à afficher via le ViewModel
    viewModel.getPhotos()

    // En fonction de l'état de chargement
    // on affiche, soit un loader, une erreur ou la liste.
    when (loadingState.value) {
        LOADING_STATES.LOADING -> {
            // Affichage d'un loader
            Loader()
        }
        LOADING_STATES.LOADED -> {
            // Affichage de la liste
            ListPhotos(items = photos.value)
        }
        LOADING_STATES.ERROR -> {
            // Affichage d'un message d'erreur
            Error()
        }
    }
}


// Affichage de la liste des éléments
@Composable
fun ListPhotos(items: List<Photo>?) {
    if (items != null) {
        LazyColumn {
            items(items) { item ->
                PhotoItem(item = item)
            }
        }
    }
}