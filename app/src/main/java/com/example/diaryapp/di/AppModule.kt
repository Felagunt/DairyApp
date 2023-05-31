package com.example.diaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.diaryapp.diary_feature.data.data_source.DiaryDatabase
import com.example.diaryapp.diary_feature.data.repository.DiaryRepositoryImpl
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import com.example.diaryapp.quote_feature.data.remote.QuoteApi
import com.example.diaryapp.quote_feature.data.repository.QuoteRepositoryImpl
import com.example.diaryapp.quote_feature.domain.repository.QuoteRepository
import com.example.diaryapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(db: DiaryDatabase): DiaryRepository {
        return DiaryRepositoryImpl(db.diaryDao)
    }

    @Provides
    @Singleton
    fun provideQuo0teRepository(api: QuoteApi): QuoteRepository {
        return QuoteRepositoryImpl(api)
    }
}