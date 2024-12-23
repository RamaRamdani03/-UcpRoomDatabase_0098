package com.example.ucp2_pam.ui.viewModel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.repository.RepositorySuplier

class SuplierViewModel (private val  repositorySuplier: RepositorySuplier) : ViewModel() {

    var uiState by mutableStateOf(SuplierUiState())

    fun updateState(suplierEvent: SuplierEvent) {
        uiState = uiState.copy(
            suplierEvent = suplierEvent,
        )
    }
}

data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean{
        return id == null && nama == null && kontak == null && alamat == null
    }
}

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat
)

data class SuplierEvent(
    val id: String = "",
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",
)

data class SuplierUiState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)