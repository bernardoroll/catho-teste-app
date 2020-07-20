package br.com.bernardoroll.catho.domain.use_case.auth

import br.com.bernardoroll.catho.domain.model.AuthModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success

class GetAuthUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String, userId: String): Either<Throwable, AuthModel> =
        when (val either = repository.getAuth(apiKey, userId)) {
            is Success -> either
            is Error -> Error(either.e)
        }
}
