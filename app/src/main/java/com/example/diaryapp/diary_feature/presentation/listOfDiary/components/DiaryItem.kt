package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
                ambientColor = MaterialTheme.colors.onSecondary
            )
            .background(MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = diary.content,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = diary.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick = {
                             onEvent(DiariesEvent.OnDeleteDiaryClick(diary))
        },
        modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete diary",
                tint = MaterialTheme.colors.onSurface
            )
        }
        Text(
            text = "Created: ${diary.timestamp}",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.background
        )
    }
}