package com.example.diaryapp.quote_feature.data.remote.dto

import com.example.diaryapp.quote_feature.domain.model.Quote

data class QuoteDto(
    val author: String,
    val quote: String
)

fun QuoteDto.toQuote(): Quote {
    return Quote(
        author = author,
        quote = quote
    )
}