package com.paworo06.gameverse.view.profile

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paworo06.gameverse.ui.theme.GameVerseTheme // Usamos tu tema para el Preview

// --- Constantes de Color (Ajusta estos colores si tienes nombres específicos en Color.kt) ---
// Simulación de colores oscuros si no están definidos globalmente.
val PurplePrimary = Color(0xFF7A00FF)
val BackgroundDark = Color(0xFF121212)
val BackgroundSurface = Color(0xFF1C1C1C)
val TextGray = Color(0xFFAAAAAA)
val DividerColor = Color(0xFF222222)

/**
 * Composable principal que representa toda la pantalla de Perfil.
 * Solo contiene la vista (estructura UI) sin lógica de estado ni eventos.
 */
@Composable
fun ProfileScreen() {
    // Usamos LazyColumn para que el contenido sea desplazable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark) // Color de fondo oscuro
            .padding(horizontal = 16.dp)
    ) {
        item {
            // 1. Encabezado (Título y Cerrar Sesión)
            ProfileHeader()

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Información del Usuario
            ProfileUserInfoSection(
                name = "GamerXtreme",
                email = "gamer.xtreme@email.com"
            )

            // 3. Divisor
            Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))

            // 4. Sección Mi Cuenta
            ProfileSectionTitle("Mi Cuenta")
            ProfileOptionItem(
                icon = Icons.Default.Person,
                title = "Información de la Cuenta"
            )

            ProfileOptionItem(
                icon = Icons.Default.FavoriteBorder, // Usamos la versión de contorno para Lista de Deseos
                title = "Lista de Deseos"
            )

            // 7. Divisor
            Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))


            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ----------------------------------------------------------------------------------
// --- COMPOSABLES AUXILIARES ---
// ----------------------------------------------------------------------------------

@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Título "Perfil"
        Text(
            text = "Perfil",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        // Botón "Cerrar Sesión" (Solo vista, sin onClick real)
        Text(
            text = "Cerrar Sesión",
            color = PurplePrimary,
            fontSize = 16.sp,
            modifier = Modifier.clickable { /* Solo clic fantasma */ }
        )
    }
}

@Composable
fun ProfileUserInfoSection(name: String, email: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(BackgroundSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Avatar",
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Nombre
        Text(
            text = name,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Email
        Text(
            text = email,
            color = TextGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Editar Perfil
        Button(
            onClick = { /* Sin funcionalidad */ },
            colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Editar Perfil", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun ProfileSectionTitle(title: String) {
    Text(
        text = title,
        color = TextGray,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ProfileOptionItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Solo clic fantasma */ }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Título
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        // Flecha derecha
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Ir",
            tint = TextGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ProfileNotificationItem(title: String, isChecked: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono de Notificación
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Título
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        // Switch (Estado fijo)
        Switch(
            checked = isChecked,
            onCheckedChange = { /* Sin funcionalidad */ },
            colors = SwitchDefaults.colors(
                checkedThumbColor = PurplePrimary,
                checkedTrackColor = PurplePrimary.copy(alpha = 0.5f),
                uncheckedThumbColor = BackgroundSurface,
                uncheckedTrackColor = Color.Gray
            )
        )
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GameVerseTheme {
        ProfileScreen()
    }
}