package com.example.exemplecomposelisteapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.example.exemplecomposelisteapi.screens.AboutScreen
import com.example.exemplecomposelisteapi.screens.HomeScreen
import com.example.exemplecomposelisteapi.screens.list.ListScreen
import com.example.exemplecomposelisteapi.screens.login.LoginScreen
import com.example.exemplecomposelisteapi.screens.photos.PhotoScreen

enum class STATES { HOME, LIST, ABOUT, PHOTO, LOGIN }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Variable réactive qui contient l'état de l'application, initialisé à HOME
            // Home sera affiché au démarrage de l'application
            val currentScreen = remember { mutableStateOf(STATES.HOME) }

            Scaffold(
                bottomBar = {
                    BottomBar(currentScreen) {
                        currentScreen.value = it
                    }
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Mon application")
                        }
                    )
                }
            ) {
                // Affichage du bon écran en fonction de la page active
                when (currentScreen.value) {
                    STATES.HOME -> HomeScreen(
                        onListClick = { currentScreen.value = STATES.LIST },
                        onPhotoClick = { currentScreen.value = STATES.PHOTO }
                    )
                    STATES.LIST -> ListScreen()
                    STATES.PHOTO -> PhotoScreen()
                    STATES.ABOUT -> AboutScreen()
                    STATES.LOGIN -> LoginScreen {
                        // Après la connexion, on affiche la page à propos
                        // Ceci est un exemple, il faudrait plutôt afficher la page d'accueil
                        // ou la page de liste
                        currentScreen.value = STATES.ABOUT
                    }
                }

            }
        }
    }
}

// Composant qui affiche les boutons de navigations en bas de l'écran
@Composable
fun BottomBar(states: MutableState<STATES>, selected: (STATES) -> Unit = {}) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_baseline_home_24), contentDescription = "Home") },
            label = { Text("Home") },
            selected = states.value == STATES.HOME,
            onClick = {
                selected(STATES.HOME)
            }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_baseline_list_24), contentDescription = "List") },
            label = { Text("List") },
            selected = states.value == STATES.LIST,
            onClick = {
                selected(STATES.LIST)
            }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_baseline_insert_photo_24), contentDescription = "List") },
            label = { Text("Photo") },
            selected = states.value == STATES.PHOTO,
            onClick = {
                selected(STATES.PHOTO)
            }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_person_24), contentDescription = "About") },
            label = { Text("Login") },
            selected = states.value == STATES.LOGIN,
            onClick = {
                selected(STATES.LOGIN)
            }
        )
    }
}


