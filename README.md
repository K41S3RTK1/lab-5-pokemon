# Lab 5 – Pokedex (Retrofit + Jetpack Compose + Navigation)

App que consume la [PokeAPI](https://pokeapi.co/) para:
- Listar los primeros 100 Pokémon (nombre + sprite).
- Mostrar detalle con 4 imágenes: Front, Back, Front Shiny, Back Shiny.

## Stack
- Kotlin + Jetpack Compose (Material3)
- Navigation Compose
- Retrofit + Gson
- Coil (carga de imágenes)

## Arquitectura
`network (Retrofit/DTO) → data (Repository) → ViewModel (StateFlow) → UI (Compose)`

## Endpoint
- `GET https://pokeapi.co/api/v2/pokemon?limit=100`

## Cómo correr
1. Android Studio actualizado  
2. En `AndroidManifest.xml`:
   ```xml
   <uses-permission android:name="android.permission.INTERNET"/>
   
<img width="1080" height="2400" alt="screenshots:detail" src="https://github.com/user-attachments/assets/b2d455d9-a32c-4dde-a74d-e48bdcb4bfcf" />
<img width="1080" height="2400" alt="screenshots:list" src="https://github.com/user-attachments/assets/c9364724-ddd6-4099-bf92-e6d84e697b8c" />
