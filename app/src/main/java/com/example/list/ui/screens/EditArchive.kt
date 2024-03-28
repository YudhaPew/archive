
package com.example.list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.list.models.Archive
import com.example.list.persistances.ArchiveDao
import kotlinx.coroutines.launch

@Composable
fun EditArchive(item: Archive, onDismiss: () -> Unit, ArchiveDao: ArchiveDao) {
    val coroutineScope = rememberCoroutineScope()
    val (nama, setNama) = remember { mutableStateOf(item.nama) }
    val (alamat, setAlamat) = remember { mutableStateOf(item.alamat) }
    val (tanggal, setTanggal) = remember { mutableStateOf(item.tanggal) }
    val (materi, setMateri) = remember { mutableStateOf(item.materi) }

    // State to hold validation error message
    val (errorMessage, setErrorMessage) = remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ubah Data Imam") },
        text = {
            Column {
                TextField(value = nama, onValueChange = setNama, label = { Text("Nama") })
                TextField(value = alamat, onValueChange = setAlamat, label = { Text("Alamat") })
                TextField(value = tanggal, onValueChange = setTanggal, label = { Text("Tanggal") })
                TextField(value = materi, onValueChange = setMateri, label = { Text("Materi") })

                // Display error message if any
                if (errorMessage != null) {
                    Text(errorMessage, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (nama.isNotBlank() && alamat.isNotBlank() && tanggal.isNotBlank() && materi.isNotBlank()) {
                    coroutineScope.launch {
                        ArchiveDao.update(
                            item.copy(
                                nama = nama,
                                alamat = alamat,
                                tanggal = tanggal,
                                materi = materi,
                            )
                        )
                        onDismiss()
                    }
                } else {
                    setErrorMessage("Data Tidak Boleh Kosong.")
                }
            }) {
                Text("Simpan")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}
