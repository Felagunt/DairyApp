package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiariesEvent

@Composable
fun DiaryItem(
    diary: Diary,
    onEvent: (DiariesEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            //.clickable { onItemClick(diary) }
            .border(4.dp, Color.DarkGray)
            .shadow(
                shape = RoundedCornerShape(15.dp),
                elevation = 15.dp,
                ambientColor = MaterialTheme.colorScheme.secondaryContainer
            )
            .background(MaterialTheme.colorScheme.onSecondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = diary.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = diary.content,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = "Created: ${diary.timestamp}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        IconButton(onClick = {
                             onEvent(DiariesEvent.OnDeleteDiaryClick(diary))
        },
        modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete diary",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun diaryItemPreview() {
    DiaryItem(diary = Diary(
        title = "Hello",
        content = "some text",
        timestamp = "2011.11.4"
    ), onEvent = {})
}