# Application d'exemple

Cette application contient les éléments suivants :

- Une page d'accueil
- Une page de liste, les données sont récupérées depuis une API (fichier `ApiService.kt`)
- Une page de détail, affiche les donnée de l'élément sélectionné dans la liste
- Une page à propos, affiche des informations sur l'application

[Voir l'application](https://www.youtube.com/shorts/-6sUwt4ZL9k)

## Architecture

Cette application est basique, elle est juste là pour illustrer l'usage de composants Android. 

Dans un véritable projet il est primordial d'utiliser :

- Jetpack Compose pour la navigation entre les différents écrans
- MVVM pour l'accès aux données (API, base de données, etc.)

## Librairies

Pour fonctionner cette application utilise les librairies suivantes :

- [Retrofit](https://square.github.io/retrofit/) pour l'accès aux données
- Gson

Ajouter les dépendances suivantes dans le fichier `build.gradle` :

```groovy
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```

⚠️ Il faut ajouter les éléments dans le bloc `dependencies` déjà présent dans le fichier `build.gradle`.

## Permissions

Cette application nécessite les permissions suivantes :

- INTERNET : pour accéder à l'API
- ACCESS_NETWORK_STATE : pour vérifier si l'appareil est connecté à internet

Ajouter les permissions dans le fichier `AndroidManifest.xml` :

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```