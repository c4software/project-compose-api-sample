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

data class LoginInfo(
    var username: String,
    var password: String,
)

data class LoginResponse(
    var token: String,
)

data class Photo(
    var albumId: Int, // Identifiant de l'album
    var id: Int, // Identifiant de la photo
    var title: String, // Titre de la photo
    var url: String, // URL de la photo (lien vers l'image, de type jpg, png, etc.)
    var thumbnailUrl: String, // URL de la miniature de la photo (lien vers l'image, de type jpg, png, etc.)
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

    // Fausse API permettant de se connecter
    // Ici nous utilisons un POST
    // https://jsonplaceholder.typicode.com/posts
    @POST("posts")
    suspend fun doLogin(@Body loginInfo: LoginInfo): LoginResponse

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