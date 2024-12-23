package com.example.ucp2_pam.ui.viewModel

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2_pam.TokoApp
import com.example.ucp2_pam.ui.viewModel.barang.BarangViewModel
import com.example.ucp2_pam.ui.viewModel.barang.DetailBarangViewModel
import com.example.ucp2_pam.ui.viewModel.barang.HomeBarangViewModel
import com.example.ucp2_pam.ui.viewModel.barang.UpdateBarangViewModel
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierHomeViewModel
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            SuplierViewModel(
                TokoApp().containerApp.repositorySuplier
            )
        }
        initializer {
            SuplierHomeViewModel(
                TokoApp().containerApp.repositorySuplier
            )
        }
        initializer {
            HomeViewModel()
        }
        initializer {
            BarangViewModel(
                TokoApp().containerApp.repositoryBarang
            )
        }
        initializer {
            HomeBarangViewModel(
                TokoApp().containerApp.repositoryBarang
            )
        }
        initializer {
            DetailBarangViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBarang
            )
        }
        initializer {
            UpdateBarangViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBarang
            )
        }
    }
}