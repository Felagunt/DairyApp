package com.example.diaryapp.diary_feature.domain.model

import com.example.diaryapp.diary_feature.data.Entity.DiaryEntity

data class Diary(
    val title: String,
    val content: String,
    val timestamp: String,
    val diaryId: Int? = null
)
fun Diary.toDiaryEntity(): DiaryEntity {
    return DiaryEntity(
        title = title,
        content = content,
        timestamp = timestamp,
        id = diaryId
    )
}