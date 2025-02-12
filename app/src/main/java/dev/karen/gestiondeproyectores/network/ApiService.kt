package dev.karen.gestiondeproyectores.ui.theme.components.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "http://10.0.2.2:3000/"

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}

data class LoginRequest(val email: String, val contrasena: String)
data class LoginResponse(val message: String, val token: String)

data class RegisterRequest(val nombre: String, val apellido: String, val email: String, val contrasena: String)
data class RegisterResponse(val message: String)

object RetrofitClient {
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
