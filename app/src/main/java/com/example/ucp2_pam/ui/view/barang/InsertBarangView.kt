package com.example.ucp2_pam.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.data.SuplierList
import com.example.ucp2_pam.ui.costumwidget.DynamicSelectTextField
import com.example.ucp2_pam.ui.costumwidget.TopAppBar
import com.example.ucp2_pam.ui.navigasi.AlamatNavigasi
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.barang.BarangEvent
import com.example.ucp2_pam.ui.viewModel.barang.BarangUiState
import com.example.ucp2_pam.ui.viewModel.barang.BarangViewModel
import com.example.ucp2_pam.ui.viewModel.barang.FormErrorStateBarang
import kotlinx.coroutines.launch

object DestinasiInsert : AlamatNavigasi{
    override val route: String = "insert_Barang"
}

@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorStateBarang: FormErrorStateBarang = FormErrorStateBarang(),
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.id,
            onValueChange = {
                onValueChange(barangEvent.copy(id = it))
            },
            label = { Text("id") },
            isError = errorStateBarang.id != null,
            placeholder = { Text("Masukkan id")},
        )
        Text(
            text = errorStateBarang.id ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("Nama Barang") },
            isError = errorStateBarang.nama != null,
            placeholder = { Text("Masukkan Nama Barang") },
        )
        Text(
            text = errorStateBarang.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi")},
            isError = errorStateBarang.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi")},
        )
        Text(
            text = errorStateBarang.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga")},
            isError = errorStateBarang.harga != null,
            placeholder = { Text("Masukkan Harga")},
        )
        Text(
            text = errorStateBarang.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("Stok")},
            isError = errorStateBarang.stok != null,
            placeholder = { Text("Masukkan Stok")},
        )
        Text(
            text = errorStateBarang.stok ?: "",
            color = Color.Red
        )

        DynamicSelectTextField(
            label = "Nama Suplier",
            modifier = Modifier,
            selectedValue = barangEvent.NamaSuplier,
            onValueChangedEvent = { selectedSuplier ->
                onValueChange(barangEvent.copy(NamaSuplier = selectedSuplier))
            },
            options = SuplierList.DataNama(),
            isError = errorStateBarang.NamaSuplier != null
        )
        Text(
            text = errorStateBarang.NamaSuplier ?: "",
            color = Color.Red
        )

    }
}

@Composable
fun InsertBarangView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState ()}
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect (uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }

        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
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
                judul = "Tambah Barang",

            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            InsertBodyBarang(
                uiState = uiState,
                onValueChange = {
                    updateEvent ->
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
fun InsertBodyBarang(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BarangUiState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorStateBarang = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}