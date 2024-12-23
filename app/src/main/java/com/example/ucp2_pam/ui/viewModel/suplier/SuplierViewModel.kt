package com.example.ucp2_pam.ui.viewModel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.repository.RepositorySuplier
import kotlinx.coroutines.launch

class SuplierViewModel (private val  repositorySuplier: RepositorySuplier) : ViewModel() {

    var uiState by mutableStateOf(SuplierUiState())

    fun updateState(suplierEvent: SuplierEvent) {
        uiState = uiState.copy(
            suplierEvent = suplierEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.suplierEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "id tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySuplier.insertSuplier(currentEvent.toSuplierEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasl disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda!"
            )
        }
    }

    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
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