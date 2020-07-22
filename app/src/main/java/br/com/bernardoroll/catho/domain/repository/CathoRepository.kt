package br.com.bernardoroll.catho.domain.repository

import br.com.bernardoroll.catho.domain.model.*
import br.com.bernardoroll.catho.domain.use_case.Either

interface CathoRepository {

    suspend fun getApiKeys(): Either<Throwable, ApiKeysModel>

    suspend fun getAuth(apiKey: String, userId: String): Either<Throwable, AuthModel>

    suspend fun getSuggestions(
        apiKey: String,
        token: String
    ): Either<Throwable, List<SuggestionModel>?>

    suspend fun getTips(apiKey: String): Either<Throwable, List<TipModel>?>

    suspend fun postTipAction(
        apiKey: String,
        token: String,
        tipId: String,
        action: String
    ): Either<Throwable, TipActionModel>
}
