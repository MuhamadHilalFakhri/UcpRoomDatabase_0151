package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen

class DosenViewModel (private val repositoryDosen: RepositoryDosen) : ViewModel()
{
    var uiState by mutableStateOf(DosenUIState())
    // Memperbarui state berdasarkan inputan

    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }
    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }



    data class DosenUIState(
        val dosenEvent: DosenEvent = DosenEvent(),
        val isEntryValid: FormErrorState = FormErrorState(),
        val snackBarMessage: String? = null,
        val dosenList: List<Dosen> = emptyList(),
        val selectedDosen: Dosen? = null
    )

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

    // Menyimpan input form ke dalam entity
    fun DosenEvent.toDosenEntity(): Dosen = Dosen(
        nidn = nidn,
        nama = nama,
        jenisKelamin = jenisKelamin
    )
}