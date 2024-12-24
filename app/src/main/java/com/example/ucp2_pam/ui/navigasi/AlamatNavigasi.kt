package com.example.ucp2_pam.ui.navigasi

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeBarang : AlamatNavigasi {
    override val route = "homebarang"
}

object DestinasiHomeSuplier : AlamatNavigasi {
    override val route = "homesuplier"
}

object DestinasiAddSuplier : AlamatNavigasi {
    override val route = "addsuplier"
}

object DestinasAddBarang : AlamatNavigasi {
    override val route = "addbarang"
}