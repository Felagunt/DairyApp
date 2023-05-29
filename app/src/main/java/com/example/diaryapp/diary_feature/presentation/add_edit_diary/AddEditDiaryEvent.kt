package com.example.diaryapp.diary_feature.presentation.add_edit_diary

import com.example.diaryapp.diary_feature.domain.model.Diary

sealed class AddEditDiaryEvent{

    data class OnChangeTitle(val value: String): AddEditDiaryEvent()
    data class OnChangeContent(val value: String): AddEditDiaryEvent()
    data class OnLeavePage(val diary: Diary): AddEditDiaryEvent()
    object SaveDiary: AddEditDiaryEvent()
}
