package com.example.list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.list.models.Archive
import com.example.list.persistances.AppDatabase

@Composable
fun ArchiveScreen() {
    val context = LocalContext.current

    val db = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    val archiveDao = db.archiveDao()

    val (itemToEdit, setItemToEdit) = remember { mutableStateOf<Archive?>(null) }

    if (itemToEdit != null) {
        EditArchive(item = itemToEdit!!, onDismiss = { setItemToEdit(null) }, archiveDao)
    }

    Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
        Text(
            text = "DATA ARSIP IMAM TARAWIH",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        FormArchiveScreen(archiveDao)

        ListArchiveScreen(archiveDao, onEdit = { item -> setItemToEdit(item) })
    }
}