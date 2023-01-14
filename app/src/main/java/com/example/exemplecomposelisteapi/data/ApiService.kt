package com.example.exemplecomposelisteapi.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class Todo(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
)

data class TodoPost(
    var userId: Int,
    var title: String,
    var completed: Boolean
)

data class Photo(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String,
)

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface APIService {
    // Récupération de la liste des éléments
    // Ici nous utilisons un GET
    // Pour appeler l'API, nous utilisons l'URL suivante :
    // https://jsonplaceholder.typicode.com/todos
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    // Récupération de la liste des photos
    // Ici nous utilisons un GET
    // Pour appeler l'API, nous utilisons l'URL suivante :
    // https://jsonplaceholder.typicode.com/photos
    @GET("photos")
    suspend fun getPhotos(): List<Photo>

    // On utilise @Body pour envoyer des données au serveur
    // L'objet TodoPost sera converti en JSON (vous devez le fournir en paramètre)
    // Ici nous utilisons un POST
    // Pour appeler l'API, nous utilisons l'URL suivante :
    // https://jsonplaceholder.typicode.com/addTodo
    @POST("addTodo")
    suspend fun addTodo(@Body todoPost: TodoPost): Todo

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}