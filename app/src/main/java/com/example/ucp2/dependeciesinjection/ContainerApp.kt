package com.example.ucp2.dependeciesinjection

import android.content.Context
import com.example.ucp2.data.database.AllDatabase
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.LocalRepositoryMK
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMK

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMK: RepositoryMK
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(AllDatabase.getDatabase(context).dosenDao())
    }

    override val repositoryMK: RepositoryMK by lazy {
        LocalRepositoryMK(AllDatabase.getDatabase(context).mataKuliahDao())
    }
}
