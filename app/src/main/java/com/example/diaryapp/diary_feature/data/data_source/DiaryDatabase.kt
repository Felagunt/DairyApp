package com.example.diaryapp.diary_feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diaryapp.diary_feature.data.Entity.DiaryEntity

@Database(
    entities = [DiaryEntity::class],
    version = 1
)
abstract class DiaryDatabase: RoomDatabase() {

    abstract val diaryDao: DiaryDao

    companion object{
        const val DATABASE_NAME = "diary_db"
    }
}