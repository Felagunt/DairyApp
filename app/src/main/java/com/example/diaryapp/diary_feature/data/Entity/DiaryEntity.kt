package com.example.diaryapp.diary_feature.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diaryapp.diary_feature.domain.model.Diary

@Entity
data class DiaryEntity(
    val title: String,
    val content: String,
    val timestamp: String,
    @PrimaryKey val id: Int
)

fun DiaryEntity.toDiary(): Diary {
    return Diary(
        title = title,
        content = content,
        timestamp = timestamp,
        diaryId = id
    )
}
