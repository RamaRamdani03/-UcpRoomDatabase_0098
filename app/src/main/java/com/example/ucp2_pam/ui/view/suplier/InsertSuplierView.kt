package com.example.ucp2_pam.ui.view.suplier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ucp2_pam.ui.viewModel.suplier.FormErrorState
import com.example.ucp2_pam.ui.viewModel.suplier.SuplierEvent


@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent = SuplierEvent(),
    onValueChange: (SuplierEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {

}