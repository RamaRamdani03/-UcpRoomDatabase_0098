package com.example.ucp2_pam.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierHomeViewModel

object SuplierList {
    @Composable
    fun DataNama(
        suplierHomeViewModel: SuplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String>{
        val getDataNama by suplierHomeViewModel.homeUiState.collectAsState()
        val namaSplr = getDataNama.listSuplier.map { it.nama }
        return namaSplr
    }
}