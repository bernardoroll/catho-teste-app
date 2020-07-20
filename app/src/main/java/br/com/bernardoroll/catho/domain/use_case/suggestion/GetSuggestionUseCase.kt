package br.com.bernardoroll.catho.domain.use_case.suggestion

import br.com.bernardoroll.catho.domain.model.SuggestionModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success

class GetSuggestionUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String, token: String): Either<Throwable, List<SuggestionModel>?> =
        when (val either = repository.getSuggestions(apiKey, token)) {
            is Success -> either
            is Error -> Error(either.e)
        }
}
