package com.example.diaryapp.diary_feature.presentation.add_edit_diary

import com.example.diaryapp.diary_feature.domain.model.Diary

data class AddEditState(
    val diaryId: Int? = null,
    val title: String = "",
    val content: String = "",
    val timestamp: String = ""
)
