package br.com.bernardoroll.catho.domain.use_case.tip

import br.com.bernardoroll.catho.domain.model.TipModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success

class GetTipsUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String): Either<Throwable, List<TipModel>?> =
        when (val either = repository.getTips(apiKey)) {
            is Success -> either
            is Error -> Error(either.e)
        }
}
