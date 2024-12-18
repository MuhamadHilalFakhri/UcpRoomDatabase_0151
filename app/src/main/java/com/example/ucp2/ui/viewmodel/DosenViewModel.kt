package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp2.repository.RepositoryDosen

class DosenViewModel (private val repositoryDosen: RepositoryDosen) : ViewModel()
{

    data class FormErrorState(
        val nidn: String? = null,
        val nama: String? = null,
        val jenisKelamin: String? = null
    ) {
        fun isValid(): Boolean {
            return nidn == null && nama == null && jenisKelamin == null
        }
    }
    // Data class untuk menyimpan data input form
    data class DosenEvent(
        val nidn: String = "",
        val nama: String = "",
        val jenisKelamin: String = ""
    )
}