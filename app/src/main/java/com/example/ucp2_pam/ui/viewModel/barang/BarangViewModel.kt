package com.example.ucp2_pam.ui.viewModel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.repository.RepositoryBarang

class BarangViewModel (private val repositoryBarang: RepositoryBarang) : ViewModel() {

}

data class FormErrorStateBarang(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val NamaSuplier: String? = null,
)