import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK
import com.example.ucp2.ui.navigation.DestinasiUpdateMatkul
import com.example.ucp2.ui.viewmodelmk.MataKuliahViewModel.MataKuliahEvent
import com.example.ucp2.ui.viewmodelmk.MataKuliahViewModel
import com.example.ucp2.ui.viewmodelmk.MataKuliahViewModel.MatkulUIState
import com.example.ucp2.ui.viewmodelmk.toDetailUiEvent
import com.example.ucp2.ui.viewmodelmk.toMataKuliahEntity
import com.example.ucp2.ui.viewmodelmk.MataKuliahViewModel.FormErrorState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatkulViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK
) : ViewModel() {

    var updateUIState by mutableStateOf(MataKuliahViewModel.MatkulUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMatkul.KODE])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMK.getMk(_kode)
                .filterNotNull()
                .first()
                .toUIStateMatkul()
        }
    }

    fun updateState(mataKuliahEvent: MataKuliahEvent) {
        updateUIState = updateUIState.copy(
            mataKuliahEvent = mataKuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.mataKuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateDataMK() {
        val currentEvent = updateUIState.mataKuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMK.updateMk(currentEvent.toMataKuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Berhasil Diupdate",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

// Memindahkan data dari entity MataKuliah ke UI state
fun MataKuliah.toUIStateMatkul(): MatkulUIState =
    MatkulUIState(
        mataKuliahEvent = this.toDetailUiEvent()
    )


