package com.example.diaryapp.diary_feature.presentation.listOfDiary

import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.quote_feature.domain.model.Quote

data class DiaryState(
    val isLoading: Boolean = false,
    val diaries: List<Diary> = emptyList(),
    val error: String = "",
    val quote: Quote? = null
)