package com.example.ucp2_pam.ui.view.suplier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ucp2_pam.ui.viewModel.suplier.FormErrorState
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierEvent
import kotlinx.coroutines.launch

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
    }
}