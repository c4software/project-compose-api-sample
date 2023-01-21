package com.example.exemplecomposelisteapi.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.exemplecomposelisteapi.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loadingState = MutableStateFlow(LOADING_STATES.LOADED)

    fun doLogin(username: String, password: String, onLoginSuccess: (LoginResponse) -> Unit) {
        // Passer le loadingState à LOADING pour afficher le chargement
        loadingState.value = LOADING_STATES.LOADING

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Appel réseau
                APIService.getInstance().doLogin(LoginInfo(username, password)).let {
                    // Cette partie du code est appelée lorsque l'appel réseau est terminé
                    loadingState.value = LOADING_STATES.LOADED

                    // On déclenche la fonction onLoginSuccess, celle-ci est passée en paramètre
                    onLoginSuccess(it)
                }
            } catch (e: Throwable) {
                loadingState.value = LOADING_STATES.ERROR
            }
        }
    }
}