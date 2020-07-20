package br.com.bernardoroll.catho.domain.use_case.suggestion

import br.com.bernardoroll.catho.domain.model.SuggestionModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either

class GetSuggestionUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String, token: String): Either<Throwable, List<SuggestionModel>?> =
        try {
            repository.getSuggestions(apiKey, token)
        } catch (error: Throwable) {
            throw error
        }

}
