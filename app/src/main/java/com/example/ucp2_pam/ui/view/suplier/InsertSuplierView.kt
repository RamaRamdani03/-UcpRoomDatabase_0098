package com.example.ucp2_pam.ui.view.suplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.ui.costumwidget.TopAppBar
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.suplier.FormErrorState
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierEvent
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierUiState
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent = SuplierEvent(),
    onValueChange: (SuplierEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.id,
            onValueChange = {
                onValueChange(suplierEvent.copy(id = it))
            },
            label = { Text("id") },
            isError = errorState.id != null,
            placeholder = { Text("Masukkan id") },
        )
        Text(
            text = errorState.id ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.nama,
            onValueChange = {
                onValueChange(suplierEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = {
                onValueChange(suplierEvent.copy(kontak = it))
            },
            label = { Text("Kontak") },
            isError = errorState.id != null,
            placeholder = { Text("Masukkan Kontak") },
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = {
                onValueChange(suplierEvent.copy(alamat = it))
            },
            label = { Text("Alamat") },
            isError = errorState.alamat != null,
            placeholder = { Text("Masukkan Alamat") },
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
    }
}

@Composable
fun InsertSuplierView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }

        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){

            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
            InsertBodySuplier(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    // Update state di ViewModel
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodySuplier(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit,
    uiState: SuplierUiState,
    onClick: () -> Unit
) {
   Column(
       modifier = modifier.fillMaxWidth(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       FormSuplier(
           suplierEvent = uiState.suplierEvent,
           onValueChange = onValueChange,
           errorState = uiState.isEntryValid,
           modifier = Modifier.fillMaxWidth()
       )
       Button(
           onClick = onClick,
           modifier = Modifier.fillMaxWidth(),
           ) {
           Text("Simpan")
       }
   }
}