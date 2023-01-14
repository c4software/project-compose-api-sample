package com.example.exemplecomposelisteapi.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.exemplecomposelisteapi.data.Photo

@Composable
fun PhotoItem(item: Photo) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(model = item.thumbnailUrl, contentDescription = item.title, modifier = Modifier.padding(8.dp))
        Text(item.title, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.weight(1f))
    }
}