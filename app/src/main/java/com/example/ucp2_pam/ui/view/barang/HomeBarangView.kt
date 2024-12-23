package com.example.ucp2_pam.ui.view.barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.R
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.ui.costumwidget.TopAppBar
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.barang.HomeBarangViewModel
import com.example.ucp2_pam.ui.viewModel.barang.HomeUiStateBarang
import kotlinx.coroutines.launch

@Composable
fun HomeBarangView(
    modifier: Modifier = Modifier,
    viewModel: HomeBarangViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddBarangClick: () -> Unit = { },
    onDetailBarangClick: (String) -> Unit = { },
    onBack: () -> Unit
) {
    Scaffold (
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ){
            TopAppBar(
                judul = "Data Barang",
                showBackButton = true,
                onBack = onBack,
            ) }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBarangClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Barang"
                )
            }
        }
    ) { innerPadding ->
        val homeUiStateBarang by viewModel.homeUiStateBarang.collectAsState()

        BodyHomeBarangView(
            homeUiStateBarang = homeUiStateBarang,
            onClick = {
                onDetailBarangClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeBarangView (
    homeUiStateBarang: HomeUiStateBarang,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {

    val  coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiStateBarang.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiStateBarang.isError -> {
            LaunchedEffect (homeUiStateBarang.errorMessage){
                homeUiStateBarang.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiStateBarang.listBarang.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Barang. ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListBarang(
                listBarang = homeUiStateBarang.listBarang,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang(
    listBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn (
        modifier = modifier
    ){
        items(
            items = listBarang,
            itemContent = { barang ->
                CardsBarang(
                    barang = barang,
                    onClick = { onClick(barang.id) }
                )
            }
        )
    }
}

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