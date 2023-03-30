package com.example.diaryapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diaryapp.diary_feature.data.data_source.DiaryDatabase
import com.example.diaryapp.diary_feature.data.repository.DiaryRepositoryImpl
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDiaryDatabase(@ApplicationContext context: Context): DiaryDatabase {
        return Room.databaseBuilder(
            context,
            DiaryDatabase::class.java,
            DiaryDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(db: DiaryDatabase): DiaryRepository {
        return DiaryRepositoryImpl(db.diaryDao)
    }
}