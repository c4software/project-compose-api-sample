package com.example.exemplecomposelisteapi.screens

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
import com.example.exemplecomposelisteapi.screens.list.ListViewModel

// Composant qui affiche la page de liste.
// Chaque élément est cliquable et affiche le détail d'un élément
@Composable
fun ListScreen(viewModel: ListViewModel = ListViewModel()) {
    val selectedItem = remember { mutableStateOf<Todo?>(null) }

    val loadingState = viewModel.loadingState.collectAsState()
    val items = viewModel.itemsList.collectAsState()




    // Récupération des éléments à afficher via le ViewModel
    viewModel.getItems()

    if (selectedItem.value == null) {
        // En fonction de l'état de chargement
        // on affiche, soit un loader, une erreur ou la liste.
        when (loadingState.value) {
            LOADING_STATES.LOADING -> {
                // Affichage d'un loader
                Loader()
            }
            LOADING_STATES.LOADED -> {
                // Affichage de la liste
                ListItems(items = items.value) { selectedItem.value = it }
            }
            LOADING_STATES.ERROR -> {
                // Affichage d'un message d'erreur
                Error()
            }
            else -> {
                // Affichage d'un message d'erreur
                Error()
            }
        }
    } else {
        DetailScreen(item = selectedItem.value!!.title) {
            selectedItem.value = null
        }
    }
}


// Affichage de la liste des éléments
@Composable
fun ListItems(items: List<Todo>?, onItemSelected: (Todo) -> Unit) {
    if(items != null) {
        LazyColumn {
            items(items) { item ->
                ListItem(item = item.title) {
                    onItemSelected(item)
                }
            }
        }
    }
}

// Vue de détail d'un élément
// Affiche le nom de l'élément et un bouton pour revenir en arrière
@Composable
fun DetailScreen(item: String, onBack: () -> Unit = {}) {
    // Detail of item
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(item, textAlign = TextAlign.Center, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = onBack) {
            Text("Retour")
        }
    }
}