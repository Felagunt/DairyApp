package com.example.diaryapp.quote_feature.data.remote

import com.example.diaryapp.quote_feature.data.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuoteApi {

    @GET("/stoic-quote")
    suspend fun getQuote(): QuoteDto
}