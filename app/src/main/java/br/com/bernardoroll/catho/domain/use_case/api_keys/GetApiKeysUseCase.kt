package br.com.bernardoroll.catho.domain.use_case.api_keys

import br.com.bernardoroll.catho.domain.model.ApiKeysModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either

class GetApiKeysUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(): Either<Throwable, ApiKeysModel> =
        try {
            repository.getApiKeys()
        } catch (error: Throwable) {
            throw error
        }
}
