package br.com.bernardoroll.catho.domain.use_case.api_keys

import br.com.bernardoroll.catho.domain.model.ApiKeysModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success

class GetApiKeysUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(): Either<Throwable, ApiKeysModel> =
        when (val either = repository.getApiKeys()) {
            is Success -> either
            is Error -> Error(either.e)
        }
}
