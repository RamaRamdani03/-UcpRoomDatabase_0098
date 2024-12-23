package com.example.ucp2_pam.ui.view.suplier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import com.example.ucp2_pam.ui.costumwidget.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.suplier.HomeUiState
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierHomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeSuplierView(
    viewModel: SuplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (String) -> Unit = { },
    onAddSuplierClick: () -> Unit = { },
    onBack: () -> Unit = { },
    modifier: Modifier
) {
    Scaffold (
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
            TopAppBar(
                judul = "List Suplier",
                showBackButton = true,
                onBack = onBack,
            ) }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSuplierClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Suplier"
                )
            }
        }
    ) { innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeSuplierView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeSuplierView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            LaunchedEffect (homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { mesaage ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(mesaage)
                    }
                }
            }
        }

        homeUiState.listSuplier.isEmpty() -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
              Text(
                  text = "Tidak ada data Suplier. ",
                  fontSize = 18.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(16.dp)
              )
            }
        }

        else -> {
            ListSuplier(
                listSuplier = homeUiState.listSuplier,
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
fun ListSuplier(
    listSuplier: List<Suplier>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn (
        modifier =modifier
    ) {
        items(
            items = listSuplier,
            itemContent = { suplier ->
                CardSuplier(
                    suplier = suplier,
                    onClick = { onClick(suplier.id) }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSuplier(
    suplier: Suplier,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = suplier.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = suplier.id,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = suplier.kontak,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = suplier.alamat,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}