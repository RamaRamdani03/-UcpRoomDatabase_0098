
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeNavbar(
    onAddBarang: () -> Unit = {},
    onAddSuplier: () -> Unit = {},
    onHomeBarang: () -> Unit = {},
    onHomeSuplier: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF8B4513), shape = RoundedCornerShape(bottomEnd = 50.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon and Text in a Column
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Icon(
                    Icons.Filled.List,
                    contentDescription = "Menu",
                    tint = Color.White
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Cap Onta Store",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Welcome to the Cap Onta Banjar store",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}