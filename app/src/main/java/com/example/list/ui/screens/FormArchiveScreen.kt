package com.example.list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuid4
import com.example.list.models.Archive
import com.example.list.persistances.ArchiveDao
import kotlinx.coroutines.launch

@Composable
fun FormArchiveScreen(archiveDao: ArchiveDao) {

    val scope = rememberCoroutineScope()

    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val alamat = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val materi = remember { mutableStateOf(TextFieldValue("")) }
    val (errorMessage, setErrorMessage) = remember { mutableStateOf<String?>(null) }



    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "Nama") },
            modifier = Modifier.fillMaxWidth(),
            value = nama.value, onValueChange = {
                nama.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Alamat") },
            modifier = Modifier.fillMaxWidth(),
            value = alamat.value, onValueChange = {
                alamat.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            modifier = Modifier.fillMaxWidth(),
            value = tanggal.value, onValueChange = {
                tanggal.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Materi") },
            modifier = Modifier.fillMaxWidth(),
            value = materi.value, onValueChange = {
                materi.value = it
            })

        Row {
            if (errorMessage != null) {
                Text(errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }

        Row {
            Button(modifier = Modifier.weight(5f), onClick = {
                // Periksa apakah ada input yang kosong sebelum menyimpan data
                if (nama.value.text.isNotBlank() && alamat.value.text.isNotBlank() && tanggal.value.text.isNotBlank() && materi.value.text.isNotBlank()) {
                    val item = Archive(uuid4().toString(),
                        nama.value.text,
                        alamat.value.text,
                        tanggal.value.text,
                        materi.value.text)

                    scope.launch {
                        archiveDao.upsert(item)
                    }
                    // Reset nilai input setelah data disimpan
                    nama.value = TextFieldValue("")
                    alamat.value = TextFieldValue("")
                    tanggal.value = TextFieldValue("")
                    materi.value = TextFieldValue("")
                    setErrorMessage(null)
                }else {
                    setErrorMessage("Lengkapi Data Anda.")
                }

            }) {
                Text(text = "Simpan")
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                // Reset nilai input ketika tombol "Batal" ditekan
                nama.value = TextFieldValue("")
                alamat.value = TextFieldValue("")
                tanggal.value = TextFieldValue("")
                materi.value = TextFieldValue("")
            }) {
                Text(text = "Batal")
            }
        }

    }
}