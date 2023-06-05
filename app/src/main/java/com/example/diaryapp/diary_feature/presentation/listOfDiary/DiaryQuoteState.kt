package com.example.diaryapp.diary_feature.presentation.listOfDiary

import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.quote_feature.domain.model.Quote

data class DiaryQuoteState(
    val isLoading: Boolean = false,
    val error: String = "",
    val quote: Quote? = null
)