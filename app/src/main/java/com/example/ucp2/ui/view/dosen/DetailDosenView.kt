package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.viewmodel.DetailUiState
import com.example.ucp2.ui.viewmodel.toDosenEntity

@Composable
fun BodyDetailDosen(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
){
    when{
        detailUiState.isLoading ->{
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty ->{
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailDosen(
                    dosen = detailUiState.detailUiEvent.toDosenEntity(),
                    modifier = Modifier
                )
            }
        }

        detailUiState.isUiEventNotEmpty ->{
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ItemDetailDosen(
    modifier: Modifier = Modifier,
    dosen: Dosen
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailDosen(judul = "NIDN", isinya = dosen.nidn)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailDosen(judul = "Nama", isinya = dosen.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailDosen(judul = "Jenis Kelamin", isinya = dosen.jenisKelamin)
        }
    }
}

@Composable
fun ComponentDetailDosen(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
