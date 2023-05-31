package com.example.diaryapp.quote_feature.domain.repository

import com.example.diaryapp.quote_feature.data.remote.dto.QuoteDto

interface QuoteRepository {

    suspend fun getQuote(): QuoteDto
}