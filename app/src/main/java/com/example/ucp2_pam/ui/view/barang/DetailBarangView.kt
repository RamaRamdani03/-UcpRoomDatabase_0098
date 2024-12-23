package com.example.ucp2_pam.ui.view.barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.data.entity.Barang
import com.example.ucp2_pam.ui.costumwidget.TopAppBar
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.barang.DetailBarangViewModel
import com.example.ucp2_pam.ui.viewModel.barang.DetailUiStateBarang
import com.example.ucp2_pam.ui.viewModel.barang.toBarangEntity

@Composable
fun DetailBarangView(
    modifier: Modifier = Modifier,
    viewModel: DetailBarangViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
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
                judul = "Detail Barang",
                showBackButton = true,
                onBack = onBack
            ) }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUiState.value.detailUiEvent.id)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
               Icon(
                   imageVector = Icons.Default.Edit,
                   contentDescription = ""
               )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetailBarang(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteBarang()
                onDeleteClick()
            }
        )

    }

}

@Composable
fun BodyDetailBarang(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiStateBarang = DetailUiStateBarang(),
    onDeleteClick: () -> Unit = { }
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailBarang(
                    barang = detailUiState.detailUiEvent.toBarangEntity(),
                    modifier = Modifier

                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                   Text("Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier =Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailBarang(
    modifier: Modifier = Modifier,
    barang: Barang
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primaryContainer,
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailBarang(judul = "Id", isinya = barang.id)
            Spacer(modifier = Modifier.padding(16.dp))

            ComponentDetailBarang(judul = "Nama", isinya = barang.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBarang(judul = "Deskripsi", isinya = barang.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBarang(judul = "Harga", isinya = barang.harga)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBarang(judul = "Stok", isinya = barang.stok)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBarang(judul = "Nama Suplier", isinya = barang.NamaSuplier)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ComponentDetailBarang(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
      Text(
          text = "$judul : ",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.Gray
      )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {  },
        title = {Text("Delete Data")},
        text = { Text("Apakah anda yakin ingin menghapus data ? ")},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        }
    )
}