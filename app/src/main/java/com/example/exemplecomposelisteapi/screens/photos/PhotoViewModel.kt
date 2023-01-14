package com.example.exemplecomposelisteapi.screens.photos

import androidx.lifecycle.ViewModel
import com.example.exemplecomposelisteapi.data.APIService
import com.example.exemplecomposelisteapi.data.LOADING_STATES
import com.example.exemplecomposelisteapi.data.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class PhotoViewModel : ViewModel() {
    val photoList = MutableStateFlow<List<Photo>>(emptyList())
    val loadingState = MutableStateFlow(LOADING_STATES.LOADING)

    // Récupération des éléments.
    // On utilise un CoroutineScope pour pouvoir faire des appels réseaux
    // sans bloquer le thread principal (c'est à dire l'interface)
    fun getPhotos() {
        loadingState.value = LOADING_STATES.LOADING
        CoroutineScope(Dispatchers.IO).launch {
            try {
                photoList.value = APIService.getInstance().getPhotos()
                loadingState.value =LOADING_STATES.LOADED
            } catch (e: Throwable) {
                loadingState.value = LOADING_STATES.ERROR
            }
        }
    }
}
