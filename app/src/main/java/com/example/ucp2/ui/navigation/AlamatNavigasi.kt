package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "home_dosen"
}

object DestinasiDetailDosen : AlamatNavigasi {
    override val route = "detail_dosen"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}