package com.example.ucp2_pam.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2_pam.R
import com.example.ucp2_pam.data.entity.Barang

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsBarang(
    barang: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {

    val stokInt = barang.stok.toIntOrNull() ?: -1

    val cardColor = when {
        stokInt == 0 -> Color(0xFFB0BEC5)
        stokInt in 1..10 -> Color(0xFFFFCDD2)
        stokInt > 10 -> Color(0xFFC8E6C9)
        else -> Color(0xFFEEEEEE)
    }

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(2.dp)
                    )
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = barang.id,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(R.drawable.iconinvetory),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(2.dp)
                )
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = barang.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(R.drawable.iconuang),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(2.dp)
                )
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = barang.harga,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = barang.NamaSuplier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}