package com.example.ucp2_pam.ui.navigasi

import HomeNavbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PengelolaanHalaman (
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
   NavHost(
       navController = navController,
       startDestination = DestinasiHome.route
   ) {
       composable(
           route = DestinasiHome.route
       ) {
           HomeNavbar(
               onAddBarang = { navController.navigate(DestinasAddBarang.route) },
               onAddSuplier = { navController.navigate(DestinasiAddSuplier.route) },
               onHomeBarang = { navController.navigate(DestinasiHomeBarang.route) },
               onHomeSuplier = { navController.navigate(DestinasiHomeSuplier.route) },
           )
       }
   }
}