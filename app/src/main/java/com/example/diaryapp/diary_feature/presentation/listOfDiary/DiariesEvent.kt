package com.example.diaryapp.diary_feature.presentation.listOfDiary

import com.example.diaryapp.diary_feature.domain.model.Diary

sealed class DiariesEvent {
    data class OnDeleteDiaryClick(val diary: Diary): DiariesEvent()
    object OnRestoreDiariesClick: DiariesEvent()
    object OnAddDiaryClick: DiariesEvent()
    data class OnDiaryClick(val diary: Diary): DiariesEvent()
}