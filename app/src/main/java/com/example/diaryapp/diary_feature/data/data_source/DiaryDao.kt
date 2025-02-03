package com.example.diaryapp.diary_feature.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diaryapp.diary_feature.data.Entity.DiaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diaryentity")
    fun getDiaries(): Flow<List<DiaryEntity>>

    @Query("SELECT * FROM diaryentity WHERE id = :id")
    suspend fun getDiaryById(id: Int): DiaryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: DiaryEntity)

    @Delete
    suspend fun deleteDairy(diary: DiaryEntity)
}