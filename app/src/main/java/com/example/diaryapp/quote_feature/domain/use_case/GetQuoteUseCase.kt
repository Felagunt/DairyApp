package com.example.diaryapp.quote_feature.domain.use_case

import com.example.diaryapp.quote_feature.data.remote.dto.toQuote
import com.example.diaryapp.quote_feature.domain.model.Quote
import com.example.diaryapp.quote_feature.domain.repository.QuoteRepository
import com.example.diaryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    operator fun invoke(): Flow<Resource<Quote>> = flow {
        try {
            emit(Resource.Loading())
            val quote = repository.getQuote().toQuote()
            emit(Resource.Success(quote))
        } catch (e: HttpException) {
            emit(Resource.Error("Smth went wrong: " + e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. " + e.localizedMessage))
        }
    }
}