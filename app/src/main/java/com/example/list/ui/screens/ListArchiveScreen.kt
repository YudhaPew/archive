package com.example.list.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.example.list.models.Archive
import com.example.list.persistances.ArchiveDao
import kotlinx.coroutines.launch

@Composable
fun ListArchiveScreen(archiveDao: ArchiveDao, onEdit: (Archive) -> Unit) {

    val _list: LiveData<List<Archive>> = archiveDao.loadAll()
    val list: List<Archive> by _list.observeAsState(initial = listOf())
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(list) { item ->
                val index = list.indexOf(item)
                ArchiveItem(
                    item = item,
                    onDelete = {
                        coroutineScope.launch {
                            archiveDao.delete(list[index].id)
                        }
                    },
                    onEdit = { onEdit(list[index]) }
                )
            }
        }
    }
}
