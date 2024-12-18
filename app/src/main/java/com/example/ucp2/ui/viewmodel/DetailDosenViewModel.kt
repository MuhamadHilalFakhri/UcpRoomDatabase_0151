package com.example.ucp2.ui.viewmodel
import com.example.ucp2.ui.viewmodel.DosenViewModel.DosenEvent
import com.example.ucp2.data.entity.Dosen

// Memindahkan data dari entity ke UI
fun Dosen.toDetailUiEvent(): DosenEvent {
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jenisKelamin = jenisKelamin
    )
}

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)