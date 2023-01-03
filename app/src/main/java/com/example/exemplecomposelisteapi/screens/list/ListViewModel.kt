package com.example.exemplecomposelisteapi.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exemplecomposelisteapi.data.APIService
import com.example.exemplecomposelisteapi.data.LOADING_STATES
import com.example.exemplecomposelisteapi.data.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ListViewModel : ViewModel() {
    val itemsList = MutableStateFlow<List<Todo>>(emptyList())
    val loadingState = MutableStateFlow<LOADING_STATES>(LOADING_STATES.LOADING)

    // Récupération des éléments.
    // On utilise un CoroutineScope pour pouvoir faire des appels réseaux
    // sans bloquer le thread principal (c'est à dire l'interface)
    fun getItems() {
        loadingState.value = LOADING_STATES.LOADING
        CoroutineScope(Dispatchers.IO).launch {
            try {
                itemsList.value = APIService.getInstance().getTodos()
                loadingState.value =LOADING_STATES.LOADED
            } catch (e: Throwable) {
                loadingState.value = LOADING_STATES.ERROR
            }
        }
    }
}
