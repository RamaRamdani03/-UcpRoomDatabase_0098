package com.example.ucp2_pam.ui.viewModel.suplier

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.data.entity.Suplier
import com.example.ucp2_pam.repository.RepositorySuplier

class SuplierViewModel (private val  repositorySuplier: RepositorySuplier) : ViewModel() {

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

data class SuplierEvent(
    val id: String = "",
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",
)