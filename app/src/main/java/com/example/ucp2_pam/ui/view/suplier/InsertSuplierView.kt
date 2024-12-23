package com.example.ucp2_pam.ui.view.suplier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ucp2_pam.ui.viewModel.suplier.FormErrorState
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierEvent

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