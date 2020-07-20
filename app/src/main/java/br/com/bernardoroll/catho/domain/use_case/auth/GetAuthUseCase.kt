package br.com.bernardoroll.catho.domain.use_case.auth

import br.com.bernardoroll.catho.domain.model.AuthModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either

class GetAuthUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String, userId: String): Either<Throwable, AuthModel> =
        try {
            repository.getAuth(apiKey, userId)
        } catch (error: Throwable) {
            throw error
        }
}
