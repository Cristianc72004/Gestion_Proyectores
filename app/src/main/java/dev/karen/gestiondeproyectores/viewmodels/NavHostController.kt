package dev.karen.gestiondeproyectores.viewmodels

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.karen.gestiondeproyectores.ui.theme.components.LoginScreen
import dev.karen.gestiondeproyectores.ui.theme.components.ProjectorRegistrationScreen
import dev.karen.gestiondeproyectores.ui.theme.components.RegisterScreen


@Composable
fun NavHost(navController: NavHostController) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("projectorRegistration") { ProjectorRegistrationScreen(navController) }
    }
}
