package com.example.diaryapp.diary_feature.domain.repository

import com.example.diaryapp.diary_feature.domain.model.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun getDiaries(): Flow<List<Diary>>

    suspend fun getDiaryById(id: Int): Diary?

    suspend fun insertDiary(diary: Diary)

    suspend fun deleteDiary(diary: Diary)
}