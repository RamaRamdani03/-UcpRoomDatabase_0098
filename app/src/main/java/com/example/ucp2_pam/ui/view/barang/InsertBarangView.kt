package com.example.ucp2_pam.ui.view.barang

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ucp2_pam.ui.viewModel.barang.BarangEvent
import com.example.ucp2_pam.ui.viewModel.barang.FormErrorStateBarang

@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorStateBarang: FormErrorStateBarang = FormErrorStateBarang(),
    modifier: Modifier = Modifier
){

}