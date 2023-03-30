package com.example.diaryapp.diary_feature.domain.repository

import com.example.diaryapp.diary_feature.domain.model.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun getDiarys(): List<Diary>

    suspend fun getDiaryById(id: String): Diary?

    suspend fun insertDiary(diary: Diary)

    suspend fun deleteDiary(diary: Diary)
}