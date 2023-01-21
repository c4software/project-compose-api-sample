package com.example.exemplecomposelisteapi.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exemplecomposelisteapi.data.LOADING_STATES
import com.example.exemplecomposelisteapi.data.LoginResponse

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), loginAction: (LoginResponse) -> Unit) {
    val localContext = LocalContext.current
    val focusManager = LocalFocusManager.current

    val loadingState = viewModel.loadingState.collectAsState()

    var mUsername by remember { mutableStateOf("") } // Variable qui va contenir le nom d'utilisateur
    var mPassword by remember { mutableStateOf("") } // Variable qui va contenir le mot de passe

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextInput de saisie du nom d'utilisateur
        OutlinedTextField(
            value = mUsername,
            maxLines = 1,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { mUsername = it },
            label = { Text("Nom d'utilisateur") },
            modifier = Modifier.fillMaxWidth()
        )

        // TextInput de saisie du mot de passe
        OutlinedTextField(
            value = mPassword,
            maxLines = 1,
            onValueChange = { mPassword = it },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth()
        )

        // Bouton de connexion qui va appeler la fonction login du viewModel
        Button(
            enabled = loadingState.value != LOADING_STATES.LOADING,
            onClick = {
                if (mUsername.isEmpty() || mPassword.isEmpty()) {
                    Toast.makeText(localContext, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                } else {
                    // Appel de la fonction login du viewModel, qui va appeler l'API, et qui va appeler la fonction loginAction précisée en paramètre
                    viewModel.doLogin(mUsername, mPassword) {
                        loginAction(it)
                    }
                }
            }) {
            if (loadingState.value == LOADING_STATES.LOADING) {
                Text("Chargement...")
            } else {
                Text("Se connecter")
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen(loginAction = {})
}