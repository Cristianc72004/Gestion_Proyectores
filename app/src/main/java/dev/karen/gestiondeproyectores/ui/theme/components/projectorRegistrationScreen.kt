package dev.karen.gestiondeproyectores.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProjectorRegistrationScreen(navController: NavHostController) {
    var selectedProjector by remember { mutableStateOf<String?>(null) }
    var idNumber by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    val projectors = listOf(
        "Proyector A" to true,
        "Proyector B" to false
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Selecciona un proyector", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        projectors.forEach { (name, isAvailable) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { if (isAvailable) selectedProjector = name },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (selectedProjector == name) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = if (isAvailable) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(name, style = MaterialTheme.typography.bodyLarge)
                        Text(if (isAvailable) "Disponible" else "No Disponible", color = if (isAvailable) Color.Green else Color.Red)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = idNumber,
            onValueChange = { if (it.length <= 10 && it.all { char -> char.isDigit() }) idNumber = it },
            label = { Text("Número de Cédula") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Hora de Retiro: $currentTime", style = MaterialTheme.typography.bodyMedium)
        Text("Hora de Entrega: $currentTime", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            enabled = selectedProjector != null && idNumber.length == 10,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar Préstamo")
        }
    }
}