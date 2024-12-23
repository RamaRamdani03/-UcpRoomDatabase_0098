
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2_pam.R


@Preview
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
            Image(
                painter = painterResource(id = R.drawable.untaa),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .shadow(10.dp, RoundedCornerShape(50.dp))
            )
        }
    }
}