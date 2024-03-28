package com.example.list.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.list.models.Archive


@Composable
fun ArchiveItem(item: Archive, onDelete: () -> Unit, onEdit: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, Color.Black) // Mengatur border
    ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Nama        :  ${item.nama}")
                Text(text = "Alamat      :  ${item.alamat}")
                Text(text = "Tanggal    :  ${item.tanggal}")
                Text(text = "Materi       :  ${item.materi}")

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = onEdit) {
                    Text(text = "Edit")
                }
                Button(onClick = onDelete) {
                    Text(text = "Hapus")
                }
            }
        }
    }
}
