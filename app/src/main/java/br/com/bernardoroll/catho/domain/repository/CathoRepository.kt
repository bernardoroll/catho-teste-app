package br.com.bernardoroll.catho.domain.repository

import br.com.bernardoroll.catho.domain.model.ApiKeysModel
import br.com.bernardoroll.catho.domain.model.AuthModel
import br.com.bernardoroll.catho.domain.model.SuggestionModel
import br.com.bernardoroll.catho.domain.use_case.Either

interface CathoRepository {

    suspend fun getApiKeys(): Either<Throwable, ApiKeysModel>

    suspend fun getAuth(apiKey: String, userId: String): Either<Throwable, AuthModel>

    suspend fun getSuggestions(apiKey: String, token: String): Either<Throwable, List<SuggestionModel>?>
}
