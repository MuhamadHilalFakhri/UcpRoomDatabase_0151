package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp2.repository.RepositoryDosen

class DosenViewModel (private val repositoryDosen: RepositoryDosen) : ViewModel()
{
    // Data class untuk menyimpan data input form
    data class DosenEvent(
        val nidn: String = "",
        val nama: String = "",
        val jenisKelamin: String = ""
    )
}