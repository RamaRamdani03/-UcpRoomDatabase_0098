package com.example.ucp2_pam.ui.navigasi

import HomeNavbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2_pam.ui.view.barang.DetailBarangView
import com.example.ucp2_pam.ui.view.barang.HomeBarangView
import com.example.ucp2_pam.ui.view.barang.InsertBarangView
import com.example.ucp2_pam.ui.view.barang.UpdateBarangView
import com.example.ucp2_pam.ui.view.suplier.HomeSuplierView
import com.example.ucp2_pam.ui.view.suplier.InsertSuplierView

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

       composable(
           DestinasiDetailBarang.routesWithArg,
           arguments = listOf(
               navArgument(DestinasiDetailBarang.id) {
                   type = NavType.StringType
               }
           )
       ) {
           val id = it.arguments?.getString(DestinasiDetailBarang.id)
           id?.let { id ->
               DetailBarangView(
                   onBack = {
                       navController.popBackStack()
                   },
                   onEditClick = {
                       navController.navigate("${DestinasiUpdateBarang.route}/$it")
                   },
                   modifier = modifier,
                   onDeleteClick = {
                       navController.popBackStack()
                   }
               )
           }
       }

       composable(
           DestinasiUpdateBarang.routesWithArg,
           arguments = listOf(
               navArgument(DestinasiUpdateBarang.id) {
                   type = NavType.StringType
               }
           )
       ) {
           UpdateBarangView(
               onBack = {
                   navController.popBackStack()
               },
               onNavigate = {
                   navController.popBackStack()
               },
               modifier = modifier,
           )
       }

       composable(
           route = DestinasiAddSuplier.route
       ) {
           InsertSuplierView(
               onBack = {
                   navController.popBackStack()
               },
               onNavigate = { },
               modifier = modifier
           )
       }
   }
}