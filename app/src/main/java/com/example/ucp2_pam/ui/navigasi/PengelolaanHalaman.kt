package com.example.ucp2_pam.ui.navigasi

import HomeNavbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2_pam.ui.view.barang.HomeBarangView
import com.example.ucp2_pam.ui.view.barang.InsertBarangView
import com.example.ucp2_pam.ui.view.suplier.HomeSuplierView

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
       composable(
           route = DestinasiHomeBarang.route
       ) {
           HomeBarangView (
               onDetailBarangClick = {
                   id ->
                   navController.navigate("${DestinasiDetailBarang.route}/$id")
                   println("PengelolaanHalaman: id_ = $id")
               },
               onAddBarangClick = { navController.navigate(DestinasAddBarang.route) },
               onBack = {
                   navController.popBackStack()
               },
               modifier = Modifier
           )
       }
       composable(
           route = DestinasiHomeSuplier.route
       ) {
           HomeSuplierView(
               onBack = {
                   navController.popBackStack()
               },
               modifier = Modifier
           )
       }
       composable(
           route = DestinasAddBarang.route
       ) {
           InsertBarangView(
               onBack = {
                   navController.popBackStack()
               },
               onNavigate = {  },
               modifier = modifier
           )
       }
   }
}