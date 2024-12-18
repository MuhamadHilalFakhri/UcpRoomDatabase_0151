package com.example.ucp2.ui.viewmodel
import com.example.ucp2.ui.viewmodel.DosenViewModel.DosenEvent
import com.example.ucp2.data.entity.Dosen


fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)