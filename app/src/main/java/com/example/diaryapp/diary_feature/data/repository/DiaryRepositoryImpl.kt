package com.example.diaryapp.diary_feature.data.repository

import com.example.diaryapp.diary_feature.data.Entity.DiaryEntity
import com.example.diaryapp.diary_feature.data.Entity.toDiary
import com.example.diaryapp.diary_feature.data.data_source.DiaryDao
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.domain.model.toDiaryEntity
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryDao: DiaryDao
): DiaryRepository {
    override fun getDiaries(): List<Diary> {
        return diaryDao.getDiarys().map { it.toDiary() }
    }

    override suspend fun getDiaryById(id: String): Diary? {
        return diaryDao.getDiaryById(id)?.toDiary()
    }

    override suspend fun insertDiary(diary: Diary) {
        diaryDao.insertDiary(diary.toDiaryEntity())
    }

    override suspend fun deleteDiary(diary: Diary) {
        diaryDao.deleteDairy(diary.toDiaryEntity())
    }
}