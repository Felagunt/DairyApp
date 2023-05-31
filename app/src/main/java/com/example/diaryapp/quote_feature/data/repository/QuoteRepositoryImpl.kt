package com.example.diaryapp.quote_feature.data.repository

import com.example.diaryapp.quote_feature.data.remote.QuoteApi
import com.example.diaryapp.quote_feature.data.remote.dto.QuoteDto
import com.example.diaryapp.quote_feature.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val api: QuoteApi
) : QuoteRepository {

    override suspend fun getQuote(): QuoteDto {
        return api.getQuote()
    }
}