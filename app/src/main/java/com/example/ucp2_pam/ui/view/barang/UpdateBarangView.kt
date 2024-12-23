package com.example.ucp2_pam.ui.view.barang

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.ui.viewModel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewModel.barang.UpdateBarangViewModel

@Composable
fun UpdateBarangView(
    onBack : () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateBarangViewModel = viewModel (factory = PenyediaViewModel.Factory)
) {

}